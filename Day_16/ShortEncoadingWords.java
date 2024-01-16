// Problem Statement:
// A valid encoding of an array of words is any reference string s and array of indices indices such that:

// words.length == indices.length
// The reference string s ends with the '#' character.
// For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
// Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.

// Example 1:

// Input: words = ["time", "me", "bell"]
// Output: 10
// Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
// words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
// words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
// words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
// Example 2:

// Input: words = ["t"]
// Output: 2
// Explanation: A valid encoding would be s = "t#" and indices = [0].
 
// Constraints:

// 1 <= words.length <= 2000
// 1 <= words[i].length <= 7
// words[i] consists of only lowercase letters.

// Solution:

class ShortEncoadingWords {
    
    // TrieNode class represents nodes in the Trie
    class TrieNode {
        Map<Character, TrieNode> map;
        boolean isEnd;
        
        // Constructor initializes the TrieNode with an empty map and isEnd flag set to false
        public TrieNode() {
            map = new HashMap<>();
            isEnd = false;
        }
    }
    
    private TrieNode ROOT;  // Trie's root
    
    // addToTrie method inserts a reversed word into the Trie
    void addToTrie(String str) {
        TrieNode currNode = ROOT;
        
        // Traverse the word in reverse order
        for (int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            
            // Check if the character is already present in the Trie
            if (currNode.map.containsKey(ch)) {
                currNode = currNode.map.get(ch);
            } else {
                // If not present, create a new TrieNode and add it to the current node's map
                TrieNode node = new TrieNode();
                currNode.map.put(ch, node);
                currNode = node;
            }
        }
        currNode.isEnd = true;  // Mark the end of the reversed word
    }
    
    int charHash;
    int minLength;
    
    // getLength method calculates the minimum length encoding by traversing the Trie
    void getLength(TrieNode root, int level) {
        // If the current TrieNode has no children (leaf node), update the minLength
        if (root.map.isEmpty()) {
            minLength += (level + charHash);
            return;
        }
        
        // Recursively traverse each child TrieNode
        for (char ch : root.map.keySet()) {
            getLength(root.map.get(ch), level + 1);
        }
    }
    
    // minimumLengthEncoding method initializes the Trie, adds words, and calculates the minimum length encoding
    public int minimumLengthEncoding(String[] words) {
        ROOT = new TrieNode();  // Initialize Trie root
        
        // Add each word to the Trie
        for (String word : words) {
            addToTrie(word);
        }
        
        minLength = 0;
        charHash = 1;
        
        // Calculate the minimum length encoding by traversing the Trie
        getLength(ROOT, 0);
        
        return minLength;
    }
}
