package com.dredom.concurrent;

public class CounterResult implements Comparable<CounterResult> {
	private int count;
	private String word;

	public CounterResult(String word, int count) {
		this.word = word;
		this.count = count;
	}
	public final int getCount() {
		return count;
	}
	public final void setCount(int count) {
		this.count = count;
	}
	public final String getWord() {
		return word;
	}
	public final void setWord(String word) {
		this.word = word;
	}
	public int compareTo(CounterResult o) {
		if (o == null) {
			return 1;
		}
		if (count > o.getCount()) {
			return 1;
		}
		if (count < o.getCount()) {
			return -1;
		}
		return word.compareToIgnoreCase(o.getWord());
	}

}
