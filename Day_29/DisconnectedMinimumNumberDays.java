// Problem statement:
// You are given an m x n binary grid grid where 1 represents land and 0 represents water. An island is a maximal 4-directionally (horizontal or vertical) connected group of 1's.

// The grid is said to be connected if we have exactly one island, otherwise is said disconnected.

// In one day, we are allowed to change any single land cell (1) into a water cell (0).

// Return the minimum number of days to disconnect the grid.

//  Example 1:

// Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]

// Output: 2
// Explanation: We need at least 2 days to get a disconnected grid.
// Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
  
// Example 2:
// Input: grid = [[1,1]]
// Output: 2
// Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
 
// Constraints:

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 30
// grid[i][j] is either 0 or 1.

// Hint 1
// Return 0 if the grid is already disconnected.
// Hint 2
// Return 1 if changing a single land to water disconnect the island.
// Hint 3
// Otherwise return 2.
// Hint 4
// We can disconnect the grid within at most 2 days.

// Solution:

/**
 * Solution for finding the minimum days to disconnect or modify islands in a grid.
 */
class DisconnectedMinimumNumberDays {
    int WATER = 0;
    int LAND = 1;

    /**
     * Main function to determine the minimum number of days.
     *
     * @param grid The input grid representing land (1) and water (0).
     * @return The minimum number of days required, where 0 means already disconnected, 1 means disconnecting with one modification, and 2 means can disconnect within at most 2 days.
     */
    public int minDays(int[][] grid) {
        if (isGraphDisconnected(grid))
            return 0;
        if (checkIfRemovingOneNodeDisconnectsGraph(grid))
            return 1;
        return 2;
    }

    /**
     * Checks if the entire graph is disconnected.
     *
     * @param grid The input grid representing land (1) and water (0).
     * @return True if the graph is disconnected, false otherwise.
     */
    boolean isGraphDisconnected(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        boolean[][] visited = new boolean[M][N];
        int dfsCount = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == LAND && !visited[i][j]) {
                    dfsCount++;
                    if (dfsCount > 1)
                        return true;
                    DFS(grid, i, j, visited);
                }
            }
        }
        return dfsCount != 1;
    }

    /**
     * Checks if removing one land node disconnects the graph.
     *
     * @param grid The input grid representing land (1) and water (0).
     * @return True if removing one land node disconnects the graph, false otherwise.
     */
    boolean checkIfRemovingOneNodeDisconnectsGraph(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        int landCount = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == LAND) {
                    landCount++;
                    grid[i][j] = WATER;
                    boolean[][] visited = new boolean[M][N];
                    int adjacentLandCount = 1;
                    for (int[] dir : dirs) {
                        int nextRow = i + dir[0];
                        int nextCol = j + dir[1];
                        if (isSafe(nextRow, nextCol, M, N) && grid[nextRow][nextCol] == 1) {
                            adjacentLandCount++;
                            if (adjacentLandCount > 1) {
                                DFS(grid, nextRow, nextCol, visited);
                                break;
                            }
                        }
                    }

                    for (int[] dir : dirs) {
                        int nextRow = i + dir[0];
                        int nextCol = j + dir[1];
                        if (isSafe(nextRow, nextCol, M, N) && grid[nextRow][nextCol] == 1) {
                            if (!visited[nextRow][nextCol])
                                return true;
                        }
                    }
                    grid[i][j] = LAND;
                }
            }
        }
        return landCount == 1;
    }

    /**
     * Checks if a cell is within the grid boundaries.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @param M   The number of rows in the grid.
     * @param N   The number of columns in the grid.
     * @return True if the cell is within the grid boundaries, false otherwise.
     */
    boolean isSafe(int row, int col, int M, int N) {
        return row >= 0 && row < M && col >= 0 && col < N;
    }

    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * Depth-first search (DFS) to traverse and mark connected land cells.
     *
     * @param grid    The input grid representing land (1) and water (0).
     * @param row     The row index of the current cell.
     * @param col     The column index of the current cell.
     * @param visited Matrix to keep track of visited cells.
     */
    void DFS(int[][] grid, int row, int col, boolean[][] visited) {
        int M = grid.length;
        int N = grid[0].length;
        if (!isSafe(row, col, M, N) || visited[row][col] || grid[row][col] == 0)
            return;
        visited[row][col] = true;
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isSafe(newRow, newCol, M, N) && !visited[newRow][newCol] && grid[newRow][newCol] == 1)
                DFS(grid, newRow, newCol, visited);
        }
    }
}
