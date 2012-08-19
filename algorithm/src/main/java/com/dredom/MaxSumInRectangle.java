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
        //                       y, x, y2, x2
        instance.printArraySum(num, 0, 0, 1, 1);
        instance.printArraySum(num, 0, 1, 2, 2);
        instance.printArraySum(num, 1, 1, 2, 2);
    }

    // =======================================================

    class Arry {
        public int x;
        public int y;
        public int size;
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
