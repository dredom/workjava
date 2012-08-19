package com.dredom;

import static java.lang.System.out;

/**
 * Given a 2-dimensional array of positive and negative integers, find the sub-rectangle with the largest sum.
 * The sum of a rectangle is the sum of all the elements in that rectangle.
 * In this problem the sub-rectangle with the largest sum is referred to as the maximal sub-rectangle.
 * A sub-rectangle is any contiguous sub-array of size 1x1 or greater located within the whole array.
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
public class MaxSumInRectangle {

    static String[] inputs = {
        "1 2 3",
        "3 4 5",
        "-5 4 -5"
    };


    /**
     * @param args
     */
    public static void main(String[] args) {
        final int size = inputs.length;
        final int[][] num = new int[size][size];
        for (int i = 0; i < size; i++) {
            String[] numbers = inputs[i].split(" ");
            for (int j = 0; j < size; j++) {
                num[i][j] = Integer.parseInt(numbers[j]);
            }
        }

        MaxSumInRectangle instance = new MaxSumInRectangle();
        instance.printArray(num);

        instance.printHeading();
        //                       y, x, y2, x2
        instance.printArraySum(num, 0, 0, 1, 2);
//        instance.printArraySum(num, 0, 1, 2, 2);
//        instance.printArraySum(num, 1, 1, 2, 2);
        instance.findMax(num);

    }

    // =======================================================


    int[] yxy2x2;

    /**
     * Rectangle is min 2x2, and smaller than array.
     * @param array
     */
    public int findMax(int[][] array) {
        final int dim = array.length - 1;
        int maxSum = 0;
        int y = 0;
        // traverse down the y axis
        while (y < dim) {
            int y2 = y + 1;
            while (y2 <= dim) {
                // traverse x axis for this y
                int x = 0;
                while (x < dim) {
                    int x2 = x + 1;
                    while (x2 <= dim) {
                        // Ensure smaller than full rectangle
                        int ylen = y2 - y;
                        int xlen = x2 - x;
                        if (xlen < dim || ylen < dim) {
                            int sum = sumArray(array, y, x, y2, x2);
                            if (sum > maxSum) {
                                yxy2x2 = new int[] { y, x, y2, x2 };
                                maxSum = sum;
                            }
                        }
                        x2++;
                    }
                    x++;
                }
                y2++;
            }
            y++;
        }
        out.printf("Max sum = %d, y,x=%d,%d y2,x2=%d,%d \n", maxSum, yxy2x2[0], yxy2x2[1], yxy2x2[2], yxy2x2[3]);
        return maxSum;
    }

    void printArray(int[][] array) {
        out.printf("\n \t");
        for (int x = 0; x < array[0].length; x++) {
            String s = "x[" + x + "]";
            out.printf("%5s", s);
        }
        for (int i = 0; i < array.length; i++) {
            out.printf("\n y[%d]\t", i);
            for (int j = 0; j < array[i].length; j++) {
                out.printf("%4d ", array[i][j]);
            }
        }
        out.println();
    }

    void printHeading() {
        out.printf("\t\t\t  y,  x \t y2, x2 \n");
    }
    void printArraySum(int[][] array, int y, int x, int y2, int x2) {
        int sum = sumArray(array, y, x, y2, x2);
        out.printf("    %6d \t\t%3d,%3d \t%3d,%3d \n", sum, y, x, y2, x2);
    }

    int sumArray(int[][] array, int y, int x, int y2, int x2) {
        int sum = 0;
        for (int i = y; i <= y2; i++) {
            for (int j = x; j <= x2; j++) {
                sum += array[i][j];
            }
        }
        return sum;
    }

}
