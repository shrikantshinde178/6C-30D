// Problem Statement:
// You are given an array of non-negative integers nums and an integer k. In one operation, you may choose any element from nums and increment it by 1.

// Return the maximum product of nums after at most k operations. Since the answer may be very large, return it modulo 109 + 7. Note that you should maximize the product before taking the modulo. 

// Example 1:

// Input: nums = [0,4], k = 5
// Output: 20
// Explanation: Increment the first number 5 times.
// Now nums = [5, 4], with a product of 5 * 4 = 20.
// It can be shown that 20 is maximum product possible, so we return 20.
// Note that there may be other ways to increment nums to have the maximum product.
// Example 2:

// Input: nums = [6,3,3,2], k = 2
// Output: 216
// Explanation: Increment the second number 1 time and increment the fourth number 1 time.
// Now nums = [6, 4, 3, 3], with a product of 6 * 4 * 3 * 3 = 216.
// It can be shown that 216 is maximum product possible, so we return 216.
// Note that there may be other ways to increment nums to have the maximum product.
 
// Constraints:

// 1 <= nums.length, k <= 105
// 0 <= nums[i] <= 106

// Hint 1
// If you can increment only once, which number should you increment?
// Hint 2
// We should always prioritize the smallest number. What kind of data structure could we use?
// Hint 3
// Use a min heap to hold all the numbers. Each time we do an operation, replace the top of the heap x by x + 1.

// Solution:
/**
 * This class provides a solution for finding the maximum product of an array
 * by incrementing elements up to 'k' times. The solution sorts the array,
 * then iteratively increments the smallest element and adjusts the index.
 * After the modifications, it calculates the product of the updated array
 * and returns the result modulo 1_000_000_007.
 */
class MaximumProductIncrement {
    
    /**
     * Finds the maximum product of the array by incrementing elements up to 'k' times.
     *
     * @param nums An array of integers.
     * @param k    The number of increments allowed.
     * @return The maximum product modulo 1_000_000_007.
     */
    public int maximumProduct(int[] nums, int k) {
        Arrays.sort(nums);
        
        int index = 0;
        // Increment 'k' smallest elements in the sorted array.
        while (k-- > 0) {
            nums[index]++;
            if (index + 1 < nums.length && nums[index] > nums[index + 1]) {
                index++;
            } else {
                index = 0;
            }
        }

        // Calculate the product of the updated array.
        long product = nums[0];
        for (int i = 1; i < nums.length; i++) {
            product *= nums[i];
            product = product % 1_000_000_007;
        }

        return (int) product;
    }
}
