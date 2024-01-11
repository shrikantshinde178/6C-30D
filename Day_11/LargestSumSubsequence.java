// Problem Statement:
// You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.

// Return any such subsequence as an integer array of length k.

// A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

// Example 1:
// Input: nums = [2,1,3,3], k = 2
// Output: [3,3]
// Explanation:
// The subsequence has the largest sum of 3 + 3 = 6.

// Example 2:
// Input: nums = [-1,-2,3,4], k = 3
// Output: [-1,3,4]
// Explanation: 
// The subsequence has the largest sum of -1 + 3 + 4 = 6.

// Example 3:
// Input: nums = [3,4,3,3], k = 2
// Output: [3,4]
// Explanation:
// The subsequence has the largest sum of 3 + 4 = 7. 
// Another possible subsequence is [4, 3].
 
// Constraints:

// 1 <= nums.length <= 1000
// -105 <= nums[i] <= 105
// 1 <= k <= nums.length

// Solution
public class LargestSumSubsequence {

    public int[] maxSubsequence(int[] nums, int k) {
        // Map to store the indices of each element in the original array
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        
        // Populate the map with indices of each element in the array
        for (int i = 0; i < nums.length; ++i) {
            map.computeIfAbsent(nums[i], key -> new LinkedList<>()).offer(i);
        }

        // Sort the array in ascending order
        Arrays.sort(nums);

        // Array to store the result - the maximum subsequence of length k
        int[] result = new int[k];

        // Fill the result array with the largest k elements from the sorted array
        for (int i = nums.length - 1, j = 0; i > nums.length - k - 1; --i, ++j) {
            result[j] = nums[i];
        }

        // Temporary array to reconstruct the original order of the maximum subsequence
        Integer[] temp = new Integer[nums.length];

        // Retrieve the original indices from the map and reconstruct the subsequence
        for (int i = 0; i < k; ++i) {
            temp[map.get(result[i]).poll()] = result[i];
        }

        // Fill the result array with the reconstructed subsequence
        for (int i = 0, j = 0; i < nums.length; ++i) {
            if (temp[i] != null) {
                result[j] = temp[i];
                ++j;
            }
        }

        return result;
    }
}
