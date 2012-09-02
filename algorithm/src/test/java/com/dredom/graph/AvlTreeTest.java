package com.dredom.graph;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AvlTreeTest {

    AvlTree<Integer> tree;

    @Before
    public void setup() {
        tree = new AvlTree<Integer>();
    }

    @Test
    public void test1() {
        int[] input1 = { 22, 11, 1, 2, 3, 4, 5 };
        int[] input = input1;
        for (int i = 0; i < input.length; i ++) {
            tree.insert(new Integer(input[i]));
        }
        Object found = tree.find(new Integer(4));
        assertNotNull(found);
        found = tree.find(new Integer(99));
        assertNull(found);
        tree.printNodes();
    }
}
