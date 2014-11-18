package com.dredom.bst;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Binary Search Tree handling.
 * Finds the largest subtree - presumably in preparation for operations that balance the tree.
 * Can handle large data.
 * Memory requirements are proportional to the greatest width of the tree.
 *
 * <p>I advise programmers to use open source tools to do stuff like this
 * rather than reinventing the wheel, but the assumption here is
 * we want to have some fun for the interview process.
 *
 * <p>Note: Love nulls. Super fast, minimize GC, intuitive.
 *
 * <pre>
 *        5
 *      /   \
 *     3     9
 *    /     /
 *   1     6
 * </pre>
 *
 * @author  Andre Untiedt, 2013-08
 */
public class BinarySearchTree {

private static final String SPC = " .";
	//    static int[] keys = { 1, 8, 2, 3, 5, 6, 9 };
//    static int[] keys = { 5, 3, 6, 1, 2, 4 };
    static int[] keys = { 5, 3, 6, 1, 4, 11, 8, 9, 12 };
//    static int[] keys = { 1, 2, 4, 5 };

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        // Setup test data
        Node root = null;
        for (int key : keys) {
            Node node = bst.insert(root, key);
            if (root == null) {
                root = node;
            }
        }

        out.println("=== FIND LARGEST BST SUBTREE ===");
        bst.printTree(root);
        out.println();
        Node largestSubtree = bst.findLargestSubtree(root);
        out.println("Largest Subtree:");
            out.printf("\t %s subtree size %d \n", largestSubtree, bst.sizeOfSubtree(largestSubtree));
            bst.printTree(largestSubtree);
        out.println();
        out.println(" ~ fini ~");
    }

    /**
     * Lookup for key
     * @param node
     * @param key
     * @return {@link Node} node, or null
     */
    public Node lookup(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key == node.getKey()) {
            return node;
        }
        // Recurse down children
        if (key < node.getKey()) {
            return lookup(node.getLeft(), key);
        }
        return lookup(node.getRight(), key);
    }

    /**
     * Insert in the correct place in the tree. (Plagiarized code.)
     * @param tree
     * @param key
     * @return {@link Node} node
     */
    public Node insert(Node tree, int key) {
        // If the tree is empty, return a new node.
        if (tree == null) {
            return newNode(key);
        }
        // otherwise recurse down the tree
        if (key <= tree.getKey()) {
            tree.setLeft( insert( tree.getLeft(), key));
        } else {
            tree.setRight( insert( tree.getRight(), key));
        }
        // Return the unchanged node pointer
        return tree;
    }

    private Node newNode( int key) {
        Node node = new Node(key);
        return node;
    }

    /**
     * BFS: Doing a breadth-first approach with a queue.
     * This is slower than recursion but really helps with stack overflows
     * on super large data.
     * @param node possibly root
     * @return {@link Node} with largest subtree, or null
     */
    public Node findLargestSubtree(Node node) {
        int largestSubtreeSize = 0;
        Node largestSubtree = null;
        ArrayDeque<Node> queue = new ArrayDeque<Node>();
        queue.add(node);
        while(queue.isEmpty() == false) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Node item = queue.removeFirst();
                if (isSubtree(item)) {
                    int size = sizeOfSubtree(item);
                    if (size > largestSubtreeSize) {
                        largestSubtreeSize = size;
                        largestSubtree = item;
                    }
                } else {
                    if (item.getLeft() != null) {
                        queue.add(item.getLeft());
                    }
                    if (item.getRight() != null) {
                        queue.add(item.getRight());
                    }
                }
            }
        }
        return largestSubtree;
    }

    /**
     * Count all nodes.
     * Recursion okay because subtree will be limited size.
     * @param node start
     * @return int size of subtree, including start node
     */
    private int sizeOfSubtree(Node node) {
        if (node == null) {
            return 0;
        }
        int left = sizeOfSubtree(node.getLeft());
        int right = sizeOfSubtree(node.getRight());
        return left + right + 1;
    }

    /**
     * Is it a subtree?
     * Assumption: Subtree has both left and right branches and
     * there are no further trees on those branches.
     * Recursion okay here since subtrees will be of limited size
     * and we stop as soon as we find it is not a subtree.
     * @param node start
     * @return boolean true if subtree
     */
    private boolean isSubtree(Node node) {
        if (node.getLeft() == null || node.getRight() == null) {
            return false;
        }
        return isSimpleBranch(node.getLeft()) && isSimpleBranch(node.getRight());
    }

    private boolean isSimpleBranch(Node node) {
        if (node.getLeft() != null && node.getRight() != null) {
            return false;   // subtree
        }
        if (node.getLeft() != null) {
            return isSimpleBranch(node.getLeft());
        }
        if (node.getRight() != null) {
            return isSimpleBranch(node.getRight());
        }
        // We have come to the end.
        return true;
    }

    /**
     * Need the depth for console visual.
     * Minimizes memory by constantly trimming the queue.
     * Will be slower and have lot of GC.
     * @param node
     * @return int depth
     */
    private int findMaxDepth(Node node) {
        if (node == null) {
            return 0;
        }
        ArrayDeque<Node> queue = new ArrayDeque<Node>();
        queue.add(node);
        int depth = 0;
        while(queue.isEmpty() == false) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node item = queue.removeFirst();
                if (item.getLeft() != null) {
                    queue.add(item.getLeft());
                }
                if (item.getRight() != null) {
                    queue.add(item.getRight());
                }
            }
        }
        return depth;
    }

    /**
     * .......5
     * ...3.......8
     * .2...4...7...9
     * 1.2.1.1.6......
     *
     * 21, 7, 3, 1 - not fibonacci 1, 1, 2, 3, 5, 8, 13
     * Formula: lowerspacing * 2 + lowerspacing
     * Template: [ls|vl|ls*2+1|vr]..
     *
     * Visual representation is valuable in testing.
     * And, yes, I know this is not perfect.
     * @param root
     */
    void printTree(Node root) {
        if (root == null) {
            return;
        }
        final Node DUMMY = new Node(0);
        DUMMY.setLeft(DUMMY);
        DUMMY.setRight(DUMMY);
        int depth = findMaxDepth(root);
        ArrayDeque<Node> queue = new ArrayDeque<Node>();
        queue.add(root);

        // Spacer width by level
        final int[] spacer = new int[depth];
        int ls = 0;
        for (int i = depth - 1; i >= 0; i--) {
        	spacer[i] = ls;
        	ls = ls * 2 + 1;
        }

        final int vSize = 2;
        int level = 0;
        while(queue.isEmpty() == false && depth > 0) {
            out.printf(" %3d: ", level);
            boolean leftSide = true;
            Iterator<Node> iter = queue.iterator();
            while (iter.hasNext()) {
            	// spacer
            	if (leftSide) {
            		printSpaces(spacer[level]); // spacer
            	}
                Node node = iter.next();
                // print value
                if (node == DUMMY) {
                	printSpaces(vSize);
                } else {
                	out.printf("%2d", node.getKey());
                }
                // If left side, print extra spacer.
                if (leftSide) {
                	printSpaces(spacer[level] + 1 + spacer[level]);
                }
                leftSide = leftSide ? false : true;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.removeFirst();
//                if (node.getLeft() == DUMMY && node.getRight() == DUMMY) {
//                	continue;
//                }
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                } else {
                	// dummy node for spacing
                	queue.add(DUMMY);
                }
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                } else {
                	// dummy node for spacing
                	queue.add(DUMMY);
                }
            }
            out.println();
            level++;
            depth--;
        }
    }

    private void printSpaces(int n) {
    	for (int i = 0; i < n; i++) out.print("."); // spacer
    }
}
