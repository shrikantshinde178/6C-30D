// Problem Statement:
// Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

// Implement KthLargest class:

// KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
// int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
 
// Example 1:

// Input
// ["KthLargest", "add", "add", "add", "add", "add"]
// [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
// Output
// [null, 4, 5, 5, 8, 8]

// Explanation
// KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
// kthLargest.add(3);   // return 4
// kthLargest.add(5);   // return 5
// kthLargest.add(10);  // return 5
// kthLargest.add(9);   // return 8
// kthLargest.add(4);   // return 8
 
// Constraints:

// 1 <= k <= 104
// 0 <= nums.length <= 104
// -104 <= nums[i] <= 104
// -104 <= val <= 104
// At most 104 calls will be made to add.
// It is guaranteed that there will be at least k elements in the array when you search for the kth element.

// Solution:

class LargestElementInStream {
    TreeNode _root;
    int _k;
    int _size;
    // Obtain the minimum value of the tree
    private int findMin() {
        TreeNode node = _root;
        while (node.left != null)
            node = node.left;
        return node.val;
    }
    
    // Obtain the smallest value in the tree > than root->val
    private int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) 
            root = root.left;
        return root.val;
    }
    
    // Obtain the largest value in the tree <= node->val
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) 
            root = root.right;
        return root.val;
    }

    // If the tree has an extra node, prune off the smallest one
    private TreeNode pruneToK(TreeNode root) {
        if (_size <= _k)
            return root;
        if (root == null) {
            return null;
        } else if (root.left != null) {
            // delete from the left subtree
            root.left = pruneToK(root.left);
        } else {
            --_size;
            if (root.left == null && root.right == null) {
                // the node is a leaf
                root = null;
            } else if (root.right != null) {
                // the node is not a leaf and has a right child
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } 
        }
        return root;
    }
    
    // Delete a node with val=key from the tree rooted at 'root'
    private TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) 
            return null;
        if (key > root.val) {
            // delete from the right subtree
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            // delete from the left subtree
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                // the node is a leaf
                root = null;
            } else if (root.right != null) {
                // the node is not a leaf and has a right child
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                // the node is not a leaf, has no right child, and has a left child    
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
    
    // insert a node into the tree at 'root' with a value 'val'
    private TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            ++_size;
            return new TreeNode(val);
        } else if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
    
    public KthLargest(int k, int[] nums) {
        _k = k;
        _root = null;
        _size = 0;
        for (int val: nums) {
            _root = insertIntoBST(_root, val);
            _root = pruneToK(_root);
        }
    }
    
    public int add(int val) {
        _root = insertIntoBST(_root, val);
        _root = pruneToK(_root);
        return findMin();
    }
};
