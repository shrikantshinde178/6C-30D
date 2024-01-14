// Problem Statement:
// Given an array of strings words and an integer k, return the k most frequent strings.

// Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.

// Example 1:

// Input: words = ["i","love","leetcode","i","love","coding"], k = 2
// Output: ["i","love"]
// Explanation: "i" and "love" are the two most frequent words.
// Note that "i" comes before "love" due to a lower alphabetical order.
// Example 2:

// Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
// Output: ["the","is","sunny","day"]
// Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
 
// Constraints:

// 1 <= words.length <= 500
// 1 <= words[i].length <= 10
// words[i] consists of lowercase English letters.
// k is in the range [1, The number of unique words[i]]
 
// Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?

// Solution:

import java.util.*;

public class kFrequencyWord {

    // Method to find the top k frequent words in the given array
    public List<String> topKFrequent(String[] words, int k) {

        // HashMap to store word frequencies
        HashMap<String, Integer> map = new HashMap<>();

        // Count frequencies of each word
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        // List to store the result
        List<String> res = new ArrayList<>();

        // Sorting the array of words
        Arrays.sort(words);

        // Iterate until k frequent words are found
        while (k-- > 0) {
            int max = 0;         // Variable to store the maximum frequency
            String s = "";       // Variable to store the word with maximum frequency

            // Iterate through the sorted array of words
            for (int i = 0; i < words.length; i++) {
                if (map.containsKey(words[i])) {
                    int c = map.get(words[i]);

                    // Update max and s if a word has a higher frequency
                    if (c > max) {
                        max = c;
                        s = words[i];
                    }
                }
            }

            // Remove the word with the maximum frequency from the map
            map.remove(s);

            // Add the word to the result list if not already present
            if (!res.contains(s)) {
                res.add(s);
            }
        }

        // Return the final result list
        return res;
    }
}
