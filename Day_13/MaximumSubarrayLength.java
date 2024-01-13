// Problem Statement:
// Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

// Example 1:

// Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
// Output: 3
// Explanation: The repeated subarray with maximum length is [3,2,1].
// Example 2:

// Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
// Output: 5
// Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
 
// Constraints:

// 1 <= nums1.length, nums2.length <= 1000
// 0 <= nums1[i], nums2[i] <= 100

// Solution:

class MaximumSubarrayLength {

  //Calculates the length of the longest common substring between two arrays.

    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int ans = 0;

        // 2D array to store lengths of common substrings
        int[][] dp = new int[m + 1][n + 1];

        // Dynamic programming approach to find common substrings
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }

                // Update the maximum length of common substring
                if (dp[i][j] > ans) {
                    ans = dp[i][j];
                }
            }
        }

        return ans;
    }
}
