/**
 *
 */
package com.dredom.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
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
    private Node last;

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
//        last = node;
    }

    /**
     * In the tree, find the next vacant spot?
     * @param last
     * @return
     */
    private Node nextParent(Node last) {
        if (last == null) {
            return null;
        }
        Node parent = last.getParent();
        if (parent == null) {
            return last;
        }
        // Empty spot on right?
        if (parent.getRight() == null) {
            return parent;
        }
        // Go over one tree edge to get one.
        parent = getSiblingParentOnRight(parent);
        if (parent != null) {
            return parent;
        }
        return getBottomLeft(root);
    }

    /**
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
//            if (queue.isEmpty()) {
//                return null;
//            }
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

    private Node getSiblingParentOnRight(Node node) {
        if (node.getParent() == null) {
            return null;
        }
        Node parent = node.getParent();
        if (node.equals(parent.getRight())) {
            return null;
        }
        return parent.getRight();
    }

    /**
     * Heapify from node up.
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

    private Node getBottomLeft(Node node) {
        if (node.getLeft() == null) {
            return node;
        }
        return getBottomLeft(node.getLeft());
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
//        if (last.equals(child)) {
//            last = oldParent;
//        }

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

    public Node getLast() {
        return last;
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
        System.out.printf("root=%s, last=%s \n", hp.getRoot(), hp.getLast());
        printNodes(hp.getRoot(), 1);
    }

    public static void printNodes(Node node) {
        printNodes(node, 1);
    }

    private static void printNodes(Node node, int level) {
        if (node == null) {
            return;
        }
        out.printf("lvl:%s \t%s \t LEFT %s \t RIGHT %s \n", level, node, node.getLeft(), node.getRight());
        printNodes(node.getLeft(), level + 1);
        printNodes(node.getRight(), level + 1);
    }
}
