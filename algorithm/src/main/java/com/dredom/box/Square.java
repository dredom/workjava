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
	public Square(int length) {
		this.volume = length * length;
	}
	@Override
	public String toString() {
		return String.valueOf(volume);
	}
}
