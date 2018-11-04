package com.park.textformatting.pedro;

import java.util.Arrays;

/**
 * TextFormatUtils is a collection of static methods for solving a Text Formatting Problem using Bottom-Up Dynamic Programming.
 * @author Pedro Luis Rivera
 */
public class TextFormatUtils {

    private static final int DEF_POWER = 2;

    /**
     * This method returns a paragraph whose lines are formated such that the square of the empty spaces is minimized.
     * @param paragraph single-line paragraph string (does not contain \n).
     * @param maxWidth max width of a line.
     * @return paragraph where the square of the empty spaces is minimized on each line.
     */
    public static String format(String paragraph, int maxWidth) {
        if(paragraph.contains("\n")) {
            throw new IllegalArgumentException("ERROR: Paragraph must be given in a single line (WITHOUT \n).");
        }
        String[] words = paragraph.split(" "); // Splitting the input paragraph
        for(int i = 0; i < words.length; i++) {
            if(words[i].length() > maxWidth) {
                throw new IllegalArgumentException("ERROR: Every word's length must be less or equal to maxWidth = "
                        + maxWidth + ". The word \"" + words[i] + "\" has a length of " + words[i].length() + ".");
            }
        }

        Integer[] solutions = new Integer[words.length]; // One solution for each word...
        solutions[words.length - 1] = words.length - 1; // The last word fits in a single line. The next line is after itself.

        Integer[] costs = new Integer[words.length]; // Each sub-problem has a corresponding cost...
        costs[words.length - 1] = 0; // The last word fits on a single line...

        for(int i = words.length - 2; i >= 0; i--) { // Since we added the solution for a single word, lets start from bottom up with the second...
            solveSingleLine(words, maxWidth, i, solutions, costs);
        }
        System.out.println("Solutions: " + Arrays.toString(solutions));
        System.out.println("Costs: " + Arrays.toString(costs));
        return getFormattedString(words, solutions);
    }

    /**
     * This method solves a single line based on
     * @param words word array that represents a single-line paragraph.
     * @param maxWidth max width of a line.
     * @param startingWordIndex index of the starting word for the current line.
     * @param solutions previously computed-solutions.
     * @param costs previously computed-costs for a corresponding solution on a sub-problem.
     */
    private static void solveSingleLine(String[] words, int maxWidth, int startingWordIndex, Integer[] solutions, Integer[] costs) {
        int end = startingWordIndex; // Initially, we assume only a word fits on the line.
        int minCost = Integer.MAX_VALUE;
        int minCostWordIndex = Integer.MAX_VALUE;
        if(width(words, startingWordIndex, words.length - 1) < maxWidth) { // If fits in a single line, cost = 0.
            minCost = 0;
            minCostWordIndex = words.length - 1;
        }
        else {
            while(width(words, startingWordIndex, end) <= maxWidth && end <= words.length - 1) { // Else, its cost will be the minimum (words that can fit in a single line + cost(next line)).
                if((Math.pow(maxWidth - width(words, startingWordIndex, end), DEF_POWER) + costs[end + 1]) < minCost) {
                    minCost = (int) (Math.pow(maxWidth - width(words, startingWordIndex, end), DEF_POWER)) + costs[end + 1];
                    minCostWordIndex = end;
                }
                end++;
            }
        }
        costs[startingWordIndex] = minCost;
        solutions[startingWordIndex] = minCostWordIndex;
    }

    /**
     * This method returns the width of a line that contains all the words contained in [i, j].
     * @param words word array that represents a single-line paragraph.
     * @param i first word index
     * @param j last word index.
     * @return width of a line that contains words contained in [i, j].
     */
    private static int width(String[] words, int i, int j) {
        // Validating indexes...
        if(i < 0 || j >= words.length) {
            throw new IllegalArgumentException("ERROR: Indexes i and j must be within [0, "
                    + (words.length - 1) + "] (words.length - 1). Input: i = " + i + " j = " + j + ".");
        }
        else if (j < i) {
            throw new IllegalArgumentException("ERROR: Invalid indexes, i must be less or equal to j.");
        }
        int width = j - i;
        while(i <= j){
            width += words[i++].length();
        }
        return width;
    }

    /**
     * This method converts an array of words into a paragraph where empty spaces are minimized.
     * @param words words word array that represents a single-line paragraph.
     * @param solutions previously computed-solutions.
     * @return formatted solution.
     */
    private static String getFormattedString(String[] words, Integer[] solutions) { // Not Working...
        String result = "";
        int i = 0; // First word...
        while(i < words.length) {
            result += words[i]; // Add the first word...
            for(int j = i + 1; j <= solutions[i]; j++){ // i is the starting word for that line, and solutions[i] is the last...
                result += " " + words[j];
            }
            result += "\n";
            i = solutions[i] + 1;
        }
        return result;
    }

}
