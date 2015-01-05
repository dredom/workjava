/**
 *
 */
package com.dredom.box;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Given a set of different sized boxes, find the box that will
 * contain the most other boxes and how many it contains.
 *
 * Do this for simple squares.
 *
 * @author andre
 *
 */
public class SquaresNester {

	public static void main(String[] args) {
		int[] T1 = {  3, 5, 2, 4 };
		out.println(Arrays.toString(T1));
		Deque<Square> list = new ArrayDeque<Square>();

		for (int i = 0; i < T1.length; i++) {
			list.add(new Square(T1[i]));

		}
		SquaresNester sn = new SquaresNester();
		Node n = sn.buildNode(list, null);
		out.println("Node=" + n);
		List<Node> leafs = sn.getLeafs(n);
		out.print("Leafs: ");
		for (Node leaf : leafs) {
			out.printf("%s, ", leaf.getValue());
		}
		out.println();
		out.println("Permutations:");
		for (Node leaf : leafs) {
			Deque<Square> permutation = sn.getAncestors(leaf);
			while (! permutation.isEmpty()) {
				out.printf(" %s >", permutation.pop());
			}
			out.println();
		}
	}

	/**
	 * All permutations of list. Recursive.
	 * @param list to do permutations
	 * @param parent
	 * @return Node with children permutations
	 */
	Node buildNode(Deque<Square> list, Node parent) {
		out.printf("buildNode: %s \n", Arrays.deepToString(list.toArray()));
		Square s = list.pop();
		Node ns = new Node(s, parent);
		for (int i = 0; i < list.size(); i++) {

			Square save = list.peek();
			Node child = buildNode(list, ns);
			list.add(save);

			ns.addChild(child);
		}
		return ns;
	}

	List<Node> getLeafs(Node root) {
		List<Node> leafs = new ArrayList<Node>();
		return getAllLeafs(root, leafs);
	}
	List<Node> getAllLeafs(Node node, List<Node> leafs) {
		if (node.children == null) {
			leafs.add(node);
			return leafs;
		}
		Deque<Node> children = node.getChildren();
		for (Node child : children) {
			getAllLeafs(child, leafs);
		}
		return leafs;
	}

	Deque<Square> getAncestors(Node leaf) {
		Deque<Square> ancestors = new ArrayDeque<Square>();
		Node node = leaf;
		do {
			ancestors.push(node.getValue());
			node = node.getParent();
		} while (node != null);
		return ancestors;
	}
}
