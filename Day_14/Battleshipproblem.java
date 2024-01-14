// Problem Statement:
// Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.

// Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).

// Example 1:

// Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
// Output: 2
// Example 2:

// Input: board = [["."]]
// Output: 0
 
// Constraints:

// m == board.length
// n == board[i].length
// 1 <= m, n <= 200
// board[i][j] is either '.' or 'X'.

// Solution:

import java.util.*;

public class Battleshipproblem {

    // Class representing a tuple (i, j)
    class Tuple {
        int i;
        int j;

        public Tuple(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() {
            return i ^ j;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Tuple) {
                Tuple t = (Tuple) obj;
                return t.i == i && t.j == j;
            }
            return false;
        }

        @Override
        public String toString() {
            return "[" + i + ", " + j + "]";
        }
    }

    // Method to count battleships on the board
    public int countBattleships(char[][] board) {
        int ships = 0;  // Variable to store the count of battleships
        Set<Tuple> visitedNodes = new HashSet<>();  // Set to keep track of visited nodes

        // Iterate through the board
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                // If a battleship is found and not visited yet
                if (board[i][j] == 'X' && !visitedNodes.contains(new Tuple(i, j))) {

                    // If there is a battleship to the right, expand right
                    if (j + 1 < board[i].length && board[i][j + 1] == 'X') {
                        expandNode(board, i, j + 1, 0, 1, visitedNodes);
                    } else if (i + 1 < board.length && board[i + 1][j] == 'X') {
                        // If there is a battleship below, expand below
                        expandNode(board, i + 1, j, 1, 0, visitedNodes);
                    }
                    ++ships;  // Increment the count of battleships
                    visitedNodes.add(new Tuple(i, j));  // Mark the current node as visited
                }
            }
        }

        return ships;  // Return the total count of battleships
    }

    // Helper method to recursively expand battleship nodes
    private void expandNode(char[][] board, int i, int j, int ip, int jp, Set<Tuple> visitedNodes) {
        if (i < board.length && j < board[0].length && board[i][j] == 'X') {
            visitedNodes.add(new Tuple(i, j));  // Mark the current node as visited
            // Recursively expand in the specified direction
            expandNode(board, i + ip, j + jp, ip, jp, visitedNodes);
        }
    }
}
