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
public class DFSCountNodes {

//    static int[] keys = { 1, 8, 2, 3, 5, 6, 9 };
    static int[] keys = { 5, 3, 6, 1, 2, 4 };
//    static int[] keys = { 5, 3, 6, 1, 4, 11, 8, 9, 12 };
//    static int[] keys = { 1, 2, 4, 5 };

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        DFSCountNodes bst = new DFSCountNodes();
        // Setup test data
        Node root = null;
        for (int key : keys) {
            Node node = bst.insert(root, key);
            if (root == null) {
                root = node;
            }
        }

        out.println("=== DFS COUNT ALL NODES ===");
//        bst.printTree(root);
        out.println();
        out.printf("\t tree size %d \n", bst.countNodes(root));
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

    
    public int countNodes(Node node) {
    	if (node == null) {
    		return 0;
    	}
    	return countNodes(node.getLeft()) + countNodes(node.getRight()) + 1; 
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
     * Visual representation is valuable in testing.
     * And, yes, I know this is not perfect.
     * @param root
     */
    void printTree(Node root) {
        if (root == null) {
            return;
        }
        int depth = findMaxDepth(root);
        ArrayDeque<Node> queue = new ArrayDeque<Node>();
        queue.add(root);
        int level = 0;
        while(queue.isEmpty() == false) {
            out.printf(" %3d: ", level);
            for (int i = 0; i < depth; i++) out.print(" "); // spacer
            Iterator<Node> iter = queue.iterator();
            while (iter.hasNext()) {
                Node node = iter.next();
                out.printf(" %3d", node.getKey());
//                out.printf(" %6s", node);
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.removeFirst();
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
            }
            out.println();
            level++;
            depth--;
        }
    }
}
