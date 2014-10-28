package com.validis.bookDecoder.vo;

/**
 * Encryption scheme
 * @author Ricardo Javier
 *
 */
public class CryptVO {
	/**
	 * Minimum fragment id that can be decoded with this encryption 
	 */
	private int start;
	
	/**
	 * Number of fragments after ´start´ that can be decoded with this encryption 
	 */
	private int length;
	
	/**
	 * Number of characters to be rotated to the right side of the ascii scheme
	 */
	private int rotate;
	
	/**
	 * Returns the number of fragments after ´start´ that can be decoded with this encryption
	 * @return start
	 */
	public int getStart() {
		return start;
	}
	
	/**
	 * Set the number of fragments after ´start´ that can be decoded with this encryption
	 * @param start
	 */
	public void setStart(int start) {
		this.start = start;
	}
	
	/**
	 * Returns the number of fragments after ´start´ that can be decoded with this encryption 
	 * @return length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Set the number of fragments after ´start´ that can be decoded with this encryption 
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	/**
	 * Returns the number of characters to be rotated to the right side of the ascii scheme
	 * @return rotate
	 */
	public int getRotate() {
		return rotate;
	}
	
	/**
	 * Set the number of characters to be rotated to the right side of the ascii scheme
	 * @param rotate
	 */
	public void setRotate(int rotate) {
		this.rotate = rotate;
	}
}
