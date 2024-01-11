// Problem Statement:
// Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

// answer[i] % answer[j] == 0, or
// answer[j] % answer[i] == 0
// If there are multiple solutions, return any of them.

// Example 1:

// Input: nums = [1,2,3]
// Output: [1,2]
// Explanation: [1,3] is also accepted.
// Example 2:

// Input: nums = [1,2,4,8]
// Output: [1,2,4,8]

// Constraints:

// 1 <= nums.length <= 1000
// 1 <= nums[i] <= 2 * 109
// All the integers in nums are unique.

// Solution
public class LargestDivisibleSubset {

    // Function to find the largest divisible subset and return it as a list
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // Sort the array to simplify the subset finding process
        Arrays.sort(nums);
        return findLargestDivisibleSubset(nums);
    }

    // Helper function to find the largest divisible subset given a sorted array
    public static List<Integer> findLargestDivisibleSubset(int[] nums) {
        int len = nums.length;
        int maxSize = 1; // Size of the largest divisible subset
        int lastIndex = 0; // Index of the last element in the largest divisible subset

        int[] dp = new int[len]; // Array to store the size of the divisible subset ending at each index
        Arrays.fill(dp, 1);

        int[] hash = new int[len]; // Array to store the previous index in the divisible subset

        for (int i = 0; i < len; i++) {
            hash[i] = i;

            // Check for divisibility with previous elements in the array
            for (int prev = 0; prev < i; prev++) {
                if (nums[i] % nums[prev] == 0 && 1 + dp[prev] > dp[i]) {
                    dp[i] = 1 + dp[prev];
                    hash[i] = prev;
                }
            }

            // Update information about the largest divisible subset
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                lastIndex = i;
            }
        }

        // Reconstruct the largest divisible subset using the 'hash' array
        List<Integer> subset = new ArrayList<>();
        subset.add(nums[lastIndex]);

        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            subset.add(nums[lastIndex]);
        }

        Collections.reverse(subset); // Reverse the list to get the correct order
        return subset;
    }
}
