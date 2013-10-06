package com.dredom.sort;

import static java.lang.System.out;

import java.util.Arrays;

/**
 *  Merge sort works recursively. Divide and conquer algorithm.
 *  First it divides a data set in half, and sorts each half separately.
 *  Next, the first elements from each of the two lists are compared.
 *  The lesser element is then removed from its list and added to the final result list.
 * <p>
 * Time average & worst:   O(n log(n))   - n * log(n)
 * Space:  O(n)
 */
public class MergeSort {

    static int[] array = { 21, 3, 1, 11, 5, 2, 7 };
//    static int[] array = { 21, 3, 1};

    /**
     * @param args
     */
    public static void main(String[] args) {
        out.printf(" IN: %s \n", Arrays.toString(array));
        long start = System.nanoTime();

        int[] result = sort(array);

        long time = System.nanoTime() - start;

        out.printf("OUT: %s  in %,d nanonseconds \n", Arrays.toString(result), time );
    }

	static int[] sort(int[] nums) {
		if (nums.length == 1) {
			return nums;
		}

		// Divide and sort
		int mid = nums.length / 2;
		int[] left = sort( copy(nums, 0, mid) );
		int[] right = sort( copy(nums, mid, nums.length - mid) );
		// Merge
		int[] result = new int[nums.length];
		for (int l = 0, r = 0, x = 0; x < result.length; x++) {
			if (l >= left.length) {
				result[x] = right[r++];
			} else if (r >= right.length) {
				result[x] = left[l++];
			} else if (left[l] <= right[r]) {
				result[x] = left[l++];
			} else {
				result[x] = right[r++];
			}
		}
		return result;
	}

	private static int[] copy(int[] in, int start, int length) {
		int[] out = new int[length];
		System.arraycopy(in, start, out, 0, length);
		return out;
	}

}
