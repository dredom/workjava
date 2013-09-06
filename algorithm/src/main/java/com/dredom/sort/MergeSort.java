package com.dredom.sort;

import static java.lang.System.out;

/**
 *  Merge sort works recursively. Divide and conquer algorithm.
 *  First it divides a data set in half, and sorts each half separately.
 *  Next, the first elements from each of the two lists are compared.
 *  The lesser element is then removed from its list and added to the final result list.
 */
public class MergeSort {

//    static int[] array = { 21, 3, 1, 11, 5, 2, 7 };
    static int[] array = { 21, 3, 1};
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

    static int[] sort(int[] input) {
        if (input.length == 1) {
            return input;
        }
        int middle = input.length / 2;
        int[] left = sort(subArray(input, 0, middle));
        int[] right = sort(subArray(input, middle, input.length - middle));
        int[] result = new int[input.length];
        int di = 0;
        int li = 0;
        int ri = 0;
        // merge
        while (di < input.length) {
            if (li == left.length) {
                result[di] = right[ri++];
            } else if (ri == right.length) {
                result[di] = left[li++];
            } else if (left[li] < right[ri]) {
                result[di] = left[li++];
            } else {
                result[di] = right[ri++];
            }
            di++;
        }
        return result;
    }

    static int[] subArray(int[] src, int start, int length) {
        int[] out = new int[length];
        System.arraycopy(src, start, out, 0, length);
        return out;
    }


    static void print(int[] arr) {
        out.print('[');
        for (int i = 0; i < arr.length; i++) {
            out.printf(" %d,", arr[i]);
        }
        out.println(']');
    }

}
