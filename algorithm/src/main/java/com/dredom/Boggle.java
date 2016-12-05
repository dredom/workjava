package com.dredom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Boggle is a find-words game. You shake it up, then the die
 * fall into a 4 x 4 grid with a letter facing up.
 * Finder of the most words wins!
 * <p>Rules: Start anywhere, travel up, down, diagonal, but
 * cannot reuse any letter already used.
 * <p>
 * Input: 4 x 4 array of characters.
 * <pre>
 *   ABCD
 *   LEFG
 *   CAND
 *   KOPE
 *  </pre> -> ALE, BE, BEND, PAN, LEAN, CAP, etc
 *
 */
public class Boggle {
    static char[][] grid;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		init(args);
		printGrid();
		Boggle instance = new Boggle();
		String[] words = instance.process();
		System.out.println(Arrays.deepToString(words));
	}

	static void init(String[] rows) {
	    // validate
	    if (rows.length != 4) {
	        throw new IllegalArgumentException("Need exactly 4 rows");
	    }
	    for (String row : rows) {
	        if (row.length() != 4) {
	            throw new IllegalArgumentException("invalid length: " + row);
	        }
	    }
	    grid = new char[4][4];
	    for (int i = 0; i < rows.length; i++) {
	        String row = rows[i];
	        for (int j = 0; j < row.length(); j++) {
	            grid[i][j] = row.charAt(j);
	        }
	    }
	    initializeDictionary(grid);
	}

	private static void initializeDictionary(char[][] grid2) {
        // TODO Auto-generated method stub

    }

    static void printGrid() {
	    for (char[] row : grid) {
	        System.out.printf(" %s \n", String.valueOf(row));
	    }
	}

	/**
	 * Every letter in the grid is a starting node.
	 * Stop at first word in each route - ignore longer words.
	 * @param grid
	 * @return words in grid
	 */
	public String[] process() {
	    String route = "";
	    boolean[][] covered = new boolean[4][4];
	    int x = 0, y = 0;
	    List<String> result = process(x, y, covered, route);
	    if (result == null) {
	        return new String[0];
	    }
	    return result.toArray(new String[result.size()]);

	}

	private List<String> process(int x, int y, boolean covered[][], String route) {
	    if (x < 0 || x >= 4 || y < 0 || y >= 4) {
	        return null;
	    }
	    if (covered[x][y]) {
	        return null;
	    }
	    String val = String.valueOf(grid[x][y]);
	    route += val;
	    if (!isPartOfWord(route)) {
	        return null;
	    }
	    ArrayList<String> out = new ArrayList<String>();
        if (isWord(route)) {
            out.add(route);
            return out;
        }
	    covered[x][y] = true;
	    // iterate through all surrounding letters
	    for (int ix = -1; ix <= 1; ix++) {
	        for (int iy = -1; iy <= 1; iy++ ) {
	            List<String> result = process(x + ix, y + iy, covered, route);
	            if (result != null) {
	                out.addAll(result);
	            }
	        }
	    }
	    return out;
	}

	private boolean isWord(String sequence) {
	    if (sequence.equals("A")) {
	        return true;
	    }
	    return false;
	}
	private boolean isPartOfWord(String sequence) {
        if (sequence.equals("A")) {
            return true;
        }
        return false;
	}
}
