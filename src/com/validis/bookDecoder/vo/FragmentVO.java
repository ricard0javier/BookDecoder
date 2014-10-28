package com.validis.bookDecoder.vo;

/**
 * Class which represents a encoded fragment
 * @author Ricardo Javier
 *
 */
public class FragmentVO {
	
	/**
	 * Identity of the fragment
	 */
	private int id;
	
	/**
	 * Array of characters with the encoded data
	 */
	private char[] data;
	
	/**
	 * Returns the identity of the fragment
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Set the identity of the fragment
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the array of characters with the encoded data
	 * @return data
	 */
	public char[] getData() {
		return data;
	}
	
	/**
	 * Set the array of characters with the encoded data
	 * @param data
	 */
	public void setData(char[] data) {
		this.data = data;
	}
	
}
