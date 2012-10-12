package com.dredom.graph;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.Iterator;


// Balanced BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order

/**
 * Implements an AVL tree. Note that all "matching" is based on the compareTo method.
 * <p>
 * A  binary tree is defined to be balanced if, at each node,
 * the height of the left and right subtrees differ by at most one.
 * <p>
 * An AVL Tree is a self-balancing binary search tree.
 * This means that the heights of a given node's children trees are either the same or they differ by one.
 * This is accomplished by rotating the tree nodes.
 * <p>
 * Binary tree rotations work by moving a child node b up to its parent's level, and making the parent node a child node.
 * When this happens, the new parent node has an extra child, and the new child node has one fewer children.
 * So to fix this, one of the new parent's children is set as a child of the old parent node.
 * <p>
 * Because the AVL Tree is self-balancing, all of its operations (insertion, lookup, and removal) all operate in O(log(n)) time.
 *
 * <pre>
 *        5
 *      /   \
 *     3     9
 *          / \
 *         6   21
 * </pre>
 * AVL - Adel’son-Vel’skii and Landis algorithm.
 *
 * @author Mark Allen Weiss
 */
public class AvlTree<E extends Comparable<?>> {

    /** The tree root. */
    private AvlNode<E> root;

    /**
     * Construct the tree.
     */
    public AvlTree() {
        this.root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x  the item to insert.
     */
    public void insert(E x) {
        this.root = insert(x, this.root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x
     *            the item to remove.
     */
    public void remove(Comparable x) {
        System.out.println("Sorry, remove unimplemented");
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public Comparable findMin() {
        return elementAt(findMin(root));
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public Comparable findMax() {
        return elementAt(findMax(root));
    }

    /**
     * Find an item in the tree.
     *
     * @param x  the item to search for.
     * @return the matching item or null if not found.
     */
    public E find(E x) {
        return elementAt(find(x, root));
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    /**
     * Internal method to get element field.
     *
     * @param t  the node.
     * @return the element field or null if t is null.
     */
    private E elementAt(AvlNode<E> t) {
        return t == null ? null : t.element;
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x  the item to insert.
     * @param t  the node that roots the tree.
     * @return the new root.
     */
    private AvlNode<E> insert(Comparable x, AvlNode<E> t) {
        out.printf("insert %s into %s \n", x, t);
        if (t == null) {
            t = new AvlNode(x, null, null);
        } else if (x.compareTo(t.element) < 0) {
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                out.printf(" BEFORE rotate left on %s \n", t);
                printNodes(t);
                if (x.compareTo(t.left.element) < 0)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithLeftChild(t);
                out.printf(" AFTER rotate left \n", t);
                printNodes(t);
            }
        } else if (x.compareTo(t.element) > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                out.printf(" BEFORE rotate right on %s \n", t);
                printNodes(t);
                if (x.compareTo(t.right.element) > 0)
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithRightChild(t);
                out.printf(" AFTER rotate right \n", t);
                printNodes(t);
            }
        } else {
            ; // Duplicate; do nothing
        }
        t.height = max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t
     *            the node that roots the tree.
     * @return node containing the smallest item.
     */
    private AvlNode findMin(AvlNode t) {
        if (t == null)
            return t;

        while (t.left != null)
            t = t.left;
        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t  the node that roots the tree.
     * @return node containing the largest item.
     */
    private AvlNode findMax(AvlNode t) {
        if (t == null)
            return t;

        while (t.right != null)
            t = t.right;
        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x  is item to search for.
     * @param root  the node that roots the tree.
     * @return node containing the matched item.
     */
    private AvlNode<E> find(Comparable x, AvlNode<E> root) {
        while (root != null) {
            if (x.compareTo(root.element) < 0)
                root = root.left;
            else if (x.compareTo(root.element) > 0)
                root = root.right;
            else
                return root; // Match
        }

        return null; // No match
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t
     *            the node that roots the tree.
     */
    private void printTree(AvlNode<E> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    public void printNodes() {
        out.println("Tree nodes:");
        printNodes(root);
    }
    private void printNodes(AvlNode<E> root) {
        ArrayDeque<AvlNode<E>> queue = new ArrayDeque<AvlNode<E>>();
        queue.add(root);
        int level = 0;
        while(queue.isEmpty() == false) {
            out.printf(" %3d: ", level);
            Iterator<AvlNode<E>> iter = queue.iterator();
            while (iter.hasNext()) {
                AvlNode<E> node = iter.next();
                for (int i = 0; i < node.height; i++) out.print(' ');
                out.printf(" %6s", node);
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                AvlNode<E> node = queue.removeFirst();
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


    /**
     * Return the height of node t, or -1, if null.
     */
    private static int height(AvlNode t) {
        return t == null ? -1 : t.height;
    }

    /**
     * Return maximum of lhs and rhs.
     */
    private static int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

    /**
     * Rotate binary tree node with left child. For AVL trees, this is a single
     * rotation for case 1. Update heights, then return new root.
     */
    private static AvlNode rotateWithLeftChild(AvlNode k2) {
        out.printf("     rotateWithLeftChild of %s \n" , k2);
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child. For AVL trees, this is a single
     * rotation for case 4. Update heights, then return new root.
     */
    private static AvlNode rotateWithRightChild(AvlNode k1) {
        out.printf("     rotateWithRightChild of %s \n" , k1);
        AvlNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child with its right child;
     * then node k3 with new left child. For AVL trees, this is a double
     * rotation for case 2. Update heights, then return new root.
     */
    private static AvlNode doubleWithLeftChild(AvlNode k3) {
        out.printf("    doubleWithLeftChild of %s \n", k3);
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child with its left child;
     * then node k1 with new right child. For AVL trees, this is a double
     * rotation for case 3. Update heights, then return new root.
     */
    private static AvlNode doubleWithRightChild(AvlNode k1) {
        out.printf("    doubleWithRightChild of %s \n", k1);
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }


    static int[] input1 = { 1, 2, 3, 4 };

     // Test program
    public static void main(String[] args) {
        AvlTree t = new AvlTree();
        final int NUMS = 4;
        final int GAP =37;

        System.out.println("Checking... (no more output means success)");

//        for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
//            t.insert(new Integer(i));
        int[] input = input1;
        for (int i = 0; i < input.length; i ++) {
            t.insert(new Integer(input[i]));
        }
        Object found = t.find(new Integer(4));
        found = t.find(new Integer(99));

        if (NUMS < 40)
            t.printTree();
        t.printNodes();

        if (((Integer) (t.findMin())).intValue() != 1)
            System.out.println("FindMin error!");
        if ( ((Integer) (t.findMax())).intValue() != NUMS - 1)
            System.out.println("FindMax error!");

//        for (int i = 1; i < NUMS; i++)
//            if (((Integer) (t.find(new Integer(i)))).intValue() != i)
//                System.out.println("Find error1!");
    }


}
