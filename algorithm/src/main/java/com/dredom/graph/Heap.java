/**
 *
 */
package com.dredom.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

import static java.lang.System.out;

/**
 * A heap is a tree-based data structure in which all the nodes of the tree are in a specific order. A Max Heap has
 * every parent node greater than its children, and every level filled. So root node is always the greatest. Heap with
 * at most 2 children is a Binary Heap.
 *
 * <pre>
 *                         6
 *                     4       5
 *                   3   2   0   1
 * </pre>
 *
 * This implementation is a max Binary Heap and uses nodes with L:R children and linked to parent.
 * <p>
 * Complexity O(log(n))
 *
 * @see PriorityQueue
 * @see <a href="https://www.hackerearth.com/practice/data-structures/trees/heapspriority-queues/tutorial/">Heaps,
 *      Priority Queues</a>
 */
public class Heap {

    private Node root;
//    private Node last;

    public class Node implements Comparable<Node> {
        private final int key;
        private Node parent;
        private Node left;
        private Node right;

        public Node(int key, Node parent) {
            this.key = key;
            this.parent = parent;
        }

        public final Node getParent() {
            return parent;
        }

        public final void setParent(Node parent) {
            this.parent = parent;
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

        public final int getKey() {
            return key;
        }

        @Override
        public int compareTo(Node o) {
            if (o == null) {
                return 1;
            }
            if (this.key < o.key) {
                return -1;
            } else if (this.key > o.key) {
                return 1;
            }
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            Node o = (Node) obj;
            return this.key == o.key ? true : false;
        }

        @Override
        public String toString() {
            return key + ":" + (parent != null ? "p" + parent.key : ".") + ":" + (left != null ? "L" + left.key : ".")
                    + ":" + (right != null ? "R" + right.key : ".");
        }
    }

    public void addValues(int[] values) {
        Arrays.stream(values).forEach(value -> add(value));
    }

    public void add(int key) {
//        Node parent = nextParent(last);
        if (root == null) {
            root = new Node(key, null);
        } else {
            Node parent = getNextAvailableParent();
            Node node = new Node(key, parent);
            if (parent.getLeft() == null) {
                parent.setLeft(node);
            } else {
                parent.setRight(node);
            }
            heapify(node);
        }
    }


    /**
     * In the heap tree, find the next vacant spot.
     * BFS from root looking for Node with open child Left or Right.
     * Each level down count is 2^n, eg level 1 = 2, level 2 = 4, etc.
     * @return Node
     */
    private Node getNextAvailableParent() {
        int level = 1;
        Deque<Node> queue = new ArrayDeque<>();
        queue.addLast(root);;
        return getNextAvailableParent(queue, level);
    }

    /**
     * Add children to queue for every node in this level.
     * If node found with an empty child, return it.
     * @param queue
     * @param level of heap
     * @return Node with an empty child slot
     */
    private Node getNextAvailableParent(Deque<Node> queue, int level) {
        double itemsInLevel = Math.pow(2, level);
        int count = 0;
        // Every node we are about to process here was added to the queue in the previous level.
        while (count++ < itemsInLevel) {
            // It is impossible for the queue to be empty because there has to be child nodes for every parent
            // and child nodes mean there will be another level down.
            Node item = queue.removeFirst();
            if (item.getLeft() == null || item.getRight() == null) {
                return item;
            }
            if (item.getLeft() != null) {
                queue.addLast(item.getLeft());
            }
            if (item.getRight() != null) {
                queue.addLast(item.getRight());
            }
        }
        // No available child slots found in this level. Go to next level.
        return getNextAvailableParent(queue, ++level);
    }


    /**
     * Heapify from node up - max heap maintains highest value at root.
     * No need to go down sibling child trees because if a node is promoted to parent
     * then it is greater than the old parent which was greater than its children.
     *
     * @param node
     */
    void heapify(Node node) {
        if (node == null || node.getParent() == null) {
            return;
        }
        if (node.compareTo(node.getParent()) > 0) {
            swapChildWithParent(node);
            // The node has now become the original parent. Recheck order with new parent.
            heapify(node);
        }
    }


