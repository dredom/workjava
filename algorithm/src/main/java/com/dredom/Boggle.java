package com.dredom;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    static String dictionaryString = "ALE APE BE BEND PAN LEAN CAP COPE A LAB LEAF LEAP FOOL NACK";
    static Set<String> dictionary;
    static Set<String> partialWords;

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
	    initializeDictionary();
	}


    static void printGrid() {
	    for (char[] row : grid) {
	        out.printf(" %s \n", String.valueOf(row));
	    }
	}

	/**
	 * Every letter in the grid is a starting node.
	 * Stop at first word in each route - ignore longer words.
	 * @param grid
	 * @return words in grid
	 */
	public String[] process() {
	    List<String> result = new ArrayList<>();
	    for (int x = 0; x < grid.length; x++) {
	        for (int y = 0; y < grid[x].length; y++) {
	            String route = "";
	            boolean[][] covered = new boolean[4][4];
	            List<String> r = process(x, y, covered, route);
	            if (r != null) {
	                result.addAll(r);
	            }
	        }
	    }
	    return result.toArray(new String[result.size()]);

	}

	/**
	 * FIXME Bug with 'covered' array - false positives when travelling a route
	 * because it is shared by other travellers. Each travel should have its own.
	 */
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
	    ArrayList<String> out = new ArrayList<>();
        if (isWord(route)) {
            out.add(route);
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
	    return dictionary.contains(sequence);
	}
	private boolean isPartOfWord(String sequence) {
	    return partialWords.contains(sequence);
	}



    static void initializeDictionary() {
        String[] words = dictionaryString.split(" ");
        dictionary = new HashSet<>(words.length);
        for (String word : words) {
            dictionary.add(word);
        }
        int factor = dictionary.size() * dictionaryString.length() / dictionary.size();
        partialWords = new HashSet<>(factor, .25f);
        dictionary.forEach( word -> {
            for (int i = 0; i <= word.length(); i++) {
                partialWords.add(word.substring(0, i));
            }
        } );
    }

}
