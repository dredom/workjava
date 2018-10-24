package com.dredom.graph;

import static junit.framework.Assert.*;

import java.util.Arrays;

import static java.lang.System.out;

import org.junit.Test;

import com.dredom.graph.Heap.Node;

public class HeapTest {

    Heap heap = new Heap();

    @Test
    public void one() {
        final int[] input = { 3, 2, 0, 5, 4, 1, 6 };
        out.println(Arrays.toString(input));
        heap.addValues(input);
        Node root = heap.getRoot();
        heap.printNodes(root);
        verifyTreeParents(root);
    }

    @Test
    public void two() {
        final int[] input = { 3, 2, 1, 0, 4, 5, 6 };
        out.println(Arrays.toString(input));
        heap.addValues(input);
        Node root = heap.getRoot();
        heap.printNodes(root);
    }

    @Test
    public void twoLevel() {
        final int[] input = { 1, 3, 4 };
        out.println(Arrays.toString(input));
        heap.addValues(input);
        heap.printNodes(heap.getRoot());
    }

    @Test
    public void fourLevel() {
        final int[] input = { 1, 3, 4, 8, 5, 4, 9, 2, 7, 0, 6 };
        out.println(Arrays.toString(input));
        heap.addValues(input);
        heap.printNodes(heap.getRoot());
    }
    private void verifyTreeParents(Node node) {
        if (node == null) {
            return;
        }
        Node left = node.getLeft();
        if (left != null) {
            assertEquals(node + " child:" + left, node, left.getParent());
            verifyTreeParents(left);
        }
        Node right = node.getRight();
        if (right != null) {
            assertEquals(node + " child:" + right, node, right.getParent());
            verifyTreeParents(right);
        }
    }
}
