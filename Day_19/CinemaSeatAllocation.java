// Problem Statement:

// A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled from 1 to 10 as shown in the figure above.

// Given the array reservedSeats containing the numbers of seats already reserved, for example, reservedSeats[i] = [3,8] means the seat located in row 3 and labelled with 8 is already reserved.

// Return the maximum number of four-person groups you can assign on the cinema seats. A four-person group occupies four adjacent seats in one single row. Seats across an aisle (such as [3,3] and [3,4]) are not considered to be adjacent, but there is an exceptional case on which an aisle split a four-person group, in that case, the aisle split a four-person group in the middle, which means to have two people on each side.

// Example 1:

// Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
// Output: 4
// Explanation: The figure above shows the optimal allocation for four groups, where seats mark with blue are already reserved and contiguous seats mark with orange are for one group.
// Example 2:

// Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
// Output: 2
// Example 3:

// Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
// Output: 4
 
// Constraints:

// 1 <= n <= 10^9
// 1 <= reservedSeats.length <= min(10*n, 10^4)
// reservedSeats[i].length == 2
// 1 <= reservedSeats[i][0] <= n
// 1 <= reservedSeats[i][1] <= 10
// All reservedSeats[i] are distinct.

// Hint 1
// Note you can allocate at most two families in one row.
// Hint 2
// Greedily check if you can allocate seats for two families, one family or none.
// Hint 3
// Process only rows that appear in the input, for other rows you can always allocate seats for two families.

// Solution:
class CinemaSeatAllocation {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // #1 create map, key is row, value is all seats of that row - an array
        Map<Integer, int[]> map = new HashMap<>();

        for(int[] seat: reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // #4 in the map we only track/mark the rows with reserved seat
            map.putIfAbsent(row, new int[11]);
            // #2 mark reserved seat as 1.
            map.get(row)[col] = 1;
        }
        
        int count = 0;
        
        for(Map.Entry<Integer, int[]> pair: map.entrySet()) {
            int[] seats = pair.getValue();
            // #3 count available group of each row
            count+= getCount(seats);
        }
        
        // #5 the row without any reservation, then we know at most we can get 2 groups, just calculate it and add it.
        int noReservationRow = n - map.size();
        
        return count + noReservationRow * 2;
    }
    
    private int getCount(int[] seats) {
        //    - all possible groups of each row are 2 to 5, 6 to 9, 4 to 7
        //    - 2 to 5, 6 to 9 are "compatible"(if possible we can pick both of them since two ranges no overlap)
        //    - 4 to 7 are overlap with 2 to 5 or 6 to 9. so if pick 4 to 7, then cannot pick anything else.
        // try to pick the max between the two options
        return Math.max(countSeat(seats, 2, 5) + countSeat(seats, 6, 9), countSeat(seats, 4, 7));
    }
    
    private int countSeat(int[] seats, int start, int end) {
        // since reversed seat is marked as 1, so once in the range there is a 1, then it is not possible and return 0
        for(int i = start; i <= end; i++) {
            if(seats[i] == 1) return 0;
        }
        // if the range is available
        return 1;
    }
}
