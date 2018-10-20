package com.dredom.graph;

import static junit.framework.Assert.*;

import org.junit.Test;

import com.dredom.graph.Heap.Node;

public class HeapTest {

    Heap heap = new Heap();

    @Test
    public void one() {
        final int[] input = { 3, 2, 0, 5, 4, 1, 6 };
        heap.addValues(input);
        Node root = heap.getRoot();
        heap.printNodes(root);
        verifyTreeParents(root);
    }

//    @Test
    public void two() {
        final int[] input = { 3, 2, 1, 0, 4, 5, 6 };
        heap.addValues(input);
        Node root = heap.getRoot();
        heap.printNodes(root);
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
