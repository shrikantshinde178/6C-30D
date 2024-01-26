// Problem Statement:
// Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.

// The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.

// Example 1:

// Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
// Output: 2
// Explanation: 
// For arr1[0]=4 we have: 
// |4-10|=6 > d=2 
// |4-9|=5 > d=2 
// |4-1|=3 > d=2 
// |4-8|=4 > d=2 
// For arr1[1]=5 we have: 
// |5-10|=5 > d=2 
// |5-9|=4 > d=2 
// |5-1|=4 > d=2 
// |5-8|=3 > d=2
// For arr1[2]=8 we have:
// |8-10|=2 <= d=2
// |8-9|=1 <= d=2
// |8-1|=7 > d=2
// |8-8|=0 <= d=2
// Example 2:

// Input: arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
// Output: 2
// Example 3:

// Input: arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
// Output: 1
 
// Constraints:

// 1 <= arr1.length, arr2.length <= 500
// -1000 <= arr1[i], arr2[j] <= 1000
// 0 <= d <= 100

// Hint 1
// Sort 'arr2' and use binary search to get the closest element for each 'arr1[i]', it gives a time complexity of O(nlogn). 
  
// Solution:

class DistanceBetweenTwoArray {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int res = 0;
        /*
            Approach : 
            Sort the second array first, so that we can effectively search 
            for the element we are searching for.
            
            Now, if mid == target or mid is atmost d distance apart, then we 
            should stop searching, as we found an element, 
            otherwise standard binary search, shorten our scope depending upon
            element is higher or lower.
            
            if no element found, then just do res++
            
        */
        for(int i : arr1){
            int low = 0;
            int high = arr2.length - 1;
            boolean found = false;
            
            while(low <= high){
                int mid = low + (high - low) / 2;
                if(arr2[mid] == i || Math.abs(arr2[mid] - i) <= d){
                    found = true;
                    break;
                }
                else if(arr2[mid] > i)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            
            if(!found)
                res++;
        }
        
        return res;
    }
}
