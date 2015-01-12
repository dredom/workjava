package com.dredom.sort;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * QuickSort is a divide-and-conquer algorithm.
 *
 * function quicksort('array')
 * 		if length('array') ≤ 1
 *      	return 'array'  // an array of zero or one elements is already sorted
 *      select and remove a pivot element 'pivot' from 'array'  // see 'Choice of pivot' below
 *      create empty lists 'less' and 'greater'
 *      for each 'x' in 'array'
 *      	if 'x' ≤ 'pivot' then append 'x' to 'less'
 *          else append 'x' to 'greater'
 *      return concatenate(quicksort('less'), list('pivot'), quicksort('greater')) // two recursive calls
 *
 *
 */
public class QuickSort {

    static Integer[] array = { 21, 3, 1, 11, 5, 2, 7 };
//    static Integer[] array = { 2, 3, 4, 5, 7, 8, 9, 10 };
//    static Integer[] array = { 21, 3, 1};

    static int iterations;

    /**
     * @param args
     */
    public static void main(String[] args) {
        print(array);
        long start = System.currentTimeMillis();

        Integer[] sorted = sort(array);

        print(sorted);
        out.println(System.currentTimeMillis() - start + "ms" + " in " + iterations + " iterations");
    }

    static Integer[] sort(Integer[] input) {
        if (input.length <= 1) {
            return input;
        }
        iterations++;
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        int mi = input.length / 2;
        int middle = input[mi];
        boolean isSorted = true;
        for (int i = 0; i < input.length; i++) {
            if (i != mi) {
                if (input[i].intValue() <= middle) {
                    left.add(input[i]);
                    if (i > mi) {	// optimize
                    	isSorted = false;
                    }
                } else {
                    right.add(input[i]);
                    if (i < mi) {	// optimize
                    	isSorted = false;
                    }
                }
            }
        }
        if (isSorted) {	// optimize
        	return input;
        }
        return concatenate(
        		sort(left.toArray(new Integer[left.size()])),
        		middle,
        		sort(right.toArray(new Integer[right.size()])));
    }

    static Integer[] concatenate(Integer[] left, Integer middle, Integer[] right) {
        Integer[] out = new Integer[left.length + 1 + right.length];
        System.arraycopy(left, 0, out, 0, left.length);
        out[left.length] = middle;
        System.arraycopy(right, 0, out, left.length + 1, right.length);
        return out;
    }

    static void print(Integer[] arr) {
        out.println(Arrays.deepToString(arr));
    }

}
