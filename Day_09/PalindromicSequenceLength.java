// Problem Statement:
// Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized. The two subsequences are disjoint if they do not both pick a character at the same index.

// Return the maximum possible product of the lengths of the two palindromic subsequences.

// A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters. A string is palindromic if it reads the same forward and backward.

// Example 1:

// example-1
// Input: s = "leetcodecom"
// Output: 9
// Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
// The product of their lengths is: 3 * 3 = 9.
// Example 2:

// Input: s = "bb"
// Output: 1
// Explanation: An optimal solution is to choose "b" (the first character) for the 1st subsequence and "b" (the second character) for the 2nd subsequence.
// The product of their lengths is: 1 * 1 = 1.
// Example 3:

// Input: s = "accbcaxxcxx"
// Output: 25
// Explanation: An optimal solution is to choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
// The product of their lengths is: 5 * 5 = 25.

// Constraints:

// 2 <= s.length <= 12
// s consists of lowercase English letters only.

// Solution:
// This class provides a solution to find the maximum product of the lengths of two

class PalindromicSequenceLength {

// Calculates the maximum product of the lengths of two non-overlapping palindromic

    public int maxProduct(String s) {
        int N = s.length();
        HashMap<Integer, Integer> palindromes = new HashMap<>();

        // Iterate through all possible subsequences and store palindromic ones in a HashMap.
        for (int mask = 1; mask < 1 << N; ++mask) {
            StringBuilder sb = new StringBuilder("");
            for (int j = 0; j < N; ++j) {
                if ((mask & (1 << j)) > 0) { // finding a subsequence
                    sb.append(s.charAt(j));
                }
            }
            String curr = sb.toString();
            String rev = sb.reverse().toString();
            if (rev.equals(curr)) {
                palindromes.put(mask, rev.length());
            }
        }

        int result = 0;

        // Iterate through all pairs of disjoint palindromic subsequences and find the maximum product.
        for (Map.Entry<Integer, Integer> it : palindromes.entrySet()) {
            for (Map.Entry<Integer, Integer> jt : palindromes.entrySet()) {
                if ((it.getKey() & jt.getKey()) == 0) { // if disjoint
                    result = Math.max(result, it.getValue() * jt.getValue());
                }
            }
        }

        return result;
    }
}
