package com.dredom.sort;

import static java.lang.System.out;

import java.util.ArrayList;
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
//    static Integer[] array = { 21, 3, 1};
    static Integer[] ia = {};

    /**
     * @param args
     */
    public static void main(String[] args) {
        print(array);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {

            sort(array);
        }
        out.println(System.currentTimeMillis() - start + "ms");
    }

    static Integer[] sort(Integer[] input) {
        if (input.length <= 1) {
            return input;
        }
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        int mi = input.length / 2;
        Integer middle = input[mi];
        for (int i = 0; i < input.length; i++) {
            if (i != mi) {
                if (input[i] <= middle) {
                    left.add(input[i]);
                } else {
                    right.add(input[i]);
                }
            }
        }
        return concatenate(sort(left.toArray(ia)), middle, sort(right.toArray(ia)));
    }

    static Integer[] concatenate(Integer[] left, Integer middle, Integer[] right) {
        Integer[] out = new Integer[left.length + 1 + right.length];
        System.arraycopy(left, 0, out, 0, left.length);
        out[left.length] = middle;
        System.arraycopy(right, 0, out, left.length + 1, right.length);
        return out;
    }

    static void print(Integer[] arr) {
        out.print('[');
        for (int i = 0; i < arr.length; i++) {
            out.printf(" %d,", arr[i]);
        }
        out.println(']');
    }

}
