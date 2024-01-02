// // Problem Statement :
// Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

// A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

// Example 1:

// Input: digits = "23"
// Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
// Example 2:

// Input: digits = ""
// Output: []
// Example 3:

// Input: digits = "2"
// Output: ["a","b","c"]
 
// Constraints:
// 0 <= digits.length <= 4
// digits[i] is a digit in the range ['2', '9'].

//Solution
class PhoneMunberCombination {
    // Method to generate letter combinations for a given digit string
    public List<String> letterCombinations(String digits) {
        String p = "";
        ArrayList<String> list = new ArrayList<>();

        // Check if the input is null or empty
        if (digits == null || digits.length() == 0) {
            return new ArrayList();
        }

        // Call helper method to generate combinations
        list = pad(p, digits);
        return list;
    }

    // Helper method to recursively generate letter combinations
    public static ArrayList<String> pad(String p, String up) {
        // Base case: if the input string is empty, add the current combination to the list
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }

        // Extract the first digit from the input string
        int digit = up.charAt(0) - '0';
        
        // List to store the generated combinations for the current digit
        ArrayList<String> ans = new ArrayList<>();

        // Generate combinations based on the digit
        if (digit <= 6) {
            for (int i = ((digit - 2) * 3); i < ((digit - 1) * 3); i++) {
                char ch = (char) ('a' + i);
                ans.addAll(pad(p + ch, up.substring(1)));
            }
        } else if (digit == 7) {
            for (int i = ((digit - 2) * 3); i <= ((digit - 1) * 3); i++) {
                char ch = (char) ('a' + i);
                ans.addAll(pad(p + ch, up.substring(1)));
            }
        } else if (digit == 8) {
            for (int i = ((digit - 2) * 3) + 1; i <= ((digit - 1) * 3); i++) {
                char ch = (char) ('a' + i);
                ans.addAll(pad(p + ch, up.substring(1)));
            }
        } else if (digit == 9) {
            for (int i = ((digit - 2) * 3) + 1; i <= ((digit - 1) * 3) + 1; i++) {
                char ch = (char) ('a' + i);
                ans.addAll(pad(p + ch, up.substring(1)));
            }
        }

        return ans;
    } 
}


