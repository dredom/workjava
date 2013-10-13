package com.dredom.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Given a text, return the top word by count.
 *
 */
public class ThreadedCountService {

	public CounterResult getTopWord(String text) throws InterruptedException, ExecutionException {
		String[] words = parse(text);

		List<Future<CounterResult>> futures = map(words);

		CounterResult result = reduce(futures);
		return result;
	}

	private String[] parse(String text) {
		return text.split(" ");
	}

	/**
	 * Map the input to threads that do the work.
	 * @param words
	 * @return List of task results
	 */
	private List<Future<CounterResult>> map(String[] words) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		List<Future<CounterResult>> futures = new ArrayList<Future<CounterResult>>();

		// Unique words for the multi-threaded counting tasks.
		Set<String> set = new HashSet<String>();
		for (String word : words) {
			set.add(word);
		}
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			String word = iter.next();
			Callable<CounterResult> task = new CallableCounter(words, word);
			Future<CounterResult> future = threadPool.submit(task);
			System.out.printf(" Submit thread for '%s': %s \n", word, future);
			futures.add(future);
		}
		return futures;
	}

	/**
	 * Return top word - reduce all results to the word with the highest count.
	 * @param futures
	 * @return result
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private CounterResult reduce(List<Future<CounterResult>> futures) throws InterruptedException, ExecutionException {
		CounterResult result = null;
		for (Future<CounterResult> future : futures) {
			CounterResult value = future.get();
			if (value.compareTo(result) > 0) {
				result = value;
			}
		}
		return result;
	}

}
