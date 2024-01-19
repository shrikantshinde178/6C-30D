// Problem Statement:
// Given a string s and a string array dictionary, return the longest string in the dictionary that can be formed by deleting some of the given string characters. If there is more than one possible result, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
// Example 1:

// Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
// Output: "apple"
// Example 2:

// Input: s = "abpcplea", dictionary = ["a","b","c"]
// Output: "a"
 
// Constraints:

// 1 <= s.length <= 1000
// 1 <= dictionary.length <= 1000
// 1 <= dictionary[i].length <= 1000
// s and dictionary[i] consist of lowercase English letters.

// Solution:

class LongestWordFinder {

     // Finds the longest word from the dictionary that can be formed by deleting
    public String findLongestWord(String s, List<String> dictionary) {
        
        // Initialize variables to store the length of the longest word and the valid words.
        int largestLength = 0;
        List<String> valids = new ArrayList<>();

        // Iterate through the dictionary to find valid words.
        for (String word : dictionary) {
            int currentIndex = 0;
            
            // Check if the characters match to identify valid words.
            for (char letter : s.toCharArray()) {
                if (letter == word.charAt(currentIndex)) {
                    currentIndex++;
                    
                    // Break if the entire word or string is processed.
                    if (currentIndex == word.length() || currentIndex == s.length())
                        break;
                }
            }
            
            // Check if the entire word is processed, and update valid words and longest length.
            if (currentIndex == word.length()) {
                largestLength = Math.max(largestLength, word.length());
                valids.add(word);
            }
        }

        // Filter words with length equal to the longest length.
        List<String> largestValids = new ArrayList<>();
        for (String word : valids) {
            if (word.length() != largestLength)
                continue;
            largestValids.add(word);
        }

        // Sort the valid words alphabetically.
        largestValids.sort(null);

        // Return the first word if any valid words are found, otherwise return an empty string.
        if (largestValids.size() == 0)
            return "";

        return largestValids.get(0);
    }
}
