package com.dredom.sort;

import static java.lang.System.out;

public class BubbleSort {

    static int[] array = { 3, 1, 5, 2, 7 };

    /**
     * @param args
     */
    public static void main(String[] args) {
        print(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        print(array);
    }

    static void print(int[] arr) {
        out.print('[');
        for (int i = 0; i < arr.length; i++) {
            out.printf(" %d,", arr[i]);
        }
        out.println(']');
    }

}
