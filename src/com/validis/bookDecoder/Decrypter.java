package com.validis.bookDecoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.validis.bookDecoder.vo.CryptVO;
import com.validis.bookDecoder.vo.FragmentVO;

/**
 * Decrypter class which contains the implementation
 * required to decode any nook based on a given db
 * @author Ricardo Javier
 *
 */
public class Decrypter {
	
	/**
	 * Path to the database
	 */
	private String dbPath;

	/**
	 * Constructor of the class
	 * @param dbPath Path to the database
	 */
	public Decrypter(String dbPath) {
		this.dbPath = dbPath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
	}

	/**
	 * Prepare a map with all the decoded characters 
	 * @param rotate
	 * @return a Map where the key is the encrypted character and the value
	 * is its equivalent decoded character
	 */
	private Map<Character, Character> getDecrypterMap(int rotate) {
		Map<Character, Character> crypto = new HashMap<Character, Character>();
		for(int i='A'; i<='Z'; i++) {
			char code = (char)(i + rotate);
			if (code > 'Z') {
				code += (char)('A' - 'Z' - 1);
			}
			crypto.put(code, (char)i);
		}
		for(int i='a'; i<='z'; i++) {
			char code = (char)(i + rotate);
			if (code > 'z') {
				code += (char)('a' - 'z' - 1);
			}
			crypto.put(code, (char)i);
		}
		return crypto;
	}

	/**
	 * Decode the given book 
	 * @return the book decodes
	 */
	public String decode() {
		Connection connection = null;
		Statement stmt = null;
		String text = "";
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			stmt = connection.createStatement();
			
			// saves all the encryption scheme in a list
			ResultSet rs = stmt.executeQuery("SELECT start, length, rotate FROM crypt");
			List<CryptVO> crypts = new ArrayList<CryptVO>();
			while (rs.next()) {
				CryptVO cryptVO = new CryptVO();
				cryptVO.setStart(rs.getInt("start"));
				cryptVO.setLength(rs.getInt("length"));
				cryptVO.setRotate(rs.getInt("rotate"));
				crypts.add(cryptVO);
			}
			rs.close();			

			// for each encryption decode the fragments
			for (CryptVO crypt : crypts) {
 				Map<Character, Character> crypto = getDecrypterMap(crypt.getRotate());				
				
 				// search fragment related with the encryption 
 				rs = stmt.executeQuery("SELECT id, data FROM b0dy where id between " + crypt.getStart() + " and " + (crypt.getStart()+crypt.getLength()-1));
				List<FragmentVO> fragments = new ArrayList<FragmentVO>();
				while (rs.next()) {
					FragmentVO dataFragmentVO = new FragmentVO();
					dataFragmentVO.setId(rs.getInt("id"));
					dataFragmentVO.setData(rs.getString("data").toCharArray());
					fragments.add(dataFragmentVO);
				}
				rs.close();	
				
				// write text decoded
				for(FragmentVO fragment : fragments) {
					String dataNew = "";
					for (int i = 0; i < fragment.getData().length; i++) {
						if (crypto.containsKey(fragment.getData()[i])) {
							dataNew += crypto.get(fragment.getData()[i]);
						} else {
							dataNew += fragment.getData()[i];
						}
					}
					text += dataNew;
				}	
			}
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("An error ocurred decoding the book: ");
			e.printStackTrace();
		}
		return text;
	}
}
