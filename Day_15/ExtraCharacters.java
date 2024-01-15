// Problem Statement:
// You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.

// Return the minimum number of extra characters left over if you break up s optimally.

// Example 1:

// Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
// Output: 1
// Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.

// Example 2:

// Input: s = "sayhelloworld", dictionary = ["hello","world"]
// Output: 3
// Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.
 
// Constraints:

// 1 <= s.length <= 50
// 1 <= dictionary.length <= 50
// 1 <= dictionary[i].length <= 50
// dictionary[i] and s consists of only lowercase English letters
// dictionary contains distinct words

// Solution:

import java.util.Arrays;
import java.util.HashSet;

 // The Solution class provides a method to calculate the minimum number of extra characters
class ExtraCharacters {

     // Calculates the minimum number of extra characters needed to make a string
     // from a given dictionary.
    public int minExtraChar(String s, String[] dictionary) {
        // Initialize an array for memoization
        Integer[] dp = new Integer[s.length()];

        // Convert the dictionary array to a HashSet for efficient lookup
        HashSet<String> dict = new HashSet<>(Arrays.asList(dictionary));

        // Call the memoization function
        return memo(dict, new StringBuilder(s), 0, dp);
    }
     // Memoization function to calculate the minimum number of extra characters needed.

    private int memo(HashSet<String> dict, StringBuilder s, int index, Integer[] dp) {
        // If the end of the string is reached, return 0 (no extra characters needed)
        if (s.length() == index) {
            return 0;
        }

        // If the result for the current index is already calculated, return it
        if (dp[index] != null) {
            return dp[index];
        }

        // Initialize ans with the case where one extra character is added
        int ans = memo(dict, s, index + 1, dp) + 1;

        // Iterate over the string to check potential words and update the ans accordingly
        for (int i = index; i < s.length(); i++) {
            String temp = s.substring(index, i + 1);

            if (dict.contains(temp)) {
                ans = Math.min(ans, memo(dict, s, i + 1, dp));
            }
        }

        // Save the result in the dp array and return the minimum number of extra characters needed
        return dp[index] = ans;
    }
}
