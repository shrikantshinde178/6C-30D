// Problem Statement:
// You are given two positive integers startPos and endPos. Initially, you are standing at position startPos on an infinite number line. With one step, you can move either one position to the left, or one position to the right.

// Given a positive integer k, return the number of different ways to reach the position endPos starting from startPos, such that you perform exactly k steps. Since the answer may be very large, return it modulo 109 + 7.

// Two ways are considered different if the order of the steps made is not exactly the same.

// Note that the number line includes negative integers.

// Example 1:

// Input: startPos = 1, endPos = 2, k = 3
// Output: 3
// Explanation: We can reach position 2 from 1 in exactly 3 steps in three ways:
// - 1 -> 2 -> 3 -> 2.
// - 1 -> 2 -> 1 -> 2.
// - 1 -> 0 -> 1 -> 2.
// It can be proven that no other way is possible, so we return 3.

// Example 2:

// Input: startPos = 2, endPos = 5, k = 10
// Output: 0
// Explanation: It is impossible to reach position 5 from position 2 in exactly 10 steps.
 
// Constraints:

// 1 <= startPos, endPos, k <= 1000

//Solution


class PositionReach {
    public int numberOfWays(int startPos, int endPos, int k) {
        // Initialize HashMap
        HashMap<Integer, HashMap<Integer, Integer>> memoMap = new HashMap<>();
        // Delegate calculation method
        return req(startPos, endPos, k, memoMap);
    }

    private int req(int currentPos, int targetPos, int remainingSteps, HashMap<Integer, HashMap<Integer, Integer>> memoMap) {
        // If no steps remaining, check if at the target position
        if (remainingSteps == 0) {
            return (currentPos == targetPos) ? 1 : 0;
        }

        // Initialize modulo constant
        int mod = 1000000007;

        // Check if current position is already memoized
        if (!memoMap.containsKey(currentPos)) {
            memoMap.put(currentPos, new HashMap<>());
            // Calculate and memoize the result for the current position and remaining steps
            memoMap.get(currentPos).put(remainingSteps, (
                    (req(currentPos + 1, targetPos, remainingSteps - 1, memoMap) % mod) +
                    (req(currentPos - 1, targetPos, remainingSteps - 1, memoMap) % mod)
            ) % mod);
        } else {
            if (!memoMap.get(currentPos).containsKey(remainingSteps)) {
                // If not, calculate and memoize the result for the remaining steps
                memoMap.get(currentPos).put(remainingSteps, (
                        (req(currentPos + 1, targetPos, remainingSteps - 1, memoMap) % mod) +
                        (req(currentPos - 1, targetPos, remainingSteps - 1, memoMap) % mod)
                ) % mod);
            }
        }

        return memoMap.get(currentPos).get(remainingSteps);
    }
}
