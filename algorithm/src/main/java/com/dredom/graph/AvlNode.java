package com.dredom.graph;

class AvlNode<E extends Comparable<?>> {
    // Friendly data; accessible by other package routines
    E element; // The data in the node
    AvlNode<E> left; // Left child
    AvlNode<E> right; // Right child
    int height; // Height

    // Constructors
    AvlNode(E theElement) {
        this(theElement, null, null);
    }

    AvlNode(E element, AvlNode<E> lt, AvlNode<E> rt) {
        this.element = element;
        this.left = lt;
        this.right = rt;
        height = 0;
    }

    @Override
    public String toString() {
        return  element + ":" + (left != null ? "L" : ".") + (right != null ? "R" : ".") + height;
    }
}
