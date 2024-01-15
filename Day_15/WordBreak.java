// Problem Statement:
// Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

// Note that the same word in the dictionary may be reused multiple times in the segmentation.

// Example 1:

// Input: s = "leetcode", wordDict = ["leet","code"]
// Output: true
// Explanation: Return true because "leetcode" can be segmented as "leet code".
// Example 2:

// Input: s = "applepenapple", wordDict = ["apple","pen"]
// Output: true
// Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
// Note that you are allowed to reuse a dictionary word.
// Example 3:

// Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
// Output: false
 
// Constraints:

// 1 <= s.length <= 300
// 1 <= wordDict.length <= 1000
// 1 <= wordDict[i].length <= 20
// s and wordDict[i] consist of only lowercase English letters.
// All the strings of wordDict are unique.

// Solution:
 import java.util.*;
 class Solution {
      // Determines if the input string can be segmented into space-separated words
      // from the provided word dictionary.
     public boolean wordBreak(String s, List<String> wordDict) {
         // Create a HashMap to store words from the dictionary for efficient lookup
         HashMap<String, Integer> h1 = new HashMap<>();
         for (String str : wordDict) {
             h1.put(str, 1);
         }
         // Get the length of the input string
         int n = s.length();

         // Initialize an array to store intermediate results for dynamic programming
         int[] dp = new int[n + 1];
         Arrays.fill(dp, -1);

         // Call the helper function to perform recursive word breaking
         return helper(s, 0, h1, dp) == 1;
     }
      // Helper function to perform recursive word breaking.
     int helper(String s, int i, HashMap<String, Integer> h1, int[] dp) {
         // If the end of the string is reached, return 1 (string can be segmented)
         if (i == s.length()) {
             return 1;
         }

         // If the result for the current index is already calculated, return it
         if (dp[i] != -1) {
             return dp[i];
         }

         // Iterate over the string from the current index to find potential words
         for (int j = i; j < s.length(); j++) {
             // If a word is found in the dictionary, recursively check the remaining part
             if (isFound(s.substring(i, j + 1), h1)) {
                 int ans = helper(s, j + 1, h1, dp);
                 dp[i] = ans;

                 // If the remaining part can be segmented, return 1
                 if (ans == 1) {
                     return 1;
                 }
             }
         }

         // If no segmentation is possible, return 0
         return 0;
     }
     // Checks if a given word is found in the dictionary.
     boolean isFound(String s, HashMap<String, Integer> h1) {
         return h1.containsKey(s);
     }
 }
