/**
 *
 */
package com.dredom.box;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Node used in generating permutations for box nesting problem.
 * @author andre
 *
 */
public class Node {
	Square value;
	Node parent;
	Deque<Node> children;
	public Node(Square value, Node parent) {
		this.value = value;
		this.parent = parent;
	}
	public void addChild(Node child) {
		if (children == null) {
			children = new ArrayDeque<Node>();
		}
		children.push(child);
	}
	public final Square getValue() {
		return value;
	}
	public final Node getParent() {
		return parent;
	}
	public final Deque<Node> getChildren() {
		return children;
	}
	@Override
	public String toString() {
		return "[" + value + ",P:" + parent + "," + (children == null ? 0 : children.size()) + "]";
	}
}
