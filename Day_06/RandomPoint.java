//Problem Statement:
// ou are given an array of non-overlapping axis-aligned rectangles rects where rects[i] = [ai, bi, xi, yi] indicates that (ai, bi) is the bottom-left corner point of the ith rectangle and (xi, yi) is the top-right corner point of the ith rectangle. Design an algorithm to pick a random integer point inside the space covered by one of the given rectangles. A point on the perimeter of a rectangle is included in the space covered by the rectangle.

// Any integer point inside the space covered by one of the given rectangles should be equally likely to be returned.

// Note that an integer point is a point that has integer coordinates.

// Implement the Solution class:

// Solution(int[][] rects) Initializes the object with the given rectangles rects.
// int[] pick() Returns a random integer point [u, v] inside the space covered by one of the given rectangles.
 
// Example 1:
// Input
// ["Solution", "pick", "pick", "pick", "pick", "pick"]
// [[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
// Output
// [null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]

// Explanation
// Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
// solution.pick(); // return [1, -2]
// solution.pick(); // return [1, -1]
// solution.pick(); // return [-1, -2]
// solution.pick(); // return [-2, -2]
// solution.pick(); // return [0, 0]
 
// Constraints:

// 1 <= rects.length <= 100
// rects[i].length == 4
// -109 <= ai < xi <= 109
// -109 <= bi < yi <= 109
// xi - ai <= 2000
// yi - bi <= 2000
// All the rectangles do not overlap.
// At most 104 calls will be made to pick.

// Solution

class RandomPoint {
    // 2D array to store the coordinates of rectangles
    int[][] rects;
    
    // TreeMap to maintain the cumulative weights and their corresponding rectangle indices
    TreeMap<Integer, Integer> weightedRectIndex = new TreeMap<>();
    
    // Total number of points in all rectangles
    int nPoints = 0;
    
    // Random number generator
    Random rng = new Random();
    // Constructor initializes the Solution with an array of rectangles.
    // It calculates the cumulative weights and stores them in the TreeMap.
    public Solution(int[][] rects) {
        this.rects = rects;
        int index = 0;
        // Calculate cumulative weights and store them in the TreeMap
        for (int[] rect : rects) {
            weightedRectIndex.put(nPoints, index++);
            nPoints += width(rect) * height(rect);
        }
    }
    
    // Randomly picks a point within the set of rectangles based on their weights.
    public int[] pick() {
        // Generate a random point within the total weight
        int point = rng.nextInt(nPoints);
        // Find the rectangle with the appropriate weight
        var entry = weightedRectIndex.floorEntry(point);
        // Find the point within the current rectangle
        int rectPoint = point - entry.getKey();
        int[] rect = rects[entry.getValue()];
        return new int[]{
            rect[0] + rectPoint % width(rect), 
            rect[1] + rectPoint / width(rect)};
    }
    
     // Calculates the width of a rectangle.
        private int width(int[] rect) {
        return rect[2] - rect[0] + 1;
    }
    
  // Calculates the height of a rectangle.

    private int height(int[] rect) {
        return rect[3] - rect[1] + 1;
    }
}
