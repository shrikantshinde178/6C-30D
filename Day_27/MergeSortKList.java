// Problem Statement:
// You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

// Merge all the linked-lists into one sorted linked-list and return it.

// Example 1:

// Input: lists = [[1,4,5],[1,3,4],[2,6]]
// Output: [1,1,2,3,4,4,5,6]
// Explanation: The linked-lists are:
// [
//   1->4->5,
//   1->3->4,
//   2->6
// ]
// merging them into one sorted list:
// 1->1->2->3->4->4->5->6
// Example 2:

// Input: lists = []
// Output: []
// Example 3:

// Input: lists = [[]]
// Output: []
 
// Constraints:

// k == lists.length
// 0 <= k <= 104
// 0 <= lists[i].length <= 500
// -104 <= lists[i][j] <= 104
// lists[i] is sorted in ascending order.
// The sum of lists[i].length will not exceed 104.

// Solution:

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * This class provides a solution for merging k sorted linked lists.
 * It utilizes a divide-and-conquer approach by recursively dividing the list of lists
 * into smaller halves until individual lists are obtained. It then merges these lists
 * using a helper function. The merge operation is done iteratively, comparing the
 * values of the nodes and creating a merged linked list.
 */
class MergeSortKList {
    
    /**
     * Merges k sorted linked lists into a single sorted linked list.
     *
     * @param lists An array of ListNode representing k sorted linked lists.
     * @return The merged sorted linked list.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergesort(lists, 0, lists.length - 1);
    }
    
    /**
     * Recursively divides and merges the array of lists.
     *
     * @param lists An array of ListNode representing k sorted linked lists.
     * @param s     The start index of the array.
     * @param e     The end index of the array.
     * @return The merged sorted linked list.
     */
    ListNode mergesort(ListNode[] lists, int s, int e) {
        if (s == e) return lists[s];
        
        int mid = s + (e - s) / 2;
        ListNode left = mergesort(lists, s, mid);
        ListNode right = mergesort(lists, mid + 1, e);
        
        return merge(left, right);
    }
    
    /**
     * Merges two sorted linked lists into a single sorted linked list.
     *
     * @param first  The first sorted linked list.
     * @param second The second sorted linked list.
     * @return The merged sorted linked list.
     */
    ListNode merge(ListNode first, ListNode second) {
        ListNode Dumnode = new ListNode(0);
        ListNode tail = Dumnode;
        
        while (true) {
            if (first == null) {
                tail.next = second;
                break;
            }
            if (second == null) {
                tail.next = first;
                break;
            }
            if (first.val < second.val) {
                tail.next = first;
                first = first.next;
                tail = tail.next;
            } else {
                tail.next = second;
                second = second.next;
                tail = tail.next;
            }
        }
        
        return Dumnode.next;
    }
}
