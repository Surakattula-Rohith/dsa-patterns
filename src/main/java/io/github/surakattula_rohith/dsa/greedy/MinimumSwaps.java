package io.github.surakattula_rohith.dsa.greedy;

public class MinimumSwaps {

    /**
     * LeetCode 1536
     * Minimum Swaps to Arrange a Binary Grid
     *
     * Greedy + Adjacent Swap Simulation
     *
     * Observation:
     * For row i (0-indexed), we need at least (n - i - 1) trailing zeros
     * so that all elements above the main diagonal are zero.
     *
     * Strategy:
     * 1. Count trailing zeros for every row.
     * 2. For each row position i:
     *      - Find the first row j >= i with
     *            trailingZeros[j] >= (n - i - 1)
     *      - If not found → return -1
     *      - Bubble that row up to position i
     *        (each upward move = 1 adjacent swap)
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public int minSwaps(int[][] grid) {

        int n = grid.length;
        int[] trailingZeros = new int[n];

        // Step 1: Count trailing zeros for each row
        for (int i = 0; i < n; i++) {
            int count = 0;

            for (int j = n - 1; j >= 0 && grid[i][j] == 0; j--) {
                count++;
            }

            trailingZeros[i] = count;
        }

        int swaps = 0;

        // Step 2: Greedy placement
        for (int i = 0; i < n; i++) {

            int requiredZeros = n - i - 1;
            int candidateRow = i;

            // Find the first row below with enough trailing zeros
            while (candidateRow < n && trailingZeros[candidateRow] < requiredZeros) {
                candidateRow++;
            }

            // If no such row exists → impossible
            if (candidateRow == n) {
                return -1;
            }

            // Bubble the row up using adjacent swaps
            while (candidateRow > i) {
                int temp = trailingZeros[candidateRow];
                trailingZeros[candidateRow] = trailingZeros[candidateRow - 1];
                trailingZeros[candidateRow - 1] = temp;

                candidateRow--;
                swaps++;
            }
        }

        return swaps;
    }
}