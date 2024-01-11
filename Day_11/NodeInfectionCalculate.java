Problem Statement:
You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

Each minute, a node becomes infected if:

The node is currently uninfected.
The node is adjacent to an infected node.
Return the number of minutes needed for the entire tree to be infected.

Example 1:
Input: root = [1,5,3,null,4,10,6,9,2], start = 3
Output: 4
Explanation: The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.

Example 2:
Input: root = [1], start = 1
Output: 0
Explanation: At minute 0, the only node in the tree is infected so we return 0.

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 105
Each node has a unique value.
A node with a value of start exists in the tree.

Solution:
public class NodeInfectionCalculate {
    public int amountOfTime(TreeNode root, int start) {
        // Map to store the parent of each node
        HashMap<Integer, TreeNode> map = new HashMap<>();
        // Variable to store the result (time/levels)
        int time = 0;
        // Queue for level-order traversal
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        TreeNode startNode = null;

        // Perform level-order traversal to find the starting node and build the parent map
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.val == start)
                    startNode = node;
                if (node.left != null) {
                    q.add(node.left);
                    map.put(node.left.val, node);
                }
                if (node.right != null) {
                    q.add(node.right);
                    map.put(node.right.val, node);
                }
            }
        }

        // Start from the target node and traverse up to the root, updating the visited set
        q.add(startNode);
        HashSet<Integer> visitedSet = new HashSet<>();
        while (!q.isEmpty()) {
            int size = q.size();
            time++;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                visitedSet.add(node.val);

                // Add the parent node to the queue if it exists and hasn't been visited
                if (map.containsKey(node.val) && !visitedSet.contains(map.get(node.val).val)) {
                    q.add(map.get(node.val));
                }

                // Add the left child to the queue if it exists and hasn't been visited
                if (node.left != null && !visitedSet.contains(node.left.val))
                    q.add(node.left);

                // Add the right child to the queue if it exists and hasn't been visited
                if (node.right != null && !visitedSet.contains(node.right.val))
                    q.add(node.right);
            }
        }

        // Subtract 1 to exclude the level of the target node itself
        return time - 1;
    }
}
