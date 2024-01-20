Problem Statement:
You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.

Fence the entire garden using the minimum length of rope, as it is expensive. The garden is well-fenced only if all the trees are enclosed.

Return the coordinates of trees that are exactly located on the fence perimeter. You may return the answer in any order.

Example 1:

Input: trees = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
Explanation: All the trees will be on the perimeter of the fence except the tree at [2, 2], which will be inside the fence.

Example 2:

Input: trees = [[1,2],[2,2],[4,2]]
Output: [[4,2],[2,2],[1,2]]
Explanation: The fence forms a line that passes through all the trees.
 
Constraints:

1 <= trees.length <= 3000
trees[i].length == 2
0 <= xi, yi <= 100
All the given positions are unique.

Solution:


class FenceErect {
    
//Finds the outer trees in a set of 2D points using the Graham's Scan algorithm.

    public int[][] outerTrees(int[][] trees) {
        // Sort the points based on x-coordinates and y-coordinates in case of ties.
        Arrays.sort(trees, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        // Initialize two stacks for the upper and lower hulls.
        LinkedList<Integer> stack = new LinkedList<>();
        LinkedList<Integer> stack2 = new LinkedList<>();
        
        // Find the upper hull.
        for (int i = 0; i < trees.length; i++) {
            while (stack.size() > 1) {
                double m = slope(trees, stack.peekLast(), i);
                int curr = stack.removeLast();
                int prev = stack.peekLast();
                double pm = slope(trees, prev, curr);

                if (m >= pm) {
                    stack.addLast(curr);
                    break;
                }
            }
            stack.addLast(i);
        }

        // Find the lower hull.
        for (int i = trees.length - 1; i >= 0; i--) {
            while (stack2.size() > 1) {
                double m = slope(trees, stack2.peekLast(), i);
                int curr = stack2.removeLast();
                int prev = stack2.peekLast();
                double pm = slope(trees, prev, curr);

                if (m >= pm) {
                    stack2.addLast(curr);
                    break;
                }
            }
            stack2.addLast(i);
        }

        // Combine upper and lower hulls to get the convex hull.
        boolean[] set = new boolean[trees.length];
        int count = 0;
        for (Integer i : stack) {
            count++;
            set[i] = true;
        }
        for (Integer i : stack2)
            if (!set[i]) {
                set[i] = true;
                count++;
            }
        
        // Create the result array with outer trees.
        int[][] ans = new int[count][2];
        for (int i = 0, j = 0; i < trees.length; i++)
            if (set[i])
                ans[j++] = trees[i];

        return ans;
    }

    /**
     * Calculates the slope between two points.
     * @param trees The input array containing 2D points.
     * @param i The index of the first point.
     * @param j The index of the second point.
     * @return The slope between the two points.
     */
    private double slope(int[][] trees, int i, int j) {
        double m = (trees[i][1] - trees[j][1]) / ((double) (trees[i][0] - trees[j][0]));
        if (m == Double.NEGATIVE_INFINITY) return Double.POSITIVE_INFINITY;
        if (m == -0.0) return 0.0;
        else return m;
    }
}
