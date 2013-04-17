package com.dredom.osgi.dict.impl;

import com.dredom.osgi.dict.api.DictionaryService;

public class DictionaryImpl implements DictionaryService {

    // The set of words contained in the dictionary.
    String[] dictionary =
        { "welcome", "to", "the", "osgi", "tutorial" };

	public boolean checkWord(String word) {
		word = word.toLowerCase();
		for (String entry : dictionary) {
			if (entry.equals(word)) {
				return true;
			}
		}
		return false;
	}

	public void setDictionary(String[] dictionary) {
		this.dictionary = dictionary;
	}

}
