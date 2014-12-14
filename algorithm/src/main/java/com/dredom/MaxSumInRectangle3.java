package com.dredom;

import static java.lang.System.out;

/**
 * Given a square 2-dimensional array of +tive and -tive integers, find the sub-rectangle with the largest sum.
 * The sum of a rectangle is the sum of all the elements in that rectangle.
 * In this problem the sub-rectangle with the largest sum is referred to as the maximal sub-rectangle.
 * A sub-rectangle is any contiguous sub-array of size 1x1 or greater located within the whole array.
 * <p>
 *   1  2  3
 *  -2  3  4
 *   3  4 -5  =  13 [[0,0][2,2]]
 * <p>
 *   1  2  3
 *  -2  3  4
 *  -3  4 -5  =  12 [[0,1][1,2]]
 * <p>
 * The input consists of an  array of integers.
 * The input begins with a single positive integer N on a line by itself indicating the size of the square two dimensional array.
 * This is followed by  integers separated by white-space (newlines and spaces).
 * These  integers  make up the array in row-major order (i.e., all numbers on the first row, left-to-right,
 * then all numbers on the second row, left-to-right, etc.).
 * N may be as large as 100. The numbers in the array will be in the range [-127, 127].
 *
 * @author auntiedt
 *
 */
public class MaxSumInRectangle3 {

	static int[][] A1 = {
		{ 1, 2, 3},
		{ -2, 3, 4},
		{ 3, 4, -5}
};
	static int[][] A2 = {
		{ 1, 2, 3},
		{ -2, 3, 4},
		{ -3, 4, -5}
};
	static int A1_EXPECTED = 13;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MaxSumInRectangle3 inst = new MaxSumInRectangle3();
		printSquare(A1);
//		printSquare(A2);
		inst.findMaxRectangle(A1);
//		inst.findMaxRectangle(A2);
	}

	static void printSquare(int[][] square) {
		for (int x = 0; x < square.length; x++) {
			out.printf(" %2d:", x);
			for (int y = 0; y < square[x].length; y++) {
				out.printf(" %2d", square[x][y]);
			}
			out.println();
		}
	}

	class Rect {
		public int x;
		public int y;
		public int xx;
		public int yy;
		public int sum;
		public Rect(int x, int y, int xx, int yy, int sum) {
			this.x = x;
			this.y = y;
			this.xx = xx;
			this.yy = yy;
			this.sum = sum;
		}
		@Override
		public String toString() {
			return new StringBuilder().append(sum)
					.append("[").append(x).append(",").append(y).append("]")
					.append("[").append(xx).append(",").append(yy).append("]")
					.toString();
		}
	}

	int square[][];
	int width;
	int depth;

	public void findMaxRectangle(int[][] array) {
		square = array;
		width = array[0].length;
		depth = array.length;

		// Cycle through all possible rectangle vertices
		Rect max = new Rect(0,0,0,0,0);
		// Rows
		for (int x = 0; x < depth; x++) {
			// Columns
			for (int y = 0; y < width; y++) {
				Rect val = maxValueForXy(x, y);
				if (val.sum > max.sum) {
					max = val;
				}
			}
		}
		out.printf(" Max value = %d at [%d, %d][%d, %d] \n", max.sum, max.x, max.y, max.xx, max.yy);
	}

	Rect maxValueForXy(int x, int y) {
		int max = Integer.MIN_VALUE;
		Rect rect = null;
		for (int i = x; i < depth; i++) {
			for (int j = y; j < width; j++ ) {
				int sum = sumValuesInRect(x, y, i, j);
				if (sum > max) {
					max = sum;
					rect = new Rect(x, y, i, j, sum);
				}
			}
		}
		return rect;
	}

	int sumValuesInRect(int x, int y, int xx, int yy) {
		int sum = 0;
		for (int i = x; i <= xx; i++) {
			for (int j = y; j <= yy; j++) {
				sum += square[i][j];
			}
		}
		return sum;
	}
}
