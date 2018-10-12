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
 * Space:  O(n), if you count stack frames for recursion O(n) + O(log(n)), so still O(n).
 */
public class MergeSort {

//    static int[] array = { 21, 3, 1};
//    static int[] array = { 21, 3, 1, 11, 5, 2, 7 };
    static int[] array = { 21,19, 17, 3, 1, 2, 11, 5, 2, 7 };
    static boolean optimize = true;

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
		System.out.printf(" %s %s \n", Arrays.toString(left), Arrays.toString(right));
		// Merge into input array to save space
		if (optimize) {
		    if (left[left.length - 1] < right[0]) {
		        System.arraycopy(left, 0, nums, 0, left.length);
		        System.arraycopy(right, 0, nums, left.length, right.length);
		        return nums;
		    }
		    if (right[right.length - 1] < left[0]) {
		        System.arraycopy(right, 0, nums, 0, right.length);
		        System.arraycopy(left, 0, nums, right.length, left.length);
		        return nums;
		    }
		}
        int l = 0;
        int r = 0;
        int s = 0;
		while (s < nums.length) {
            while (l < left.length && r < right.length) {
                if (left[l] < right[r]) {
                    nums[s++] = left[l++];
                } else {
                    nums[s++] = right[r++];
                }
            }
            if (l < left.length) {
                System.arraycopy(left, l, nums, s, left.length - l);
            } else {
                System.arraycopy(right, r, nums, s, right.length - r);
            }
            s = nums.length;
        }
		return nums;
	}

	private static int[] copy(int[] in, int start, int length) {
		int[] out = new int[length];
		System.arraycopy(in, start, out, 0, length);
		return out;
	}

}
