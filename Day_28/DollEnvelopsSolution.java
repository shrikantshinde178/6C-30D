// Problem Statement:
// You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.

// One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.

// Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).

// Note: You cannot rotate an envelope.

// Example 1:

// Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
// Output: 3
// Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
// Example 2:

// Input: envelopes = [[1,1],[1,1],[1,1]]
// Output: 1

// Constraints:

// 1 <= envelopes.length <= 105
// envelopes[i].length == 2
// 1 <= wi, hi <= 105

// Solution:

/**
 * The `maxEnvelopes` method calculates the maximum number of envelopes that can be nested inside each other.
 * It first sorts the input array of envelopes based on width in ascending order and height in descending order.
 * Then, it iterates through the sorted array, maintaining a list of heights that can form a valid envelope sequence.
 * The binary search is used to efficiently find the position to update the list, ensuring the increasing height condition.
 * The final result is the size of the list, representing the maximum number of nested envelopes.
 * 
 * @param envelopes 2D array representing envelopes with width and height.
 * @return The maximum number of envelopes that can be nested.
 */
public int maxEnvelopes(int[][] envelopes) {
    int n = envelopes.length, left, right, mid;

    // Sort array based on width and height
    Arrays.sort(envelopes, new Comparator<int[]>() {
        public int compare(int[] a, int[] b) {
            if (a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            } else {
                return Integer.compare(a[0], b[0]);
            }
        }
    });

    List<Integer> list = new ArrayList<>();
    list.add(envelopes[0][1]);

    for (int i = 1; i < n; i++) {
        if (envelopes[i][1] > list.get(list.size() - 1)) {
            list.add(envelopes[i][1]);
        } else {
            // Find pos of the first element which is >= current element
            left = 0;
            right = list.size() - 1;
            while (left < right) {
                mid = left + (right - left) / 2;
                if (list.get(mid) >= envelopes[i][1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            list.set(right, envelopes[i][1]);
        }
    }
    return list.size();
}
