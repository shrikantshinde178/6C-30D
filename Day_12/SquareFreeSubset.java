// Problem Statement:
// You are given a positive integer 0-indexed array nums.

// A subset of the array nums is square-free if the product of its elements is a square-free integer.

// A square-free integer is an integer that is divisible by no square number other than 1.

// Return the number of square-free non-empty subsets of the array nums. Since the answer may be too large, return it modulo 109 + 7.

// A non-empty subset of nums is an array that can be obtained by deleting some (possibly none but not all) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.

// Example 1:

// Input: nums = [3,4,4,5]
// Output: 3
// Explanation: There are 3 square-free subsets in this example:
// - The subset consisting of the 0th element [3]. The product of its elements is 3, which is a square-free integer.
// - The subset consisting of the 3rd element [5]. The product of its elements is 5, which is a square-free integer.
// - The subset consisting of 0th and 3rd elements [3,5]. The product of its elements is 15, which is a square-free integer.
// It can be proven that there are no more than 3 square-free subsets in the given array.
// Example 2:

// Input: nums = [1]
// Output: 1
// Explanation: There is 1 square-free subset in this example:
// - The subset consisting of the 0th element [1]. The product of its elements is 1, which is a square-free integer.
// It can be proven that there is no more than 1 square-free subset in the given array.
 
// Constraints:

// 1 <= nums.length <= 1000
// 1 <= nums[i] <= 30

// Hint 1
// There are 10 primes before number 30.

// Hint 2
// Label primes from {2, 3, … 29} with {0,1, … 9} and let DP(i, mask) denote the number of subsets before index: i with the subset of taken primes: mask.

// Hint 3
// If the mask and prime factorization of nums[i] have a common prime, then it is impossible to add to the current subset, otherwise, it is possible.

// Solution

class SquareFreeSubset {
    int[] counts;
    int[] codes;
    long sum;

    // Calculates the number of square-free subsets in the given array.
    public int squareFreeSubsets(int[] nums) {
        counts = new int[31];
        
        // Count occurrences of square-free numbers in the array
        for (int num : nums) {
            if (isSquareFree(num)) {
                if (num == 1) {
                    counts[num] += (counts[num] + 1) % 1000000007;
                } else {
                    counts[num]++;
                }
            }
        }
        
        codes = new int[] { 0, 0, 1, 2, 1023, 4, 3, 8, 1023, 1023, 5,
                            16, 1023, 32, 9, 6, 1023, 64, 1023, 128, 1023,
                            10, 17, 256, 1023, 1023, 33, 1023, 1023, 512, 7 };
        
        // Use backtracking to calculate the total number of square-free subsets
        backtracking(30, 0, 0);
        
        return (int) sum;
    }
    // Recursive backtracking to explore subsets and count square-free subsets.
    private void backtracking(int position, int code, long count) {
        if (position == 0) {
            sum += count;
            sum %= 1000000007;
        } else {
            backtracking(position - 1, code, count);
            if (counts[position] != 0 && (code & codes[position]) == 0) {
                count = (count == 0) ? counts[position] : count * counts[position] % 1000000007;
                backtracking(position - 1, code | codes[position], count);
            }
        }
    }

   // Checks if a number is square-free.
    private boolean isSquareFree(int num) {
        return num % 4 != 0 && num % 9 != 0 && num % 16 != 0 && num % 25 != 0;
    }
}
