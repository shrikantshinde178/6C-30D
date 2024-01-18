// Problem Statement:
// You are given an array start where start = [startX, startY] represents your initial position (startX, startY) in a 2D space. You are also given the array target where target = [targetX, targetY] represents your target position (targetX, targetY).

// The cost of going from a position (x1, y1) to any other position in the space (x2, y2) is |x2 - x1| + |y2 - y1|.

// There are also some special roads. You are given a 2D array specialRoads where specialRoads[i] = [x1i, y1i, x2i, y2i, costi] indicates that the ith special road can take you from (x1i, y1i) to (x2i, y2i) with a cost equal to costi. You can use each special road any number of times.

// Return the minimum cost required to go from (startX, startY) to (targetX, targetY).

// Example 1:

// Input: start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
// Output: 5
// Explanation: The optimal path from (1,1) to (4,5) is the following:
// - (1,1) -> (1,2). This move has a cost of |1 - 1| + |2 - 1| = 1.
// - (1,2) -> (3,3). This move uses the first special edge, the cost is 2.
// - (3,3) -> (3,4). This move has a cost of |3 - 3| + |4 - 3| = 1.
// - (3,4) -> (4,5). This move uses the second special edge, the cost is 1.
// So the total cost is 1 + 2 + 1 + 1 = 5.
// It can be shown that we cannot achieve a smaller total cost than 5.
// Example 2:

// Input: start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
// Output: 7
// Explanation: It is optimal to not use any special edges and go directly from the starting to the ending position with a cost |5 - 3| + |7 - 2| = 7.

// Constraints:

// start.length == target.length == 2
// 1 <= startX <= targetX <= 105
// 1 <= startY <= targetY <= 105
// 1 <= specialRoads.length <= 200
// specialRoads[i].length == 5
// startX <= x1i, x2i <= targetX
// startY <= y1i, y2i <= targetY
// 1 <= costi <= 105

// Solution:

class MinimunCostRoads {
    int r[] = {0, -1, 1, 0};
    int c[] = {1, 0, 0, -1};

    // This inner class represents a point with x and y coordinates along with an associated cost.   
    class P {
        int x;
        int y;
        int cost;

        @Override
        public String toString() {
            return x + " &&& " + y + " &&& " + cost;
        }
    }

    // Computes the minimum cost to travel from the start point to the target point.
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int res = Integer.MAX_VALUE;
        PriorityQueue<P> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

        // Create a map to store special roads with their associated costs
        Map<String, List<int[]>> special = new HashMap<>();
        for (int[] s : specialRoads) {
            String key = s[0] + "##" + s[1];

            if (special.containsKey(key)) {
                special.get(key).add(new int[]{s[2], s[3], s[4]});
            } else {
                List<int[]> list = new ArrayList<>();
                list.add(new int[]{s[2], s[3], s[4]});
                special.put(key, list);
            }
        }

        // Initialize the starting point and add it to the priority queue
        P st = new P();
        st.x = start[0];
        st.y = start[1];
        st.cost = 0;
        pq.add(st);

        // Set to keep track of visited points
        Set<String> visited = new HashSet<>();

        // Explore points using Dijkstra's algorithm
        while (pq.size() > 0) {
            P p1 = pq.poll();

            if (p1.x == target[0] && p1.y == target[1]) {
                return p1.cost; // Return the minimum cost when reaching the target
            }

            String currentKey = p1.x + "##" + p1.y;
            if (visited.contains(currentKey)) continue;
            visited.add(currentKey);

            // Explore neighbors and special roads
            for (Map.Entry<String, List<int[]>> entry : special.entrySet()) {
                int d = Math.abs(p1.x - Integer.valueOf(entry.getKey().split("##")[0])) +
                        Math.abs(p1.y - Integer.valueOf(entry.getKey().split("##")[1]));

                for (int[] cos : entry.getValue()) {
                    P p = new P();
                    p.x = cos[0];
                    p.y = cos[1];
                    p.cost = d + cos[2] + p1.cost;
                    pq.add(p);
                }
            }

            // Add the target point to explore the path directly to the target
            P p2 = new P();
            p2.x = target[0];
            p2.y = target[1];
            p2.cost = Math.abs(target[0] - p1.x) + Math.abs(target[1] - p1.y) + p1.cost;
            pq.add(p2);
        }

        return -1; // If no valid path is found
    }
}
