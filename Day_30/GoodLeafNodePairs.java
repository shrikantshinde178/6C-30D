Problem Statement:
You are given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.

Return the number of good leaf node pairs in the tree.

Example 1:
Input: root = [1,2,3,null,4], distance = 3
Output: 1
Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.

Example 2:
Input: root = [1,2,3,4,5,6,7], distance = 3
Output: 2
Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.

Example 3:
Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
Output: 1
Explanation: The only good pair is [2,5].

Constraints:

The number of nodes in the tree is in the range [1, 210].
1 <= Node.val <= 100
1 <= distance <= 10

Hint 1
Start DFS from each leaf node. stop the DFS when the number of steps done > distance.
Hint 2
If you reach another leaf node within distance steps, add 1 to the answer.
Hint 3
Note that all pairs will be counted twice so divide the answer by 2.

Solution:
/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    // Constructors
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * Solution class for counting pairs of nodes in a binary tree with distance constraint.
 */
class GoodLeafNodePairs {
    private int count = 0;

    /**
     * Main method to count pairs in the binary tree.
     * @param root The root of the binary tree.
     * @param distance The distance constraint.
     * @return The count of pairs satisfying the distance constraint.
     */
    public int countPairs(TreeNode root, int distance) {
        // Call the recursive DFS method
        dfs(root, distance);
        return count;
    }

    /**
     * Recursive Depth-First Search (DFS) method to calculate the distances from each leaf node.
     * @param root The current node in the tree.
     * @param distance The distance constraint.
     * @return An array representing the distances from each leaf node.
     */
    private int[] dfs(TreeNode root, int distance) {
        // Base case: If the node is null, return an array of zeros.
        if (root == null) {
            return new int[distance + 1];
        }
        
        // Base case: If the node is a leaf, return an array with 1 at distance 1.
        if (root.left == null && root.right == null) {
            int[] leaf = new int[distance + 1];
            leaf[1]++;
            return leaf;
        }

        // Recursive calls for the left and right subtrees.
        int[] left = dfs(root.left, distance);
        int[] right = dfs(root.right, distance);
        
        // Iterate through all possible combinations of distances and update the count.
        for (int i = 0; i <= distance; i++) {
            for (int j = 0; j <= distance && i + j <= distance; j++) {
                count += (left[i] * right[j]);
            }
        }

        // Create an array representing distances from each leaf node.
        int[] leaf = new int[distance + 1];
        for (int i = 1; i < distance; i++) {
            leaf[i] = left[i - 1] + right[i - 1];
        }

        return leaf;
    }
}
