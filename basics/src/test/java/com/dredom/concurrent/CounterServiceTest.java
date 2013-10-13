package com.dredom.concurrent;

import static org.junit.Assert.*;

import org.junit.Test;

public class CounterServiceTest {

	static final String[][] DATA = {
		{"We the people of the nation", "the", "2"},
		{"When the maid went down to the river she saw another maid who was also a maid", "maid", "3"},
	};

	private ThreadedCountService service = new ThreadedCountService();

	@Test
	public void getTopWord() throws Exception {
		for (int i = 0; i < DATA.length; i++) {
			String text = DATA[i][0];
			String expectedWord = DATA[i][1];
			int expectedCount = Integer.parseInt(DATA[i][2]);

			CounterResult result = service.getTopWord(text);
			assertNotNull(result);
			assertEquals("word", expectedWord, result.getWord());
			assertEquals("count for " + expectedWord, expectedCount, result.getCount());
		}
	}
}
