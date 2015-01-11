/**
 *
 */
package com.dredom.sort;

import java.util.Arrays;

/**
 * Builds a heap from the input. So heap root (1st) will be highest value.
 * Traverse heap breadth-first building array. Iteratively...
 * - Swap heap root to end of array, length - 1
 * - Swap heap max node with last, remove last.
 * - Re-heapify to make heap root the max again.
 * <p>
 * Definition 'heap':
 * In computer science, a heap is a specialized tree-based data structure that satisfies the heap property:
 * Either the keys of parent nodes are always greater than or equal to those of the children and the
 * highest key is in the root node (this kind of heap is called max heap)
 * or the keys of parent nodes are less than or equal to those of the children and the
 * lowest key is in the root node (min heap).
 * Not to be confused with a 'binary search tree' which is sorted and is not a heap.
 * <p>
 * Heapsort is a comparison-based sorting algorithm to create a sorted array (or list).
 * Although somewhat slower in practice on most machines than a well-implemented quicksort,
 * it has the advantage of a more favorable worst-case O(n log n) runtime.
 * Heapsort is an in-place algorithm, but it is not a stable sort (does not maintain the relative order of records with equal keys).
 * <p>
 * Worst case performance : O(n log n)
 * Best case performance : O(n log n)
 * Average case performance : O(n log n)
 */
public class HeapSort {

	private static int N;

	/* Sort Function */
	public static void sort(int arr[]) {

		N = arr.length - 1;

		// Put max value in 1st position
		heapify(arr);

		// Progress through values, right to left
		for (int i = N; i > 0; i--) {

			// 1st position is max; swap it to end.
			swap(arr, 0, i);

			N = N - 1;
			maxheap(arr, 0);

		}

	}

	/* heapify: create a heap out of given array of elements */
	public static void heapify(int arr[]) {

		// Start from middle; progress to left.
		for (int i = N / 2; i >= 0; i--)
			maxheap(arr, i);

	}

	/**
	 * Function to swap largest element in heap into first position.
	 */
	public static void maxheap(int arr[], int i) {

		int left = 2 * i;
		int right = 2 * i + 1;
		int max = i;

		if (left <= N && arr[left] > arr[i])
			max = left;

		if (right <= N && arr[right] > arr[max])
			max = right;

		if (max != i) {

			swap(arr, i, max);
			maxheap(arr, max);

		}

	}

	/* swap two numbers in an array */
	public static void swap(int arr[], int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	/* Main method */
	public static void main(String[] args) {

		/* Make array of n elements */

		int arr[] = { 2, 5, 1, 3, 7 };
		System.out.println(Arrays.toString(arr));
		int n = arr.length;

		/* Call method sort */

		sort(arr);

		/* Print sorted Array */
		System.out.println("\nElements after sorting ");
		System.out.println(Arrays.toString(arr));
		System.out.println();

	}

}
