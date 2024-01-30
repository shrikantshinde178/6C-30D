// Problem Statement:
// You are building a string s of length n one character at a time, prepending each new character to the front of the string. The strings are labeled from 1 to n, where the string with length i is labeled si.

// For example, for s = "abaca", s1 == "a", s2 == "ca", s3 == "aca", etc.
// The score of si is the length of the longest common prefix between si and sn (Note that s == sn).

// Given the final string s, return the sum of the score of every si.

// Example 1:

// Input: s = "babab"
// Output: 9
// Explanation:
// For s1 == "b", the longest common prefix is "b" which has a score of 1.
// For s2 == "ab", there is no common prefix so the score is 0.
// For s3 == "bab", the longest common prefix is "bab" which has a score of 3.
// For s4 == "abab", there is no common prefix so the score is 0.
// For s5 == "babab", the longest common prefix is "babab" which has a score of 5.
// The sum of the scores is 1 + 0 + 3 + 0 + 5 = 9, so we return 9.
// Example 2:

// Input: s = "azbazbzaz"
// Output: 14
// Explanation: 
// For s2 == "az", the longest common prefix is "az" which has a score of 2.
// For s6 == "azbzaz", the longest common prefix is "azb" which has a score of 3.
// For s9 == "azbazbzaz", the longest common prefix is "azbazbzaz" which has a score of 9.
// For all other si, the score is 0.
// The sum of the scores is 2 + 3 + 9 = 14, so we return 14.
 
// Constraints:

// 1 <= s.length <= 105
// s consists of lowercase English letters.

// Hint 1
// Each s_i is a suffix of the string s, so consider algorithms that can determine the longest prefix that is also a suffix.
// Hint 2
// Could you use the Z array from the Z algorithm to find the score of each s_i?

// Solution:

class BuiltStringsCode {
    /**
     * Calculates the sum of Z-array scores for a given string.
     *
     * @param s The input string.
     * @return The sum of Z-array scores.
     */
    public long sumScores(String s) {
        char[] a = s.toCharArray();
        int[] z = zarray(a);
        long count = 0;
        for (int i : z)
            count += i;
        return count;
    }

    /**
     * Generates the Z-array for a given character array.
     *
     * @param a The input character array.
     * @return The Z-array.
     */
    public int[] zarray(char[] a) {
        int[] z = new int[a.length];
        z[0] = a.length;
        int left = 0;
        int right = 0;

        for (int k = 1; k < a.length; k++) {
            if (k > right) {
                left = right = k;
                while (right < a.length && a[right] == a[right - left])
                    right++;
                z[k] = right - left;
                right--;
            } else {
                int k1 = k - left;
                if (z[k1] < right - k + 1)
                    z[k] = z[k1];
                else {
                    left = k;
                    while (right < a.length && a[right] == a[right - left])
                        right++;
                    z[k] = right - left;
                    right--;
                }
            }
        }
        return z;
    }
}
