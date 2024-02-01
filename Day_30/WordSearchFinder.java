Problem Statement:
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 
Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 
Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 104 calls will be made to addWord and search.

Hint 1
You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.

Solution:

/**
 * WordDictionary class representing a dictionary using a Trie structure.
 */
class WordDictionary {
    TrieNode root = null;  // Root of the Trie

    /**
     * TrieNode class representing a node in the Trie data structure.
     */
    private class TrieNode {
        TrieNode[] children;  // Array to store child nodes for each letter
        boolean isEnd;        // Flag to indicate the end of a word
        
        // Constructor to initialize a TrieNode
        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEnd = false;
        }
    }

    // Constructor to initialize a WordDictionary with an empty Trie
    public WordDictionary() {
        this.root = new TrieNode();
    }
    
    /**
     * Adds a word to the Trie.
     * @param word The word to be added to the Trie.
     */
    public void addWord(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (current.children[index] == null) {
                TrieNode node = new TrieNode();
                current.children[index] = node;
                current = node;
            } else {
                current = current.children[index];
            }
        }
        current.isEnd = true;
    }
    
    /**
     * Searches for a word in the Trie.
     * @param word The word to be searched.
     * @return True if the word is found, false otherwise.
     */
    public boolean search(String word) {
        return search(word, 0, root);
    }

    /**
     * Recursive helper method for searching a word in the Trie.
     * @param word The word to be searched.
     * @param pos The current position in the word.
     * @param node The current TrieNode being processed.
     * @return True if the word is found, false otherwise.
     */
    private boolean search(String word, int pos, TrieNode node) {
        if (node == null) {
            return false;
        }

        if (pos == word.length()) {
            return node.isEnd;
        }

        char ch = word.charAt(pos);

        if (ch == '.') {
            // Handle wildcard character '.'
            for (int i = 0; i < 26; i++) {
                if (search(word, pos + 1, node.children[i])) {
                    return true;
                }
            }
            return false;
        }

        return search(word, pos + 1, node.children[ch - 'a']);
    }
}
