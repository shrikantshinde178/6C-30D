// Problem Statement:
//  Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

// Return the sorted string. If there are multiple answers, return any of them.

// Example 1:

// Input: s = "tree"
// Output: "eert"
// Explanation: 'e' appears twice while 'r' and 't' both appear once.
// So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
// Example 2:

// Input: s = "cccaaa"
// Output: "aaaccc"
// Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
// Note that "cacaca" is incorrect, as the same characters must be together.
// Example 3:

// Input: s = "Aabb"
// Output: "bbAa"
// Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
// Note that 'A' and 'a' are treated as two different characters.
 
// Constraints:

// 1 <= s.length <= 5 * 105
// s consists of uppercase and lowercase English letters and digits.

// Solution:

class CharacterSortByFrequency {
       //Sorts characters in the given string based on their frequency in descending order.
    public String frequencySort(String s) {
        // Initialize a 2D array to store character frequencies [ASCII][[character, frequency]]
        int[][] arr = new int[128][2];

        // Count the frequency of each character in the input string
        for (char ch : s.toCharArray()) {
            arr[(int) ch][0] = (int) ch;
            arr[(int) ch][1]++;
        }

        // Sort the array based on character frequency in descending order
        Arrays.sort(arr, (a, b) -> b[1] - a[1]);

        // Build the sorted string using the sorted array
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            // Check if the character is a printable ASCII character
            if (arr[i][0] >= 48) {
                // Append the character to the result string based on its frequency
                for (int j = 1; j <= arr[i][1]; j++) {
                    str.append((char) arr[i][0]);
                }
            }
        }

        // Return the final sorted string
        return str.toString();
    }
}
