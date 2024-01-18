// Problem Statement:
// A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.

// The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:

// lefti is the x coordinate of the left edge of the ith building.
// righti is the x coordinate of the right edge of the ith building.
// heighti is the height of the ith building.
// You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

// The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.

// Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]

//  Example 1:

// Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
// Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
// Explanation:
// Figure A shows the buildings of the input.
// Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
// Example 2:

// Input: buildings = [[0,2,3],[2,5,3]]
// Output: [[0,3],[5,0]]
 
// Constraints:

// 1 <= buildings.length <= 104
// 0 <= lefti < righti <= 231 - 1
// 1 <= heighti <= 231 - 1
// buildings is sorted by lefti in non-decreasing order.

// Solution:

class SkylineProblem {
    
    // Computes the skyline based on the given array of buildings.
    public List<List<Integer>> getSkyline(int[][] arr) {
        List<List<Integer>> skyline = new ArrayList<>();
        
        // Build a list of Building objects from the input array
        List<Building> buildings = build(arr);
        
        // Sort buildings based on x-coordinate and height
        Collections.sort(buildings, (a, b) -> a.x == b.x ? a.height - b.height : a.x - b.x);
        
        // Use a max priority queue to keep track of heights
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        pq.add(0); // Add initial ground height
        
        int prevMaxHeight = Integer.MIN_VALUE;
        
        // Iterate through the sorted buildings
        for (Building building : buildings) {
            if (building.height < 0) { // Negative height indicates the starting point of a building
                pq.add(building.height * -1);
            } else {
                pq.remove(building.height); // Positive height indicates the ending point, so remove it
            }
            
            int currMaxHeight = pq.peek();
            
            // If the current max height is different from the previous one, add to the skyline
            if (currMaxHeight != prevMaxHeight) {
                skyline.add(Arrays.asList(building.x, currMaxHeight));
                prevMaxHeight = currMaxHeight;
            }
        }   
        
        return skyline;
    }
    
    /**
     * Converts the input array of buildings to a list of Building objects.
     * 
     * @param arr An array representing buildings with [start, end, height] information.
     * @return A list of Building objects.
     */
    private List<Building> build(int[][] arr) {
        List<Building> list = new ArrayList<>();
        for (int[] curr : arr) {
            // Make the height negative to indicate the starting point of the building
            list.add(new Building(curr[0], -curr[2]));
            
            // Positive height indicates the ending point of the building
            list.add(new Building(curr[1], curr[2]));
        }
        return list;
    }
}

// This class represents a building with x-coordinate and height information.
class Building {
    int x;
    int height;
    
     // Constructor for Building class.
    public Building(int x, int height) {
        this.x = x;
        this.height = height;
    }
}
