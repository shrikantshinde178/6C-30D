// Problem Statement:
// There are n cities numbered from 1 to n. You are given an array edges of size n-1, where edges[i] = [ui, vi] represents a bidirectional edge between cities ui and vi. There exists a unique path between each pair of cities. In other words, the cities form a tree.

// A subtree is a subset of cities where every city is reachable from every other city in the subset, where the path between each pair passes through only the cities from the subset. Two subtrees are different if there is a city in one subtree that is not present in the other.

// For each d from 1 to n-1, find the number of subtrees in which the maximum distance between any two cities in the subtree is equal to d.

// Return an array of size n-1 where the dth element (1-indexed) is the number of subtrees in which the maximum distance between any two cities is equal to d.

// Notice that the distance between the two cities is the number of edges in the path between them.

// Example 1:

// Input: n = 4, edges = [[1,2],[2,3],[2,4]]
// Output: [3,4,0]
// Explanation:
// The subtrees with subsets {1,2}, {2,3} and {2,4} have a max distance of 1.
// The subtrees with subsets {1,2,3}, {1,2,4}, {2,3,4} and {1,2,3,4} have a max distance of 2.
// No subtree has two nodes where the max distance between them is 3.
// Example 2:

// Input: n = 2, edges = [[1,2]]
// Output: [1]
// Example 3:

// Input: n = 3, edges = [[1,2],[2,3]]
// Output: [2,1]
 
// Constraints:

// 2 <= n <= 15
// edges.length == n-1
// edges[i].length == 2
// 1 <= ui, vi <= n
// All pairs (ui, vi) are distinct.

// Hint 1
// Iterate through every possible subtree by doing a bitmask on which vertices to include. How can you determine if a subtree is valid (all vertices are connected)?
// Hint 2
// To determine connectivity, count the number of reachable vertices starting from any included vertex and only traveling on edges connecting 2 vertices in the subtree. The count should be the same as the number of 1s in the bitmask.
// Hint 3
// The diameter is basically the maximum distance between any two nodes. Root the tree at a vertex. The answer is the max of the heights of the two largest subtrees or the longest diameter in any of the subtrees.

// Solution:
class Solution {
    // Function to count subgraphs for each diameter
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        // Create an adjacency list for the graph
        ArrayList<Integer>[] graph = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        // Populate the adjacency list based on the given edges
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            --u;
            --v;

            graph[u].add(v);
            graph[v].add(u);
        }

        // Array to store counts for each diameter
        int[] darr = new int[n - 1];

        // Iterate through all possible subsets of nodes using bitmask
        for (int mask = 0; mask < (1 << n); mask++) {
            // Get the maximum diameter for the current subset
            int d = getmax(mask, graph);

            // If diameter is greater than 0, increment the count
            if (d > 0) {
                darr[d - 1]++;
            }
        }

        return darr;
    }

    // Function to get the maximum diameter for a subset of nodes
    int getmax(int mask, ArrayList<Integer>[] graph) {
        int maxd = -1;

        // Iterate through each bit in the mask
        for (int i = 0; i < 15; i++) {
            if ((mask & (1 << i)) != 0) {
                // Update max diameter using DFS for the current node in the subset
                maxd = Math.max(maxd, dfs(i, graph, mask));
            }
        }

        return maxd;
    }

    // Depth-first search to calculate the diameter for a node subset
    int dfs(int node, ArrayList<Integer>[] graph, int mask) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        int dist = -1;
        int curr = mask;

        // Perform BFS for the current node subset
        while (!q.isEmpty()) {
            dist++;

            // Process nodes level by level
            for (int l = q.size() - 1; l >= 0; l--) {
                int z1 = q.peek();
                curr = curr ^ (1 << z1);
                q.remove();

                // Explore neighbors of the current node
                for (int z : graph[z1]) {
                    if (((mask & (1 << z)) != 0) && ((curr & (1 << z)) != 0)) {
                        q.add(z);
                    }
                }
            }
        }

        // If there are remaining nodes in the subset, return -1 (indicating invalid subset)
        if (curr != 0) {
            return -1;
        }

        return dist;
    }
}
