package com.dredom.concurrent;

import java.util.ArrayList;
import java.util.List;
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

	private List<Future<CounterResult>> map(String[] words) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		List<Future<CounterResult>> futures = new ArrayList<Future<CounterResult>>();

		for (String word : words) {
			Callable<CounterResult> task = new CallableCounter(words, word);
			Future<CounterResult> future = threadPool.submit(task);
			futures.add(future);
		}
		return futures;
	}

	/**
	 * Return top word.
	 * @param futures
	 * @return
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
