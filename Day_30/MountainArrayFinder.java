// Problem Statement:
// You may recall that an array arr is a mountain array if and only if:

// arr.length >= 3
// There exists some i with 0 < i < arr.length - 1 such that:
// arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
// arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
// Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.

// You cannot access the mountain array directly. You may only access the array using a MountainArray interface:

// MountainArray.get(k) returns the element of the array at index k (0-indexed).
// MountainArray.length() returns the length of the array.
// Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

// Example 1:

// Input: array = [1,2,3,4,5,3,1], target = 3
// Output: 2
// Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
// Example 2:

// Input: array = [0,1,2,4,2,1], target = 3
// Output: -1
// Explanation: 3 does not exist in the array, so we return -1.

// Constraints:

// 3 <= mountain_arr.length() <= 104
// 0 <= target <= 109
// 0 <= mountain_arr.get(index) <= 109

// Hint 1
// Based on whether A[i-1] < A[i] < A[i+1], A[i-1] < A[i] > A[i+1], or A[i-1] > A[i] > A[i+1], we are either at the left side, peak, or right side of the mountain. We can binary search to find the peak. After finding the peak, we can binary search two more times to find whether the value occurs on either side of the peak.

// Solution:
class MountainArrayFinder {

    // Function to find the target in a mountain array
    public int findInMountainArray(int target, MountainArray mountainArr) {
        // Get the length of the array
        int n = mountainArr.length();
        
        // Initialize pointers for binary search
        int low = 0;
        int high = n - 1;
        
        // Variable to store the peak index
        int peak = -1;

        // Binary search to find the peak element
        while (low <= high) {
            int mid = (low + high) / 2;

            // Check if mid is a peak
            if (low < mid && mountainArr.get(mid) < mountainArr.get(mid - 1)) {
                high = mid - 1;
            } else if (high > mid && mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                low = mid + 1;
            } else {
                peak = mid;
                break;
            }
        }

        // If peak not found, set it to the last known high
        if (peak == -1) {
            peak = high;
        }

        // Get the element at the peak
        int peakEle = mountainArr.get(peak);

        // Check if the peak element is the target
        if (peakEle == target) {
            return peak;
        }

        // If target is greater than peak element, search left of peak; otherwise, search right
        int index = find(0, peak - 1, target, mountainArr, true);
        if (index != -1) {
            return index;
        } 

        index = find(peak + 1, n - 1, target, mountainArr, false);
        return index;
    }

    // Function to perform binary search in a subarray
    private int find(int low, int high, int target, MountainArray nums, boolean up) {
        // Binary search depending on the direction (up or down)
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums.get(mid) == target) {
                return mid;
            } else if (up ? nums.get(mid) < target : nums.get(mid) > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // If target not found, return -1
        return -1;
    }
}
