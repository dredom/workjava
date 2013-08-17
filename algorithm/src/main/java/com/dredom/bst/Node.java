package com.dredom.bst;

/**
 * Node is a binary search tree with int key.
 * @author  Andre Untiedt
 */
public class Node {
    private int key;
    private Node left;
    private Node right;

    public Node(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key + ":" + (left != null ? 'L' : ".") + (right != null ? 'R' : ".");
    }
    public final int getKey() {
        return key;
    }
    public final void setKey(int key) {
        this.key = key;
    }
    public final Node getLeft() {
        return left;
    }
    public final void setLeft(Node left) {
        this.left = left;
    }
    public final Node getRight() {
        return right;
    }
    public final void setRight(Node right) {
        this.right = right;
    }
}
