package io.github.surakattula_rohith.dsa.sliding_window;

import java.util.HashSet;
import java.util.Set;

/**
 * Longest Balanced Subarray - LeetCode 3719
 *
 * A subarray is called balanced if:
 *   number of DISTINCT even values
 *   == number of DISTINCT odd values
 *
 * Key Observation:
 * --------------------------------------------------
 * This is NOT a classic sliding window problem.
 *
 * Reason:
 * - The condition depends on DISTINCT counts.
 * - Removing an element from the left may or may not
 *   reduce the distinct count (if that value appears later).
 * - Hence, the condition is NOT monotonic.
 *
 * Because of this, we fix the starting index and
 * expand the ending index while reusing information.
 *
 * Approach:
 * --------------------------------------------------
 * For every starting index i:
 *   - Expand j from i to n-1
 *   - Maintain:
 *       * a set of distinct even numbers
 *       * a set of distinct odd numbers
 *   - Whenever both sets have equal size,
 *     update the maximum length.
 *
 * Time Complexity:
 * --------------------------------------------------
 * O(n^2)
 * - n choices for starting index
 * - n expansions for ending index
 *
 * Space Complexity:
 * --------------------------------------------------
 * O(n) for the sets
 */
public class LongestBalanced {

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        // Fix the starting index of the subarray
        for (int i = 0; i < n; i++) {

            // Sets to track DISTINCT even and odd values
            Set<Integer> evenSet = new HashSet<>();
            Set<Integer> oddSet = new HashSet<>();

            // Expand the ending index
            for (int j = i; j < n; j++) {
                int val = nums[j];

                if ((val & 1) == 0) {
                    evenSet.add(val);
                } else {
                    oddSet.add(val);
                }

                // Check if current subarray is balanced
                if (evenSet.size() == oddSet.size()) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }
}
