package com.validis.bookDecoder;



/**
 * Class that contains the main method which expect at least 
 * one parameter, the database file path, and an additional
 * parameter (optional) to indicate where the decoded book 
 * should be stored, by default, the decoded book is stored
 * at the same place where the jar is located (./book.txt)
 * 
 * @author Ricardo Javier
 *
 */
public class Main {

	/**
	 * Main method which expect at least 
	 * one parameter, the database file path, and an additional
	 * parameter (optional) to indicate where the decoded book 
	 * should be stored, by default, the decoded book is stored
	 * at the same place where the jar is located (./book.txt) 
	 * @param args
	 */
	public static void main(String args[]) {
		Decrypter decrypter = new Decrypter(args[0]);
		String content = decrypter.decode();
		
		String filePath;
		if (args.length > 1 && args[1] != null) {
			filePath = args[1];
		} else {
			filePath = "book.txt";
		}
		FileUtil.write(content, filePath);
	}
}