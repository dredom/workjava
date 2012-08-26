package com.dredom.graph;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *   The depth first search (DFS) is well geared towards problems where we want to
 *   find any solution to the problem (not necessarily the shortest path), or to visit all of the nodes in the graph.
 */
public class DepthFirstSearch {

    static int xlen = 600;
    static int ylen = 400;
//    static int xlen = 6;
//    static int ylen = 4;

    static boolean[][] fill = new boolean[xlen][ylen];

    static String[] rectangles = {
        "0 292 399 307",
//        "0 2 3 4",  // top, left, bottom, right
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
//        print(fill);
        long start = System.currentTimeMillis();
        Integer[] results = search(fill);
        out.println(Arrays.deepToString(results));
        out.println(System.currentTimeMillis() - start + "ms");
    }


    static Integer[] search(boolean[][] input) {
        List<Integer> results = new ArrayList<Integer>();
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
        ArrayDeque<Node> stack = new ArrayDeque<DepthFirstSearch.Node>();
        stack.add(new Node(x, y));
        while (!stack.isEmpty()) {
            Node top = stack.pop();
            // Ensure within bound of grid
            if (top.x < 0 || top.x >= xlen) {
                continue;
            }
            if (top.y < 0 || top.y >= ylen) {
                continue;
            }
            // Check we haven't visited this pixel before
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
            stack.push(new Node( top.x + 1, top.y));
            stack.push(new Node( top.x - 1, top.y));
            stack.push(new Node( top.x, top.y + 1));
            stack.push(new Node( top.x, top.y - 1));

        }
        return result;
    }

    static void setup() {
        for (int j = 0; j < fill.length; j++) {
            Arrays.fill(fill[j], false);
        }
        for (String rectangle : rectangles) {
            out.println(rectangle);
            String[] coords = rectangle.split(" ");
            int top = Integer.parseInt(coords[0]);
            int left = Integer.parseInt(coords[1]);
            int bottom = Integer.parseInt(coords[2]);
            int right = Integer.parseInt(coords[3]);

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
