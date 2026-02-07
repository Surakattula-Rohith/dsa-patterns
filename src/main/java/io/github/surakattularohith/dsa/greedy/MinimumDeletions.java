package io.github.surakattularohith.dsa.greedy;

public class MinimumDeletions {
    /**
     * Minimum Deletions to Make String Balanced - LeetCode Problem 1653
     *
     * Greedy + Prefix Counting approach.
     *
     * A string is considered balanced if:
     *      there is no pair (i < j) such that s[i] = 'b' and s[j] = 'a'
     *
     * In other words:
     *      all 'a's must appear before any 'b'
     *
     * Key observation:
     * While scanning the string from left to right, at every position we have
     * two choices when we encounter an 'a':
     *
     * 1) Delete this 'a'
     *    - cost increases by 1
     * 2) Delete all previous 'b's
     *    - cost equals number of 'b's seen so far
     *
     * We always choose the minimum of these two options.
     *
     * Approach:
     * 1) Traverse the string from left to right
     * 2) Maintain:
     *    - bCount: number of 'b's seen so far
     *    - ans: minimum deletions required up to current position
     * 3) If current character is:
     *    - 'b': increment bCount
     *    - 'a': update ans = min(ans + 1, bCount)
     *
     * This ensures the prefix remains balanced with minimum deletions.
     *
     * Answer:
     *      ans after processing the full string
     *
     * Time Complexity:
     *      O(n)  -> single pass through the string
     *
     * Space Complexity:
     *      O(1)  -> constant extra space
     */
    public int minimumDeletions(String s) {
        int bCount = 0;
        int ans = 0;

        for (char c : s.toCharArray()) {
            if (c == 'b') {
                bCount++;
            } else {
                ans = Math.min(ans + 1, bCount);
            }
        }
        return ans;
    }
}
