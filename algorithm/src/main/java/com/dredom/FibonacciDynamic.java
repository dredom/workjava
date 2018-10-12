package com.dredom;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Dynamic programming (DP) is an optimization technique where previously computed sub-results are reused, as in a cache.
 *  If the given problem can be broken up in to smaller sub-problems
 *  and these smaller subproblems are in turn divided in to still-smaller ones,
 *  and in this process, if you observe some over-lapping subproblems, then its a big hint for DP.
 * <p>
 * 1. Top-Down: Start solving the given problem by breaking it down. If you see that the problem has been solved already,
 * then just return the saved answer. If it has not been solved, solve it and save the answer.
 * This is usually easy to think of and very intuitive. This is referred to as <i>Memoization</i>.
 * <p>
 * 2. Bottom-Up: Analyze the problem and see the order in which the sub-problems are solved
 * and start solving from the trivial subproblem, up towards the given problem.
 * In this process, it is guaranteed that the subproblems are solved before solving the problem.
 * This is referred to as Dynamic Programming.
 * <p>
 * Big O - The letter O is used because the growth rate of a function is also referred to as the order of the function.
 * A description of a function in terms of big O notation usually only provides an upper bound on the growth rate of the function.
 * <p>
 * Fibonacci sequence formula: n = sum of previous 2.
 *
 * @author andre
 *
 */
public class FibonacciDynamic {

    Map<Integer, Long> map = new HashMap<>();

	public static void main(String[] args) {
		int i = 35;
		FibonacciDynamic instance = new FibonacciDynamic();
		System.out.println(Arrays.toString(instance.getArray(i)));
		long start = time(0);
		System.out.printf("recursive %s=%s \t %sms\n", i, instance.getRecursive(i), time(start));
		start = time(0);
		System.out.printf("memoization %s=%s \t %sms\n", i, instance.getMemoization(i), time(start));
	}

	private static long time(long start) {
        return System.currentTimeMillis() - start;
    }

    /**
	 * Top down. Every value for every position is recomputed every time.
	 * <pre> call tree
	 *               f(5)         <- height n, 2 calls from each level
	 *        f(4)          f(3)
	 *    f(3)    f(2)   f(2)   f(1)
	 * f(2) f(1)  ...
	 * </pre>
	 * Big O time (2^n)
	 * Big O space (n)
	 * @param number position
	 * @return value
	 */
	public long getRecursive(int number) {
	    if (number <= 2) { // 1st 2
	        return 1;
	    }
	    return getRecursive(number - 2) + getRecursive(number - 1);
	}

	/**
	 * This technique of following a top-down approach and saving previously computed resulted is also known as memoization.
     * <pre> call tree
     *               f(5)         <- height n
     *        f(4)          x
     *    f(3)     x
     * f(2)   x
     * </pre>
     * Big O time (n)
     * Big O space (log(n))
	 * @param number
	 * @return value
	 */
	public long getMemoization(int number) {
	    if (number <= 2) {
	        return 1;
	    }
	    if (map.containsKey(number)) {
	        return map.get(number);
	    }
	    long result = getMemoization(number - 1) + getMemoization(number - 2);
	    map.put(number, result);
	    return result;
	}

	/**
	 * Bottom up. Memoization of only the most recent values.
     * Big O time (0)
     * Big O space (0)
	 * @param howMany
	 * @return array
	 */
	public int[] getArray(int howMany) {
		final int[] result = new int[howMany + 1];
		int i = 0;
		result[i++] = 0;
		result[i++] = 1;
		for (; i <= howMany; i++) {
			result[i] = result[i - 1] + result[i - 2];
		}
		return result;
	}
}
