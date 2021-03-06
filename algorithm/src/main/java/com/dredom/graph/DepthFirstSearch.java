package com.dredom.graph;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *   The depth first search (DFS) is well geared towards problems where we want to
 *   find any solution to the problem (not necessarily the shortest path), or to
 *   visit all of the nodes in the graph.
 * <p>
 * Problem based on graphics fill requirement.
 * <p>
 * Input: Rectangles which are already filled (true).
 * <p>
 * Output: Sizes of unfilled rectangles;
 */
public class DepthFirstSearch {

//    static int xlen = 600;
//    static int ylen = 400;
    static int xlen = 6;
    static int ylen = 4;

    static boolean[][] fill = new boolean[xlen][ylen];

    static int[][] rectangles = {
//        "0 292 399 307",
//        "0 2 3 4",  // top, left, bottom, right
        { 0, 2, 2, 4 },  // top, left, bottom, right
//        "0 192 399 207", "0 392 399 407", "120 0 135 599", "260 0 275 599",
    };
    static int[] expected = {
        116800,  116800
    };

    static class Node {
        public int x;
        public int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        setup();
        print(fill);
        long start = System.currentTimeMillis();
        Integer[] results = search(fill);
        out.printf("Unfilled rectangle sizes: %s \n", Arrays.deepToString(results));
        out.println(System.currentTimeMillis() - start + "ms");
    }


    /**
     * Search for unfilled (false) pixels and tally up
     * the sizes of the unfilled rectangles.
     * @param input
     * @return sizes of areas unfilled
     */
    static Integer[] search(boolean[][] input) {
        List<Integer> results = new ArrayList<>();
        for (int x = 0; x < xlen; x++) {
            for (int y = 0; y < ylen; y++) {
                if (input[x][y] == false) {
                    results.add( doFill(x, y));
                }
            }
        }
        return results.toArray(new Integer[0]);
    }


    static int doFill(int x, int y) {
        int result = 0;
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.add(new Node(x, y));
        while (!stack.isEmpty()) {
            Node top = stack.pop();
            // Check we haven't visited this pixel before and not already filled.
            if (fill[top.x][top.y]) {
                continue;
            }
            // Record we have visited node
            fill[top.x][top.y] = true;

            // We have found this node to be empty and part of
            // this connected area, so add 1 to total.
            result++;

            // Now we know there is at least one empty node, so
            // visit every adjacent node.
            if (top.x + 1 < xlen) {
                stack.push(new Node( top.x + 1, top.y));
            }
            if (top.x > 0) {
                stack.push(new Node( top.x - 1, top.y));
            }
            if (top.y + 1 < ylen) {
                stack.push(new Node( top.x, top.y + 1));
            }
            if (top.y > 0) {
                stack.push(new Node( top.x, top.y - 1));
            }
        }
        return result;
    }

    static void setup() {
        for (int j = 0; j < fill.length; j++) {
            Arrays.fill(fill[j], false);
        }
        for (int[] rectangle : rectangles) {
            out.println("Filled rectangle: " + Arrays.toString(rectangle) + "  (top, left, bottom, right)");
            int top = rectangle[0];
            int left = rectangle[1];
            int bottom = rectangle[2];
            int right = rectangle[3];

            for (int y = top; y <= bottom; y++) {
                for (int x = left; x <= right; x++) {
                    fill[x][y] = true;
                }
            }
        }
    }


    static void print(boolean[][] arr) {
        for (int y = 0; y < arr[0].length; y++) {
            out.print('[');
            for (int x = 0; x < arr.length; x++) {
                out.print(arr[x][y] ? " T" : " f");
            }
            out.println(" ]");
        }
    }

}
