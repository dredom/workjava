package com.dredom;

import java.util.Arrays;

/**
 * Fibonacci sequence formula: n = sum of previous 2.
 *
 * @author andre
 *
 */
public class Fibonacci {

	public static void main(String[] args) {
		int i = 9;
		Fibonacci instance = new Fibonacci();
		System.out.println(Arrays.toString(instance.get(i)));
	}

	public int[] get(int howMany) {
		final int[] result = new int[howMany];
		int i = 0;
		result[i++] = 0;
		result[i++] = 1;
		for (; i < howMany; i++) {
			result[i] = result[i - 1] + result[i - 2];
		}
		return result;
	}
}
