// Problem Statement:
// Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

// Assume a BST is defined as follows:

// The left subtree of a node contains only nodes with keys less than the node's key.
// The right subtree of a node contains only nodes with keys greater than the node's key.
// Both the left and right subtrees must also be binary search trees.
 
// Example 1:

// Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
// Output: 20
// Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
// Example 2:

// Input: root = [4,3,null,1,2]
// Output: 2
// Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
// Example 3:

// Input: root = [-4,-2,-5]
// Output: 0
// Explanation: All values are negatives. Return an empty BST. 

// Constraints:

// The number of nodes in the tree is in the range [1, 4 * 104].
// -4 * 104 <= Node.val <= 4 * 104

//Solution

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class T {
    public boolean isBST;
    public Integer max;
    public Integer min;
    public Integer sum;
    public T() {
        this.isBST=false;
        this.max=null;
        this.min=null;
        this.sum=null;
    }
    public T(boolean isBST, int max, int min, int sum) {
        this.isBST=isBST;
        this.max=max;
        this.min=min;
        this.sum=sum;
    }
} 

class Solution {
    public int maxSumBST(TreeNode root) {

        traverse(root);
        return ans;
        
    }

    private int ans=0;

    private T traverse(TreeNode root) {
        if(root == null)
        return new T(true, Integer.MIN_VALUE, Integer.MAX_VALUE , 0);

      T left = traverse(root.left);
      T right=traverse(root.right);

      if(!left.isBST || !right.isBST)
         return new T();
       if(root.val <=left.max || root.val >= right.min)
          return new T();  

       final int sum =root.val + left.sum + right.sum;
       ans=Math.max(ans, sum);
       return new T(true, Math.max(root.val, right.max),
                     Math.min(root.val, left.min), sum);     
    }
}
