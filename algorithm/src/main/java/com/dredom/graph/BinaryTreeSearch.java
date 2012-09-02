package com.dredom.graph;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Log(n) to look up value in n size data. Quick insert.
 * <pre>
 *        5
 *      /   \
 *     3     9
 *    /     /
 *   1     6
 * </pre>
 */
public class BinaryTreeSearch {

    static class Node {
        public int key;
        public Node left;
        public Node right;
        public Node(int key) {
            this.key = key;
        }
        @Override
        public String toString() {
            return key + ":" + (left != null ? 'L' : ".") + (right != null ? 'R' : ".");
        }
    }

    static int[] keys = { 5, 3, 6, 1, 2, 4 };

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        Node root = null;
        for (int key : keys) {
            Node node = insert(root, key);
            if (root == null) {
                root = node;
            }
        }
        print(root);
        printTree(root);
        out.printf(" look up %d: %b \n", 6, lookup(root, 6));
        out.printf(" look up %d: %b \n", 7, lookup(root, 7));
    }

    static Node lookup(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key == node.key) {
            return node;
        }
        // Recurse down children
        if (key < node.key) {
            return lookup(node.left, key);
        }
        return lookup(node.right, key);
    }

    /**
     * with the given number in the correct place in the tree.
     * Returns the new root pointer which the caller should then use
     * (the standard trick to avoid using reference parameters).
     */
    static Node insert(Node tree, int key) {
        // If the tree is empty, return a new node.
        if (tree == null) {
            return newNode(key);
        }
        // otherwise recurse down the tree
        if (key <= tree.key) {
            tree.left = insert( tree.left, key);
        } else {
            tree.right = insert( tree.right, key);
        }
        // Return the unchanged node pointer
        return tree;
    }

    private static Node newNode( int key) {
        Node node = new Node(key);
        return node;
    }

    static void print(Node node) {
        if (node.left != null) {
            print(node.left);
        }
        out.println(node.key);
        if (node.right != null) {
            print(node.right);
        }
    }

    static void printTree(Node root) {
        ArrayDeque<Node> queue = new ArrayDeque<BinaryTreeSearch.Node>();
        queue.add(root);
        int level = 0;
        while(queue.isEmpty() == false) {
            out.printf(" %3d: ", level);
            Iterator<Node> iter = queue.iterator();
            while (iter.hasNext()) {
                BinaryTreeSearch.Node node = iter.next();
                out.printf(" %3d", node.key);
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.removeFirst();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            out.println();
            level++;
        }
    }
}
