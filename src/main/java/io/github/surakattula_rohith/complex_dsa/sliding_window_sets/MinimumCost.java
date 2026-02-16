package io.github.surakattula_rohith.complex_dsa.sliding_window_sets;

import java.util.*;

public class MinimumCost {

    /**
     * LeetCode problem 3013
     *
     * Sliding window approach.
     *
     * nums[0] always contributes to the answer, so we only need to choose (k-1)
     * additional starting points from the remaining elements.
     *
     * For each possible window of indices [i ... i + dist], we must pick the
     * (k-1) smallest values from this window to minimize the total cost.
     *
     * The main challenge is efficiently maintaining the (k-1) smallest elements
     * while sliding the window. Sorting each window would be too slow.
     *
     * To optimize this, we use two ordered sets:
     * 1) kMinimum  : stores the current (k-1) smallest elements in the window
     * 2) remaining : stores the rest of the elements in the window
     *
     * As the window slides:
     * - Insert the new element into kMinimum
     * - If kMinimum exceeds size (k-1), move the largest element to remaining
     * - Remove the element that falls out of the window and rebalance the sets
     *
     * At every step, the sum of elements in kMinimum represents the minimum
     * possible cost contribution for that window.
     */
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;

        /**
         * TreeSets store (value, index) pairs.
         * Sorting is done first by value, then by index to avoid collisions
         * between elements having the same value.
         */
        TreeSet<int[]> kMinimum = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        TreeSet<int[]> remaining = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        /** Sum of the (k-1) smallest elements in the current window */
        long sum = 0;

        int i = 1;

        /**
         * Build the initial window covering indices [1 ... dist + 1]
         */
        while (i < n && i - dist < 1) {
            int[] cur = new int[]{nums[i], i};
            kMinimum.add(cur);
            sum += nums[i];

            /**
             * Ensure kMinimum contains only the (k-1) smallest elements
             */
            if (kMinimum.size() > k - 1) {
                int[] largest = kMinimum.pollLast();
                sum -= largest[0];
                remaining.add(largest);
            }
            i++;
        }

        long result = Long.MAX_VALUE;

        /**
         * Slide the window across the array
         */
        while (i < n) {
            int[] cur = new int[]{nums[i], i};
            kMinimum.add(cur);
            sum += nums[i];

            /**
             * Rebalance if kMinimum exceeds size (k-1)
             */
            if (kMinimum.size() > k - 1) {
                int[] largest = kMinimum.pollLast();
                sum -= largest[0];
                remaining.add(largest);
            }

            /**
             * Update the minimum sum for the current window
             */
            result = Math.min(result, sum);

            /**
             * Remove the element that falls out of the window (index i - dist)
             */
            int remIdx = i - dist;
            int[] toRemove = new int[]{nums[remIdx], remIdx};

            if (kMinimum.remove(toRemove)) {
                sum -= nums[remIdx];

                /**
                 * Promote the smallest element from remaining to kMinimum
                 */
                if (!remaining.isEmpty()) {
                    int[] promote = remaining.pollFirst();
                    kMinimum.add(promote);
                    sum += promote[0];
                }
            } else {
                remaining.remove(toRemove);
            }

            i++;
        }

        /**
         * nums[0] always contributes to the total cost
         */
        return nums[0] + result;
    }
}
