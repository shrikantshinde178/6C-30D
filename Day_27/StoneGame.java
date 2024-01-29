// Problem Statement:
// Alice and Bob take turns playing a game, with Alice starting first.

// There are n stones in a pile. On each player's turn, they can remove a stone from the pile and receive points based on the stone's value. Alice and Bob may value the stones differently.

// You are given two integer arrays of length n, aliceValues and bobValues. Each aliceValues[i] and bobValues[i] represents how Alice and Bob, respectively, value the ith stone.

// The winner is the person with the most points after all the stones are chosen. If both players have the same amount of points, the game results in a draw. Both players will play optimally. Both players know the other's values.

// Determine the result of the game, and:

// If Alice wins, return 1.
// If Bob wins, return -1.
// If the game results in a draw, return 0.

// Example 1:

// Input: aliceValues = [1,3], bobValues = [2,1]
// Output: 1
// Explanation:
// If Alice takes stone 1 (0-indexed) first, Alice will receive 3 points.
// Bob can only choose stone 0, and will only receive 2 points.
// Alice wins.
// Example 2:

// Input: aliceValues = [1,2], bobValues = [3,1]
// Output: 0
// Explanation:
// If Alice takes stone 0, and Bob takes stone 1, they will both have 1 point.
// Draw.
// Example 3:

// Input: aliceValues = [2,4,3], bobValues = [1,6,7]
// Output: -1
// Explanation:
// Regardless of how Alice plays, Bob will be able to have more points than Alice.
// For example, if Alice takes stone 1, Bob can take stone 2, and Alice takes stone 0, Alice will have 6 points to Bob's 7.
// Bob wins.

// Constraints:

// n == aliceValues.length == bobValues.length
// 1 <= n <= 105
// 1 <= aliceValues[i], bobValues[i] <= 100

// Hint 1
// When one takes the stone, they not only get the points, but they take them away from the other player too.
// Hint 2
// Greedily choose the stone with the maximum aliceValues[i] + bobValues[i].

// Solution:

/**
 * This class provides a solution for the Stone Game VI problem.
 * The problem involves two players, Alice and Bob, selecting stones
 * from a set of piles. Each player aims to maximize their score,
 * which is determined by the sum of values associated with the selected stones.
 * The solution uses an array to store the combined, Alice's, and Bob's values
 * for each pile. It then sorts the array based on the combined values in descending order.
 * The algorithm iterates through the sorted array, and each player selects stones alternatively
 * based on their index. Finally, the method returns the result of comparing Alice's and Bob's scores.
 */
public class StoneGame {
    
    /**
     * Computes the result of the Stone Game VI based on the given values for Alice and Bob.
     *
     * @param aliceValues An array representing the values of stones chosen by Alice.
     * @param bobValues   An array representing the values of stones chosen by Bob.
     * @return            An integer representing the result of the game (-1, 0, or 1).
     */
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int[][] arr = new int[aliceValues.length][3];
        int n = aliceValues.length;
        
        // Populate the array with combined, Alice's, and Bob's values for each pile.
        for (int i = 0; i < n; i++) {
            arr[i][0] = aliceValues[i] + bobValues[i];
            arr[i][1] = aliceValues[i];
            arr[i][2] = bobValues[i];
        }
        
        // Sort the array based on combined values in descending order.
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        
        int a = 0, b = 0;
        
        // Iterate through the sorted array to determine scores for Alice and Bob.
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                a += arr[i][1];
            } else {
                b += arr[i][2];
            }
        }
        
        // Return the result of comparing Alice's and Bob's scores.
        return Integer.compare(a, b);
    }
}
