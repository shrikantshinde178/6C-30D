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
import java.util.*;
import java.lang.*;
import java.lang.Math;

class Solution {
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        // Initialize the maximum possible value
        int maxPossibleValue = Integer.MAX_VALUE;

        // Iterate over possible values
        for (int i = 1; i <= Integer.MAX_VALUE; i++) {
            // Check if it's possible to create arrays with the given conditions
            if (isPossible(divisor1, divisor2, uniqueCnt1, uniqueCnt2, i)) {
                // Update the maximum possible value
                maxPossibleValue = i;
                break;
            }
        }

        // Return the minimum maximum value
        return maxPossibleValue;
    }

    // Helper method 
    private boolean isPossible(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2, int maxPossibleValue) {
        // Counters for the number of elements added to each array
        int count1 = 0, count2 = 0;

        // Iterate up to the maximum possible value
        for (int i = 1; i <= maxPossibleValue; i++) {
            if (i % divisor1 != 0 && count1 < uniqueCnt1) {
                count1++;
            }
            else if (i % divisor2 != 0 && count2 < uniqueCnt2) {
                count2++;
            }
        }

        // Check required counts
        return count1 == uniqueCnt1 && count2 == uniqueCnt2;
    }
}
