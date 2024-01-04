// Problem Statement:
// You are given an integer matrix isWater of size m x n that represents a map of land and water cells.

// If isWater[i][j] == 0, cell (i, j) is a land cell.
// If isWater[i][j] == 1, cell (i, j) is a water cell.
// You must assign each cell a height in a way that follows these rules:

// The height of each cell must be non-negative.
// If the cell is a water cell, its height must be 0.
// Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
// Find an assignment of heights such that the maximum height in the matrix is maximized.

// Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them. 

// Example 1:

// Input: isWater = [[0,1],[0,0]]
// Output: [[1,0],[2,1]]
// Explanation: The image shows the assigned heights of each cell.
// The blue cell is the water cell, and the green cells are the land cells.
// Example 2:

// Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
// Output: [[1,1,0],[0,1,1],[1,2,2]]
// Explanation: A height of 2 is the maximum possible height of any assignment.
// Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.
 
// Constraints:

// m == isWater.length
// n == isWater[i].length
// 1 <= m, n <= 1000
// isWater[i][j] is 0 or 1.
// There is at least one water cell.

// Solution
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class HighestPeak {
    public int[][] highestPeak(int[][] isWater) {

        // Get the dimensions of the input matrix
        final int rows = isWater.length;
        final int cols = isWater[0].length;

        // Define the directions: right, down, left, up
        final int[] directions = {0, 1, 0, -1, 0};

        // Initialize the result matrix with all values set to -1
        int[][] resultMatrix = new int[rows][cols];
        Arrays.stream(resultMatrix).forEach(row -> Arrays.fill(row, -1));

        // Create a queue to perform BFS
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();

        // Iterate through the input matrix
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                if (isWater[i][j] == 1) {
                    queue.offer(new Pair<>(i, j));
                    resultMatrix[i][j] = 0;
                }

        // Perform BFS
        while (!queue.isEmpty()) {
            // Get the coordinates of the current cell from the queue
            final int currentRow = queue.peek().getKey();
            final int currentCol = queue.poll().getValue();

            for (int k = 0; k < 4; ++k) {
                final int neighborRow = currentRow + directions[k];
                final int neighborCol = currentCol + directions[k + 1];

                // Check if the neighboring cell is within bounds
                if (neighborRow < 0 || neighborRow == rows || neighborCol < 0 || neighborCol == cols)
                    continue;

                // Check if the neighboring cell has been visited
                if (resultMatrix[neighborRow][neighborCol] != -1)
                    continue;

                resultMatrix[neighborRow][neighborCol] = resultMatrix[currentRow][currentCol] + 1;

                queue.offer(new Pair<>(neighborRow, neighborCol));
            }
        }

        return resultMatrix;

    }
}
