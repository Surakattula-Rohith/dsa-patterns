package io.github.surakattula_rohith.complex_dsa.sorting_two_pointer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MakeLargestSpecial {
    /**
     * LeetCode 761 – Special Binary String (Make Largest Special)
     * <p>
     * Recursive Decomposition + Descending Sort
     * <p>
     * Problem Constraint:
     * -------------------
     * A special binary string has:
     *   1. Equal number of 0's and 1's.
     *   2. Every prefix has at least as many 1's as 0's.
     * <p>
     * We can swap any two consecutive, non-empty special substrings
     * any number of times.
     * Return the lexicographically largest result.
     * <p>
     * Core Insight:
     * ------------
     * A special binary string is structurally identical to
     * balanced parentheses: treat '1' as '(' and '0' as ')'.
     * <p>
     * Example: "11011000" → "(()(())) "
     * <p>
     * A top-level special substring is one where the running
     * balance (prefix count of 1's minus 0's) returns to zero
     * for the first time since the substring started.
     * <p>
     * Since we have unlimited swaps of adjacent special substrings,
     * we can rearrange them in any order (like bubble sort).
     * To maximize lexicographic order → sort them descending.
     * <p>
     * Every top-level special substring has the form:
     *   "1" + inner + "0"
     * <p>
     * The inner part is itself a concatenation of special substrings.
     * So we recursively apply the same logic to the inner part.
     * <p>
     * Algorithm:
     * ----------
     * 1. Walk through string with a counter (+1 for '1', -1 for '0').
     * 2. Whenever counter hits 0 → found one top-level special substring.
     * 3. Strip outer '1' and '0', recurse on the inner part.
     * 4. Wrap the recursed result back: "1" + recurse(inner) + "0".
     * 5. After all top-level substrings are collected, sort descending.
     * 6. Concatenate and return.
     * <p>
     * Time Complexity  : O(n^2) in the worst case (due to substring operations)
     * Space Complexity : O(n) for recursion stack and substring storage
     */
    public String makeLargestSpecial(String s) {

        List<String> topLevel = new ArrayList<>();
        int count = 0;
        int start = 0;

        /**
         * Iterate through the string once to find
         * all top-level special substrings.
         *
         * We use a running counter:
         *   '1' increments by 1 (like an opening parenthesis)
         *   '0' decrements by 1 (like a closing parenthesis)
         *
         * When count returns to 0, we've found one complete
         * top-level special substring from index 'start' to 'i'.
         */
        for (int i = 0; i < s.length(); i++) {

            count += s.charAt(i) == '1' ? 1 : -1;

            /**
             * count == 0 means:
             * The substring s[start..i] has equal 1's and 0's,
             * and never dipped below zero during traversal
             * (guaranteed by the special string property).
             *
             * This substring has the structure: "1" + inner + "0"
             *
             * We strip the outer '1' (at index start) and '0' (at index i),
             * recursively process the inner part s[start+1 .. i-1],
             * then wrap it back with "1" and "0".
             *
             * Recursion ensures that even the inner special substrings
             * are rearranged optimally before we sort at this level.
             */
            if (count == 0) {
                String inner = makeLargestSpecial(s.substring(start + 1, i));
                topLevel.add("1" + inner + "0");
                start = i + 1;
            }
        }

        /**
         * After recursion, each top-level substring is in its
         * best possible form.
         *
         * Since unlimited swaps of adjacent special substrings
         * allow any permutation, we sort them in descending
         * lexicographic order to maximize the final string.
         *
         * Example:
         *   ["10", "1100"] → sorted descending → ["1100", "10"]
         *   Concatenated: "110010"
         */
        Collections.sort(topLevel, Collections.reverseOrder());

        /**
         * Concatenate all sorted top-level substrings
         * to form the lexicographically largest result.
         */
        return String.join("", topLevel);
    }
}