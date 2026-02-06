package io.github.surakattularohith.complex_dsa.slidingWindow_sorting;

import java.util.Arrays;

public class MinElementsToRemove {
    /**
     * Minimum Removals to Balance Array
     *
     * Sliding window + sorting approach.
     *
     * An array is considered balanced if:
     *      max <= min * k
     *
     * Key observation:
     * After sorting the array, any valid balanced array must correspond to a
     * contiguous subarray in the sorted order.
     *
     * Therefore, instead of deciding which elements to remove,
     * we try to find the longest subarray [l ... r] such that:
     *
     *      nums[r] <= nums[l] * k
     *
     * All elements outside this window can be removed.
     *
     * Approach:
     * 1) Sort the array
     * 2) Use two pointers (sliding window)
     *    - l represents the minimum element
     *    - r represents the maximum element
     * 3) For each l, expand r as far as possible while the balance condition holds
     * 4) Track the maximum window size
     *
     * Answer:
     *      total elements - maximum valid window size
     *
     * Time Complexity:
     *      O(n log n)  -> sorting
     *      O(n)        -> sliding window
     *
     * Space Complexity:
     *      O(1) extra space (ignoring sort stack)
     */
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        int maxWindow = 1;
        int r = 0;

        for (int l = 0; l < n; l++) {
            while (r < n && (long) nums[r] <= (long) nums[l] * k) {
                r++;
            }

            maxWindow = Math.max(maxWindow, r - l);
        }

        return n - maxWindow;
    }
}
