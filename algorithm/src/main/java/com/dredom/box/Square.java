/**
 *
 */
package com.dredom.box;

/**
 * Simplified box;
 *
 * @author andre
 *
 */
public class Square {
	int volume;
	int length;
	public Square(int length) {
		this.length = length;
		this.volume = length * length;
	}
	@Override
	public String toString() {
		return String.valueOf(length);
	}
}
