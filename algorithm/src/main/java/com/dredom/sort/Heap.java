/**
 *
 */
package com.dredom.sort;

import java.util.Arrays;
import java.util.PriorityQueue;


/**
 * A Heap has every parent node greater than its children,
 * and every level filled.
 * So root node is always the greatest.
 * @see PriorityQueue
 */
public class Heap {

	private Node root;
	private Node last;

	private class Node implements Comparable<Node> {
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
	        return key + ":"
	        		+ (parent != null ? "p" + parent.key : ".") + ":"
	        		+ (left != null ? "L" + left.key : ".") + ":"
	        		+ (right != null ? "R" + right.key : ".");
	    }
	}

	public void add(int key) {
		Node parent = nextParent(last);
		Node node = new Node(key, parent);
		if (root == null) {
			root = node;
		} else {
			if (parent.getLeft() == null) {
				parent.setLeft(node);
			} else {
				parent.setRight(node);
			}
		}
		last = node;
		heapify(node);
	}

	private Node nextParent(Node last) {
		if (last == null) {
			return null;
		}
		Node parent = last.getParent();
		if (parent == null) {
			return last;
		}
		if (parent.getRight() == null) {
			return parent;
		}
		parent = getSiblingParentOnRight(parent);
		if (parent != null) {
			return parent;
		}
		return getBottomLeft(root);
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
	 * @param node
	 */
	void heapify(Node node) {
		if (node == null || node.getParent() == null) {
			return;
		}
		if (node.compareTo(node.getParent()) > 0) {
			swapWithParent(node);
			heapify(node.getParent());
		}
	}

	private Node getBottomLeft(Node node) {
		if (node.getLeft() == null) {
			return node;
		}
		return getBottomLeft(node.getLeft());
	}

	private void swapWithParent(Node child) {
		if (child.getParent() == null) {
			return;
		}
		Node parent = child.getParent();
		// Parent's parent -> child
		if (parent.getParent() != null) {
			Node pParent = parent.getParent();
			if (pParent != null) {
				if (pParent.getLeft().equals(parent)) {
					pParent.setLeft(child);
				} else {
					pParent.setRight(child);
				}
			}
		}
		// Child parent -> parent's parent
		child.setParent(parent.getParent());
		// Parent parent -> child
		parent.setParent(child);
		// Parent's children -> child children
		Node pLeft = parent.getLeft();
		Node pRight = parent.getRight();
		parent.setLeft(child.getLeft());
		parent.setRight(child.getRight());
		// Child children -> parent, old parent child (left/right)
		if (child.equals(pLeft)) {
			child.setLeft(parent);
			child.setRight(pRight);
		} else {
			child.setLeft(pLeft);
			child.setRight(parent);
		}
		// Root
		if (root.equals(parent)) {
			root = child;
		}
		if (last.equals(child)) {
			last = parent;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int[] values = { 3, 8, 5, 4, 1, 6 };
		System.out.println(Arrays.toString(values));
		Heap hp = new Heap();
		for (int val : values) {
			hp.add(val);
		}
	}

}
