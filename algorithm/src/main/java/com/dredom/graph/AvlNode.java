package com.dredom.graph;

class AvlNode {
    // Friendly data; accessible by other package routines
    Comparable element; // The data in the node
    AvlNode left; // Left child
    AvlNode right; // Right child
    int height; // Height

    // Constructors
    AvlNode(Comparable theElement) {
        this(theElement, null, null);
    }

    AvlNode(Comparable element, AvlNode lt, AvlNode rt) {
        this.element = element;
        this.left = lt;
        this.right = rt;
        height = 0;
    }

    @Override
    public String toString() {
        return  element + ":" + (left != null ? 'L' : ".") + (right != null ? 'R' : "." + height);
    }
}
