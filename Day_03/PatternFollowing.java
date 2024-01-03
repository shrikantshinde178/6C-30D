// Problem Statement:
// Given a pattern containing only I's and D's. I for increasing and D for decreasing. Devise an algorithm to print the minimum number following that pattern. Digits from 1-9 and digits can't repeat.

// Example 1:

// Input:
// D
// Output:
// 21
// Explanation:
// D is meant for decreasing, so we choose the minimum number among all possible numbers like 21,31,54,87,etc.
// Example 2:

// Input:
// IIDDD
// Output:
// 126543
// Explanation:
// Above example is self- explanatory,
// 1 < 2 < 6 > 5 > 4 > 3
//   I - I - D - D - D
// Your Task:

// You don't need to read input or print anything. Your task is to complete the function printMinNumberForPattern() which takes the string S and returns a string containing the minimum number following the valid number.

// Expected Time Complexity: O(N)
// Expected Auxiliary Space: O(1)

// Constraints:
// 1 ≤ Length of String ≤ 8

//Solution:

//Driver Code Starts
Export java.io.*;
import java.util.*;

public class GFG

public static void main(String args[]) throws IOException

BufferedReader read new BufferedReader(new InputStreamReader(System.in));

t = tnte*pi*er (read.readLine());

while(t0)

String 5 read.readLine();

Solution ob new Solution();

System.out.println(ob.printMinNumberForPattern(S));

)
// Oriver Code Ends
// User function Template for Java
class Solution {
    // Function to print the minimum number for the given pattern
    static String printMinNumberForPattern(String pattern) {
        // Initialize an empty string to store the result
        String result = "";

        // Create a string with consecutive numbers from 1 to pattern.length() + 1
        for (int i = 0; i <= pattern.length(); i++) {
            result += i + 1;
        }

        // Iterate through the input pattern string
        for (int i = 0; i < pattern.length(); i++) {
            // Check if the current character is 'D' (decreasing)
            if (pattern.charAt(i) == 'D') {
                // Save the current index
                int startIndex = i;

                // Continue until the pattern changes or reaches the end
                while (i < pattern.length() && pattern.charAt(i) == 'D') {
                    i++;
                }

                // Generate a new string with the decreasing pattern
                String tempResult = result.substring(0, startIndex);
                for (int j = i; j >= startIndex; j--) {
                    tempResult += result.charAt(j);
                }
                for (int j = i + 1; j < result.length(); j++) {
                    tempResult += result.charAt(j);
                }

                // Update the result string with the new pattern
                result = tempResult;
            }
        }

        // Return the final result
        return result;
    }
}
