// Problem Statement:
// You are given a 0-indexed integer array nums containing n distinct positive integers. A permutation of nums is called special if:

// For all indexes 0 <= i < n - 1, either nums[i] % nums[i+1] == 0 or nums[i+1] % nums[i] == 0.
// Return the total number of special permutations. As the answer could be large, return it modulo 109 + 7.

// Example 1:

// Input: nums = [2,3,6]
// Output: 2
// Explanation: [3,6,2] and [2,6,3] are the two special permutations of nums.
// Example 2:

// Input: nums = [1,4,3]
// Output: 2
// Explanation: [3,1,4] and [4,1,3] are the two special permutations of nums.

// Constraints:

// 2 <= nums.length <= 14
// 1 <= nums[i] <= 109

// Hint 1
// Can we solve this problem using DP with bit masking?
// Hint 2
// You just need two states in DP which are last_ind in the permutation and the mask of numbers already used.
  
// Solution:

class PermutationProblem {
    int MOD = 1000000007;
    int endMask;
    int[] nums;
    Integer[][] dp;
    private int solve(int mask, int last) {
        if(mask == endMask) {
            return 1;
        }

        //Dynamic programming state check
        if(this.dp[mask][last] != null) {
            return this.dp[mask][last];
        }

        int ans = 0;
        for(int i = 0; i < nums.length; i++) {
            //If the index is not already considered, (Checking using bitmasking)
            if(((1 << i) & mask) == 0) {

                //If the number satisfy the condition of special permutation
                if(nums[i] % nums[last] == 0 || nums[last] % nums[i] == 0) {

                    //Recursively checking for all other elements.
                    ans = ((ans % MOD) + (solve((mask ^ (1 << i)), i) % MOD)) % MOD;
                }
            }
        }

        //Caching and returning the result
        return this.dp[mask][last] = ans;
    }
    public int specialPerm(int[] nums) {

        //Initialization
        this.nums = nums;
        this.endMask = ((int)(Math.pow(2, nums.length)))-1;
        this.dp = new Integer[this.endMask][nums.length];
        int ans = 0;

        //Finding permutation for every possible permutation
        for(int i = 0; i < nums.length; i++) {
            ans = ((ans % MOD) + (solve((1 << i), i)) % MOD) % MOD;
        }

        return ans;
    }
}
