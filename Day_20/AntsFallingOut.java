Problem Statement:
We have a wooden plank of the length n units. Some ants are walking on the plank, each ant moves with a speed of 1 unit per second. Some of the ants move to the left, the other move to the right.

When two ants moving in two different directions meet at some point, they change their directions and continue moving again. Assume changing directions does not take any additional time.

When an ant reaches one end of the plank at a time t, it falls out of the plank immediately.

Given an integer n and two integer arrays left and right, the positions of the ants moving to the left and the right, return the moment when the last ant(s) fall out of the plank.

Example 1:
Input: n = 4, left = [4,3], right = [0,1]
Output: 4
Explanation: In the image above:
-The ant at index 0 is named A and going to the right.
-The ant at index 1 is named B and going to the right.
-The ant at index 3 is named C and going to the left.
-The ant at index 4 is named D and going to the left.
The last moment when an ant was on the plank is t = 4 seconds. After that, it falls immediately out of the plank. (i.e., We can say that at t = 4.0000000001, there are no ants on the plank).

Example 2:
Input: n = 7, left = [], right = [0,1,2,3,4,5,6,7]
Output: 7
Explanation: All ants are going to the right, the ant at index 0 needs 7 seconds to fall.

Example 3:
Input: n = 7, left = [0,1,2,3,4,5,6,7], right = []
Output: 7
Explanation: All ants are going to the left, the ant at index 7 needs 7 seconds to fall.
 
Constraints:

1 <= n <= 104
0 <= left.length <= n + 1
0 <= left[i] <= n
0 <= right.length <= n + 1
0 <= right[i] <= n
1 <= left.length + right.length <= n + 1
All values of left and right are unique, and each value can appear only in one of the two arrays.

Solution:

class AntsFallingOut {
    
   // Calculates the moment when the last ant(s) fall out of the plank.

    public int getLastMoment(int n, int[] left, int[] right) {
        int LeftMax = 0, RightMin = 0;
        
        // Initialize LeftMax with the maximum value in the left array.
        if (left.length != 0) {
            LeftMax = left[0];
        }
        
        // Initialize RightMin with the minimum value in the right array.
        if (right.length != 0) {
            RightMin = right[0];
        }
       
        // Take the maximum value in the left array.
        for (int i = 0; i < left.length; i++) {
            if (left[i] > LeftMax) {
                LeftMax = left[i];
            }
        }
        
        // Take the minimum value in the right array.
        for (int j = 0; j < right.length; j++) {
            if (right[j] < RightMin) {
                RightMin = right[j];
            }
        }
        
        // Handle cases when either the left or right array is empty.
        if (left.length == 0) {
            return n - RightMin;
        } 
        if (right.length == 0) {
            return LeftMax;
        }

        // Return the maximum of LeftMax and n - RightMin, representing the moment when the last ant(s) fall out.
        return Math.max(LeftMax, n - RightMin);
    }
}
