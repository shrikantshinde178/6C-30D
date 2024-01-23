// Problem Statement:
// You are given two 0-indexed arrays of strings startWords and targetWords. Each string consists of lowercase English letters only.

// For each string in targetWords, check if it is possible to choose a string from startWords and perform a conversion operation on it to be equal to that from targetWords.

// The conversion operation is described in the following two steps:

// Append any lowercase letter that is not present in the string to its end.
// For example, if the string is "abc", the letters 'd', 'e', or 'y' can be added to it, but not 'a'. If 'd' is added, the resulting string will be "abcd".
// Rearrange the letters of the new string in any arbitrary order.
// For example, "abcd" can be rearranged to "acbd", "bacd", "cbda", and so on. Note that it can also be rearranged to "abcd" itself.
// Return the number of strings in targetWords that can be obtained by performing the operations on any string of startWords.

// Note that you will only be verifying if the string in targetWords can be obtained from a string in startWords by performing the operations. The strings in startWords do not actually change during this process.

// Example 1:

// Input: startWords = ["ant","act","tack"], targetWords = ["tack","act","acti"]
// Output: 2
// Explanation:
// - In order to form targetWords[0] = "tack", we use startWords[1] = "act", append 'k' to it, and rearrange "actk" to "tack".
// - There is no string in startWords that can be used to obtain targetWords[1] = "act".
//   Note that "act" does exist in startWords, but we must append one letter to the string before rearranging it.
// - In order to form targetWords[2] = "acti", we use startWords[1] = "act", append 'i' to it, and rearrange "acti" to "acti" itself.
// Example 2:

// Input: startWords = ["ab","a"], targetWords = ["abc","abcd"]
// Output: 1
// Explanation:
// - In order to form targetWords[0] = "abc", we use startWords[0] = "ab", add 'c' to it, and rearrange it to "abc".
// - There is no string in startWords that can be used to obtain targetWords[1] = "abcd".
 
// Constraints:

// 1 <= startWords.length, targetWords.length <= 5 * 104
// 1 <= startWords[i].length, targetWords[j].length <= 26
// Each string of startWords and targetWords consists of lowercase English letters only.
// No letter occurs more than once in any string of startWords or targetWords.

// Hint 1
// Which data structure can be used to efficiently check if a string exists in startWords?
// Hint 2
// After appending a letter, all letters of a string can be rearranged in any possible way. How can we use this to reduce our search space while checking if a string in targetWords can be obtained from a string in startWords?

// Solution:
/**
 * This class provides a solution for counting the number of target words that can be formed by removing a single character 
 * from any of the start words and checking if the modified word exists in the set of start words.
 */
public class Solution {

    /**
     * Counts the number of target words that can be formed by removing a single character from any of the start words.
     *
     * @param startWords Array of starting words.
     * @param targetWords Array of target words to be checked.
     * @return The count of target words that can be formed.
     */
    public int wordCount(String[] startWords, String[] targetWords) {
        // HashSet to store sorted versions of start words for efficient lookup
        HashSet<String> set = new HashSet<>();

        // Add all start words in sorted order to check whether target words can be formed
        for (String s : startWords) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            set.add(new String(chars));
        }

        // Initialize count of target words that can be formed
        int count = 0;

        // Iterate through each target word
        for (String t : targetWords) {
            char[] chars = t.toCharArray();
            Arrays.sort(chars);

            // Create a new word by removing one character at a time and check if it exists in the HashSet
            String newWord = new String(chars);
            int wordLen = newWord.length();

            for (int i = 0; i < wordLen; i++) {
                // Removing one character and checking if the modified word exists in the HashSet
                String str = newWord.substring(0, i) + newWord.substring(i + 1);

                if (set.contains(str)) {
                    // Increment count if a valid word is found
                    count++;
                    break; // Move to the next target word
                }
            }
        }

        // Return the final count of target words that can be formed
        return count;
    }
}
