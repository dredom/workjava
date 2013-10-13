package com.dredom.concurrent;

import java.util.concurrent.Callable;

public class CallableCounter implements Callable<CounterResult> {
	private String[] words;
	private String word;

	public CallableCounter(String[] words, String word) {
		this.words = words;
		this.word = word;
	}

	public CounterResult call() throws Exception {
		int count = 0;
		for (String w : words) {
			if (w.equals(word)) {
				count++;
			}
		}
		return new CounterResult(word, count);
	}

}