    private void swapChildWithParent(Node child) {
        if (child.getParent() == null) {
            return;
        }
        Node oldParent = child.getParent();
        // Set Parent's parent -> child
        Node grandParent = oldParent.getParent();
        if (grandParent != null) {
            if (grandParent.getLeft().equals(oldParent)) {
                grandParent.setLeft(child);
            } else {
                grandParent.setRight(child);
            }
            setParentChildLinksToParent(grandParent);
        } else {
            // root - no parent
            child.setParent(null);
        }
        // Save child children for oldParent which will be the new child.
        Node saveLeft = child.getLeft();
        Node saveRight = child.getRight();
        // Child, as newParent, gets oldParent as child
        if (oldParent.getRight() != null && oldParent.getRight().equals(child)) {
            child.setRight(oldParent);
            child.setLeft(oldParent.getLeft());
        } else {
            child.setLeft(oldParent);
            child.setRight(oldParent.getRight());
        }
        // Set oldParent (which is newChild) children to saved child children.
        oldParent.setLeft(saveLeft);
        oldParent.setRight(saveRight);
        // Set new parent relationships
        setParentChildLinksToParent(child);
        setParentChildLinksToParent(oldParent);

//        Node newParent = child;
        // Root
        if (root.equals(oldParent)) {
            root = child;
        }
    }


    private void setParentChildLinksToParent(Node node) {
        if (node.getLeft() != null) {
            node.getLeft().setParent(node);
        }
        if (node.getRight() != null) {
            node.getRight().setParent(node);
        }
    }

    public Node getRoot() {
        return root;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        final int[] values = { 3, 2, 0, 5, 4, 1, 6 };
        System.out.println("Input: " + Arrays.toString(values));
        Heap hp = new Heap();
        for (int val : values) {
            hp.add(val);
        }
        System.out.printf("root=%s \n", hp.getRoot());
//        printNodes(hp.getRoot(), 1);
        printNodes(hp.getRoot());
    }

    /**
     * Print a graph with root in top center, children trees arranged below.
     *
     * <ul>
     *  <li>Build BFS queue.
     *  <li>Print by level.
     * </ul>
     * @param node
     */
    public static void printNodes(Node node) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(node);
        Deque<Node> nodes = new ArrayDeque<>();
        buildBFSQueue(queue, 0, nodes);
        printTree(nodes);
    }


    private static void printTree(Deque<Node> queue) {
        int len = queue.size();
        // Compute how many levels in tree
        int length = len;
        int levels = 0;
        while (length > 0) {
            length -= Math.pow(2, levels++);
        }
        out.printf("queue size=%s, depth=%s %n", len, levels);
        printLevel(queue, 1, levels);
    }

    /**
     * Start from bottom to figure out spacing.
     * Assuming simple spacing for 4 x node bottom row like
     * <pre> X_X_X_X </pre>
     * At each level there are 2^height - 1 nodes below.
     * Formula for left spacer = 2^height - 1
     *                         = 2^(depth - level) - 1
     * @param queue
     * @param level
     * @param depth
     */
    private static void printLevel(Deque<Node> queue, int level, int depth) {
        if (queue.isEmpty()) {
            return;
        }
        // Compute spacing for this level - so we center it nicely.
        // Left spacer is bottom row length / level * 2
        final int itemSize = 10;
        int spacerLeftLen = (int) (Math.pow(2, depth - level) - 1) * itemSize;
        String spacerLeft = spacer(spacerLeftLen);
        out.print(spacerLeft);
        String spacerTween = "";
        if (level > 0) {
            // spacer tween is previous level left spacer
            int previousHeight = depth - (level - 1);
            int spacerTweenLen = (int) (Math.pow(2, previousHeight) - 1)  * itemSize;
            spacerTween = spacer(spacerTweenLen);
        }
        double rowItems = Math.pow(2, level - 1);
        // Print in two's, with bigger spacer between the two's.
        int count = 0;
        while (!queue.isEmpty() && count++ < rowItems) {
            Node node = queue.removeFirst();
            out.printf("%s%s", node, spacerTween);
        }
        out.println();
        printLevel(queue, ++level, depth);
    }

    private static String spacer(int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, ' ');
        return String.valueOf(chars);
    }
    private static void buildBFSQueue(Deque<Node> queue, int level, Deque<Node> out) {
        double itemsInLevel = Math.pow(2, level);
        int count = 0;
        // Every node we are about to process here was added to the queue in the previous level.
        while (count++ < itemsInLevel) {
            if (queue.isEmpty()) {
                return;
            }
            Node item = queue.removeFirst();
            if (item.getLeft() != null) {
                queue.addLast(item.getLeft());
            }
            if (item.getRight() != null) {
                queue.addLast(item.getRight());
            }
            out.add(item);
        }
        buildBFSQueue(queue, ++level, out);
    }
}
