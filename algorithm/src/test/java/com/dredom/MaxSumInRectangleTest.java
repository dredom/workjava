package com.dredom;

import static junit.framework.Assert.*;

import java.util.Random;

import org.junit.Test;

public class MaxSumInRectangleTest {

    static MaxSumInRectangle2 alg = new MaxSumInRectangle2();

    static int[][][] test = {

        {   // test 0
            { 1, 2, 3 }, { 2, 3, 4 }, { 3, 4, 5 },
        },
        {   // test 1
            { 1, 2, 3 }, { 2, 3, 4 }, { 3, 4, -5 },
        },
        {   // test 2
            { 1, 2, 3 }, { -2, -3, -4 }, { 3, 4, 5 },
        },
        {   // test 3
            { -1, -2, -3 }, { 2, 3, 4 }, { 3, 4, 5 },
        },
        {   // test 0
            { -1, -1, -1, -1, -1 }, { -2, 2, 2, 2, -2 }, { -3, 3, 3, 3, -3 }, { -3, 3, 3, 3, -3 }, { -3, -3, -3, -3, -3 },
        },
    };

    static int[] expected = {
        21, // 0
        15, // 1
        7,  // 2
        21, // 3
        24, // 4
    };

    @Test
    public void findMax() {
        for (int i = 0; i < test.length; i++ ) {

                findMax(test[i], expected[i], i);
        }
    }

    void findMax(int[][] array, int expected, int test) {
//        alg.printArray(array);
        int sum = alg.findMax(array);
        assertEquals("[" + test + "]", expected, sum);
    }

    /**
     * About 15 seconds
     */
    @Test
    public void load() {
        int size = 99;
        int maxVal = 127;
        int ltest[][] = new int[size][size];
        Random rnd = new Random();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int val = rnd.nextInt(maxVal);
                int sign = (val % 2) == 0 ? 1 : -1;
                ltest[y][x] = val * sign;
            }
        }
        alg.findMax(ltest);
    }
}
