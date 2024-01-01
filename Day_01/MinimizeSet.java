// Problem Statement : 
// We have two arrays arr1 and arr2 which are initially empty. You need to add positive integers to them such that they satisfy all the following conditions:
// arr1 contains uniqueCnt1 distinct positive integers, each of which is not divisible by divisor1.
// arr2 contains uniqueCnt2 distinct positive integers, each of which is not divisible by divisor2.
// No integer is present in both arr1 and arr2.
// Given divisor1, divisor2, uniqueCnt1, and uniqueCnt2, return the minimum possible maximum integer that can be present in either array.

// Example:

// Input: divisor1 = 2, divisor2 = 7, uniqueCnt1 = 1, uniqueCnt2 = 3
// Output: 4

// Explanation: 
// We can distribute the first 4 natural numbers into arr1 and arr2.
// arr1 = [1] and arr2 = [2,3,4].
// We can see that both arrays satisfy all the conditions.
// Since the maximum value is 4, we return it.

// Constraints:
// 2 <= divisor1, divisor2 <= 105
// 1 <= uniqueCnt1, uniqueCnt2 < 109
// 2 <= uniqueCnt1 + uniqueCnt2 <= 109

//Solution 
class Solution {
    // Function to calculate the greatest common divisor 
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Function to find the minimum maximum value
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        // Initialize variables for binary search
        long l = 1, r = (int) (2 * 1e9);
        long ans = r;

        // Calculate the least common multiple of divisor1 and divisor2
        long lcm = (1L * divisor1 * divisor2) / gcd(divisor1, divisor2);

        // Binary search loop to find the minimum maximum value
        for (; l <= r;) {
            long mid = (l + r) >> 1;  // Calculate the midpoint
            long x = mid - mid / divisor1;  // Count of numbers not divisible by divisor1
            long y = mid - mid / divisor2;  // Count of numbers not divisible by divisor2
            long z = mid - mid / lcm;  // Count of numbers not divisible by the least common multiple

            // Check if the counts satisfy the conditions
            if (x < 1L * uniqueCnt1 || y < 1L * uniqueCnt2 || z < 1L * (uniqueCnt1 + uniqueCnt2)) {
                l = mid + 1;  // Adjust the search range
            } else {
                ans = Math.min(ans, mid);  // Update the minimum maximum value
                r = mid - 1;  // Adjust the search range
            }
        }

        // Return the minimum maximum value as an integer
        return (int) ans;
    }
}
