package com.dredom.osgi.dict.api;

public interface DictionaryService {

	/**
	 * Check for the existence of a word.
	 * @param word
	 * @return boolean
	 */
	boolean checkWord(String word);
}
