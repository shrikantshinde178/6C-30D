Problem Statement:
Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.

A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:

0 <= i, j < nums.length
i != j
|nums[i] - nums[j]| == k
Notice that |val| denotes the absolute value of val.

Example 1:

Input: nums = [3,1,4,1,5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:

Input: nums = [1,2,3,4,5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:

Input: nums = [1,3,1,5,4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
 

Constraints:

1 <= nums.length <= 104
-107 <= nums[i] <= 107
0 <= k <= 107

// Solution

  class KdiffSize {
     public int findPairs(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        
        // Sorting the array for efficient search
        Arrays.sort(nums);
        
        // Case when the target difference is 0
        if (k == 0) {
            int pre = 100000001;
            for (int i = 0; i < (n - 1); i++) {
                if ((nums[i] == nums[i + 1]) && pre != nums[i]) {
                    count++;
                }
                pre = nums[i];
            }
            return count;
        }
        
        // Case when the target difference is not 0
        int pre = 100000001;
        for (int i = 0; i < n; i++) {
            if (pre != nums[i]) {
                count = count + search(nums, i, k, n - 1);
                pre = nums[i];
            }
        }
        return count;
    }

   // Binary search for finding the target in the sorted array.
    public int search(int[] nums, int l, int k, int r) {
        int target = nums[l];
        int t = (target + k);
        
        while (l <= r) {
            int mid = (l + r) / 2;

            if (nums[mid] == t) {
                return 1;
            } else if (nums[mid] > t) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return 0;
    }
}
