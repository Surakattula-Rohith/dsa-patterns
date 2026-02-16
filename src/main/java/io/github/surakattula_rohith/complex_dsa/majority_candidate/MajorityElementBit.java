package io.github.surakattula_rohith.complex_dsa.majority_candidate;

public class MajorityElementBit {

    /**
     * Bit Manipulation Approach
     *
     * Idea:
     * -----
     * Instead of cancelling elements (Moore's algorithm),
     * we reconstruct the majority number bit-by-bit.
     *
     * For each bit position from 0 to 31:
     *   - Count how many numbers have that bit set.
     *   - If count > n/2, set that bit in the answer.
     *
     * Why this works:
     * ----------------
     * Since the majority element appears more than half the time,
     * for every bit that is set in the majority element,
     * that bit will also appear in more than half of the numbers.
     *
     * Thus we can recover the majority element uniquely.
     *
     * Time Complexity  : O(32 * n) ≈ O(n)
     * Space Complexity : O(1)
     */
    public int majorityElement(int[] nums) {

        int n = nums.length;
        int result = 0;

        /**
         * Iterate through all 32 bit positions
         */
        for (int bit = 0; bit < 32; bit++) {

            int count = 0;

            /**
             * Count how many numbers have this bit set
             */
            for (int num : nums) {
                if ((num & (1 << bit)) != 0) {
                    count++;
                }
            }

            /**
             * If more than half the numbers have this bit set,
             * then majority element must also have this bit set.
             */
            if (count > n / 2) {
                result |= (1 << bit);
            }
        }

        return result;
    }
}

