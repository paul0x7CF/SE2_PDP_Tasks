package task.logic;

import java.util.Arrays;

public class SearchAlgorithm {

    /**
     * Calculates the distance between two strings using the Levenshtein algo written from Chat-Gpt.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The Levenshtein distance between the two strings.
     */
    public static int calculateLevenshteinDistance(String s1, String s2) {
        // Create a matrix to store the distances between prefixes of the two strings
        int[][] distanceMatrix = new int[s1.length() + 1][s2.length() + 1];

        // Initialize the matrix with base cases
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    distanceMatrix[i][j] = j;  // Initialization for the first string
                } else if (j == 0) {
                    distanceMatrix[i][j] = i;  // Initialization for the second string
                } else {
                    // Calculate the minimum cost of three possible operations: substitution, deletion, insertion
                    distanceMatrix[i][j] = min(
                            distanceMatrix[i - 1][j - 1] + costOfSubstitution(s1.charAt(i - 1), s2.charAt(j - 1)),
                            distanceMatrix[i - 1][j] + 1,
                            distanceMatrix[i][j - 1] + 1);
                }
            }
        }

        // The final value in the matrix represents the Levenshtein distance between the two strings
        return distanceMatrix[s1.length()][s2.length()];
    }

    /**
     * Calculates the cost of substituting one character with another.
     *
     * @param a The first character.
     * @param b The second character.
     * @return The cost of substituting one character with another (0 if equal, 1 otherwise).
     */
    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    /**
     * Finds the minimum value among a set of numbers.
     *
     * @param numbers The set of numbers to find the minimum from.
     * @return The minimum value among the given numbers.
     */
    private static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
}
