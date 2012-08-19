package com.dredom;

import static java.lang.System.out;

import java.util.Arrays;

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
        "2 3 4",
        "3 4 -5"
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
        instance.printArraySum(num, 0, 1, 0, 1);
        instance.printArraySum(num, 0, 1, 0, 2);
        instance.printArraySum(num, 1, 2, 1, 2);
    }

    // =======================================================

    class Arry {
        public int x;
        public int y;
        public int size;
    }


    void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            out.print("\n\t");
            for (int j = 0; j < array[i].length; j++) {
                out.printf("%4d ", array[i][j]);
            }
        }
        out.println();
    }

    void printHeading() {
        out.printf("\t\t\t  x,  x2 \t  y,  y2 \n");
    }
    void printArraySum(int[][] array, int x, int x2, int y, int y2) {
        int sum = sumArray(array, x, x2, y, y2);
        out.printf("    %6d \t\t%3d, %3d \t%3d, %3d \n", sum, x, x2, y, y2);
    }

    int sumArray(int[][] array, int x, int x2, int y, int y2) {
        int sum = 0;
        for (int i = x; i <= x2; i++) {
            for (int j = y; j <= y2; j++) {
                sum += array[i][j];
            }
        }
        return sum;
    }

}
