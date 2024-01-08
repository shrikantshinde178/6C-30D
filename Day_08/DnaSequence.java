// Problem Statement:
// The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.

// For example, "ACGAATTCCG" is a DNA sequence.
// When studying DNA, it is useful to identify repeated sequences within the DNA.

// Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.

// Example 1:
// Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
// Output: ["AAAAACCCCC","CCCCCAAAAA"]

// Example 2:
// Input: s = "AAAAAAAAAAAAA"
// Output: ["AAAAAAAAAA"]
 
// Constraints:

// 1 <= s.length <= 105
// s[i] is either 'A', 'C', 'G', or 'T'.

// Solution 
class DnaSequence {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<Character, Integer> n2d = new HashMap<>();
        n2d.put('A', 0);
        n2d.put('C', 1);
        n2d.put('G', 2);
        n2d.put('T', 3);
        Set<Integer> candidates = new HashSet<>();
		
        Set<String> duplicates = new HashSet<>();
        int cur = 0;
        for (int i = 0; i < s.length(); i++)
        {
            // only keep at most 9 letters before the current letter
		    cur %= 1<<18;                
            cur = cur * 4 + n2d.get(s.charAt(i));
            if (i < 9) continue;
            if (candidates.contains(cur))
            {
                duplicates.add(s.substring(i-9, i+1));
            }
            else
            {
                candidates.add(cur);
            }
        }
        return new ArrayList<String>(duplicates);
    }
}
