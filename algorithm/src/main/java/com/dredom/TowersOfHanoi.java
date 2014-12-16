package com.dredom;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *   -!-       !        !
 *  --!--      !        !
 * ---!---     !        !
 * =======  =======  =======
 * <p>
 * Move rings from 1st to 3rd. One at a time.
 * The object is to move the n rings from Post A to Post C by successively moving a
 * ring from one post to another post that is empty or has a larger diameter ring on top.
 *
 * @author andre
 * @version 1, 2014-12
 */
public class TowersOfHanoi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 3;
		TowersOfHanoi inst = new TowersOfHanoi();
		inst.init(size);
		inst.move();

	}


	/**
	 * Ring class to help us confirm no illegal moves.
	 */
	class Ring implements Comparable<Ring> {
		public int size;
		public Ring(int size) {
			this.size = size;
		}
		public boolean lt(Ring o) {
			return compareTo(o) > 0;
		}
		public int compareTo(Ring o) {
			if (o == null) {
				return 1;
			}
			if (o.size < this.size) {
				return -1;
			}
			if (o.size > this.size) {
				return 1;
			}
			return 0;
		}
		@Override
		public String toString() {
			return Integer.toString(size);
		}
	}

	final Deque<Ring> postA = new ArrayDeque<Ring>();
	final Deque<Ring> postB = new ArrayDeque<Ring>();
	final Deque<Ring> postC = new ArrayDeque<Ring>();

	public void move() {
		printPosts();
		int count = postA.size();
		moveRings(count, postA, postB, postC);
		printPosts();
		printPost(postC);

	}

	/**
	 * Recursive formula for the moves.
	 * @param count
	 * @param from
	 * @param aux
	 * @param to
	 */
	void moveRings(int count, Deque<Ring> from, Deque<Ring> aux, Deque<Ring> to) {
		if (count == 1) {
			moveFrom(from, to);
		} else {
			moveRings(count - 1, from, to, aux);
			moveRings(1, from, aux, to);
			moveRings(count - 1, aux, from, to);
		}

	}

	void printPosts() {
		out.printf("\t%2d \t%2d \t%2d \n", postA.size(), postB.size(), postC.size());
	}
	void printPost(Deque<Ring> post) {
		while (post.peek() != null) {
			out.printf(" %s", post.pop());
		}
		out.println();
	}

	private void moveFrom(Deque<Ring> postx, Deque<Ring> posty) {
		if (!canMove(postx, posty)) {
			throw new RuntimeException("Cannot move " + postx + " to " + posty);
		}
		Ring ringx = postx.pop();
		posty.push(ringx);
	}

	private boolean canMove(Deque<Ring> postx, Deque<Ring> posty) {
		Ring ringA = postx.peek();
		if (ringA == null) {
			return false;
		}
		Ring ringy = posty.peek();
		if (ringy == null) {
			return true;
		}
		return ringA.lt(ringy);
	}


	void init(int size) {
		for (int i = size; i > 0; i--) {
			postA.push(new Ring(i));
		}

	}
}
