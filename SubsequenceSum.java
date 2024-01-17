// Problem Statement:
// Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

// A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

// Example 1:

// Input: nums = [10,2,-10,5,20], k = 2
// Output: 37
// Explanation: The subsequence is [10, 2, 5, 20].
// Example 2:

// Input: nums = [-1,-2,-3], k = 1
// Output: -1
// Explanation: The subsequence must be non-empty, so we choose the largest number.
// Example 3:

// Input: nums = [10,-2,-10,-5,20], k = 2
// Output: 23
// Explanation: The subsequence is [10, -2, -5, 20].

// Constraints:

// 1 <= k <= nums.length <= 105
// -104 <= nums[i] <= 104

// Solution:

class SubsequenceSum {

    public int constrainedSubsetSum(int[] nums, int k) {
        // Using a deque to efficiently track the potential candidates for the subsequence sum.
        Deque<Integer> dq = new ArrayDeque<>();

        // Iterating through the array to calculate the constrained subsequence sum.
        for (int i = 0; i < nums.length; i++) {
            // Adding the current element to the sum of the previous subsequence (if any).
            nums[i] += (dq.isEmpty()) ? 0 : nums[dq.peekFirst()];

            // Removing elements from the deque that are not potential candidates for the subsequence sum.
            while (!dq.isEmpty() && ((nums[i] >= nums[dq.peekLast()]) || (i - dq.peekFirst() >= k))) {
                if (nums[i] >= nums[dq.peekLast()]) {
                    dq.pollLast();
                } else {
                    dq.pollFirst();
                }
            }

            // Adding the current index to the deque if the sum of the subsequence is positive.
            if (nums[i] > 0) {
                dq.offerLast(i);
            }
        }

        // Finding the maximum sum from the calculated subsequence sums.
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(nums[i], ans);
        }

        return ans;
    }
}
