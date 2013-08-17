package com.dredom.graph;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    static int[] keys = { 1, 8, 2, 3, 5, 6, 9 };
//    static int[] keys = { 5, 3, 6, 1, 2, 4 };

    static int maxDepth;
    static String spacer;

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
        spacer="";
        print(root);
//        printTree(root);
        out.printf(" look up %d: %s \n", 6, lookup(root, 6));
        out.printf(" look up %d: %s \n", 7, lookup(root, 7));
        printMaxDepth(root);
//        out.printf(" %s subtree=%b \n", root, isSubtree(root));
        List<Node> subtrees = findSubtrees(root);
        out.print("Subtrees: ");
        for (Node subtree : subtrees) {
            out.printf(" %s size %d ", subtree, sizeOfSubtree(subtree));
        }
        out.println();
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

    /**
     * Finds depth with recursion.
     * Fast but chews memory.
     * @param depth
     * @param node
     */
    private static void findMaxDepth(int depth, Node node) {
        if (node == null) {
            return;
        }
        if (depth > maxDepth) {
            maxDepth = depth;
        }
        depth++;
        findMaxDepth(depth, node.left);
        findMaxDepth(depth, node.right);
    }

    /**
     * Minimizes memory by constantly trimming the queue.
     * Will be slower and have lot of GC.
     * @param node
     * @return int depth
     */
    private static int findMaxDepth(Node node) {
        ArrayDeque<Node> queue = new ArrayDeque<Node>();
        queue.add(node);
        int depth = 0;
        while(queue.isEmpty() == false) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node item = queue.removeFirst();
                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            }
        }
        return depth;
    }

    private static int sizeOfSubtree(Node node) {
        if (node == null) {
            return 0;
        }
        int left = sizeOfSubtree(node.left);
        int right = sizeOfSubtree(node.right);
        return left + right + 1;
    }

    private static List<Node> findSubtrees(Node node) {
        List<Node> subtrees = new ArrayList<Node>();
        ArrayDeque<Node> queue = new ArrayDeque<Node>();
        queue.add(node);
        while(queue.isEmpty() == false) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node item = queue.removeFirst();
                if (isSubtree(item)) {
                    subtrees.add(item);
                } else {
                    if (item.left != null) {
                        queue.add(item.left);
                    }
                    if (item.right != null) {
                        queue.add(item.right);
                    }
                }
            }
        }
        return subtrees;
    }
    private static boolean isSubtree(Node node) {
        if (node.left == null || node.right == null) {
            return false;
        }
        return isSimpleBranch(node.left) && isSimpleBranch(node.right);
    }
    private static boolean isSimpleBranch(Node node) {
        if (node.left != null && node.right != null) {
            return false;   // subtree
        }
        if (node.left != null) {
            return isSimpleBranch(node.left);
        }
        if (node.right != null) {
            return isSimpleBranch(node.right);
        }
        return true;
    }

    static void printMaxDepth(Node node) {
        maxDepth = 1;
        int depth = 1;
        findMaxDepth(depth, node);
        out.printf("Max depth for node %s is %d. \n", node, maxDepth);
        depth = findMaxDepth(node);
        out.printf("Max depth for node %s is %d. \n", node, depth);
    }

    static void print(Node node) {
        spacer += " ";
        if (node.left != null) {
            print(node.left);
        }
        out.println(spacer + node.toString());
        if (node.right != null) {
            print(node.right);
        }
        spacer = spacer.substring(1);
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
