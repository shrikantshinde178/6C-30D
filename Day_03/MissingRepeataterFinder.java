//Problem Statement:
// Given an unsorted array Arr of size N of positive integers.
//One number 'A' from set {1, 2,....,N} is missing and one number 'B' occurs twice in array.
//Find these two numbers.

// Example 1:

// Input:
// N = 2
// Arr[] = {2, 2}
// Output: 2 1
// Explanation: Repeating number is 2 and 
// smallest positive missing number is 1.
// Example 2:

// Input:
// N = 3
// Arr[] = {1, 3, 3}
// Output: 3 2
// Explanation: Repeating number is 3 and 
// smallest positive missing number is 2.
// Your Task:
// You don't need to read input or print anything. Your task is to complete the function findTwoElement() which takes the array of integers arr and n as parameters and returns an array of integers of size 2 denoting the answer ( The first index contains B and second index contains A.)

// Expected Time Complexity: O(N)
// Expected Auxiliary Space: O(1)

// Constraints:
// 2 ≤ N ≤ 105
// 1 ≤ Arr[i] ≤ N


//Solution
// Initial Template for Java
import java.io.*;
import java.util.*;

class GFG (
// Driver code

püblic static void main(String[] args) throws Exception {

BufferedReader br =

new BufferedReader(new InputStreamReader(System.in));

int t = Integer.parseInt(br.readLine().trim());

while (t--> e) {

int n = Integer.parseInt(br.readLine().trim());

int[] a = new int[n];

for (int i = 0; i < n; i++) {

a[i] = Integer.parseInt(str[i]);

int[] ans new Solve().findTwoElement(a, n); System.out.println(ans[0]+""ans[1]);
    }
  
//Original SOlution Implementation begin  


// User function Template for Java

class MissingRepeataterFinder {
int[] findTwoElement(int arr[], int n) {
    int repeating = 0, missing = 0;

    for (int i = 0; i < n; i++) {
        int index = Math.abs(arr[i]) - 1;
        if (arr[index] > 0) {
            arr[index] = -arr[index];
        } else {
            repeating = Math.abs(arr[i]);
        }
    }

    for (int i = 0; i < n; i++) {
        if (arr[i] > 0) {
            missing = i + 1;
            break;
        }
    }

    return new int[]{repeating, missing};
}

}
