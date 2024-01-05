// Problem Statement:
// Given a string, Your task is to  complete the function encode that returns the run length encoded string for the given string.
// eg if the input string is “wwwwaaadexxxxxx”, then the function should return “w4a3d1e1x6″.
// You are required to complete the function encode that takes only one argument the string which is to be encoded and returns the encoded string.

// Example 1:

// Input:
// str = aaaabbbccc
// Output: a4b3c3
// Explanation: a repeated 4 times
// consecutively b 3 times, c also 3
// times.
// Example 2:

// Input:
// str = abbbcdddd
// Output: a1b3c1d4
// Your Task:
// Complete the function encode() which takes a character array as a input parameter and returns the encoded string.

// Expected Time Complexity: O(N), N = length of given string.
// Expected Auxiliary Space: O(1)

// Constraints:
// 1<=length of str<=100
 
// Solution

// Driver Code Starts
import java.util.*;

class RLEncoding {

public static void main(String[] args){

Scanner sc = new Scanner(System.in);

int T = sc.nextInt();
sc.nextLine();

while(T>e){
  String str sc.nextLine();
  GfGg new GfG(); System.out.println(g.encode(str));
  T--;
      }
    }
  }
class GfG {
    String encode(String str) {
        // Initialize variables
        StringBuilder encodedString = new StringBuilder();
        int count = 1;

        // Iterate through the characters in the string
        for (int i = 0; i < str.length(); i++) {
            // Check if the current character is the same as the next one
            if (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                // Increment count if consecutive characters are the same
                count++;
            } else {
                // Append the character and its count to the encoded string
                encodedString.append(str.charAt(i));
                encodedString.append(count);

                // Reset count for the next character
                count = 1;
            }
        }

        // Return the final encoded string
        return encodedString.toString();
    }
}
