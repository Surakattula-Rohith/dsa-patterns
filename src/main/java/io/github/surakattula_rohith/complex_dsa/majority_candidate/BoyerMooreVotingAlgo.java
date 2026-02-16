package io.github.surakattula_rohith.complex_dsa.majority_candidate;

public class BoyerMooreVotingAlgo {
    /**
     * LeetCode 169 – Majority Element
     * <p>
     * Boyer–Moore Voting Algorithm
     * <p>
     * Problem Constraint:
     * -------------------
     * The majority element is guaranteed to exist.
     * It appears strictly more than ⌊ n / 2 ⌋ times.
     * <p>
     * Core Insight:
     * ------------
     * Since the majority element appears more than half the time,
     * all other elements combined appear less than half the time.
     * <p>
     * If we "pair cancel" one occurrence of the majority element
     * with one occurrence of a different element,
     * the majority element will still remain in surplus.
     * <p>
     * Mathematically:
     * <p>
     * Let:
     * M = frequency of majority element
     * R = frequency of all other elements combined
     * <p>
     * Since M > n/2 and n = M + R
     * ⇒ M > R
     * <p>
     * Therefore:
     * Even after cancelling M with R,
     * at least (M - R) > 0 copies remain.
     * <p>
     * The algorithm simulates this cancellation process.
     * <p>
     * We maintain:
     * candidate → current potential majority element
     * count     → net support after cancellations
     * <p>
     * Invariant:
     * ----------
     * count represents:
     * (# occurrences of candidate)
     * minus
     * (# occurrences of non-candidate elements)
     * within the processed prefix.
     * <p>
     * Whenever count becomes zero,
     * it means the previous candidate has been fully cancelled out.
     * So we reset and choose the next element as candidate.
     * <p>
     * Since the true majority can never be fully cancelled,
     * it will survive as the final candidate.
     * <p>
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     */
    public int majorityElement(int[] nums) {

        int candidate = 0;
        int count = 0;

        /**
         * Iterate through the array once.
         */
        for (int num : nums) {

            /**
             * If count is zero,
             * it means there is currently no active candidate.
             *
             * We select the current element as a new candidate.
             *
             * Conceptually:
             * All previous elements were perfectly cancelled out.
             */
            if (count == 0) {
                candidate = num;
            }

            /**
             * If current number equals candidate:
             *   → It supports the candidate.
             *   → Increase count.
             *
             * Else:
             *   → It opposes the candidate.
             *   → Perform one cancellation (decrement count).
             *
             * This simulates pair elimination.
             */
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        /**
         * Because the problem guarantees that a majority exists,
         * the remaining candidate after all cancellations
         * must be the majority element.
         */
        return candidate;
    }
}
