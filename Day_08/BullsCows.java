// Problem Statement:
// You are playing the Bulls and Cows game with your friend.

// You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:

// The number of "bulls", which are digits in the guess that are in the correct position.
// The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
// Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.

// The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.

// Example 1:

// Input: secret = "1807", guess = "7810"
// Output: "1A3B"
// Explanation: Bulls are connected with a '|' and cows are underlined:
// "1807"
//   |
// "7810"
// Example 2:

// Input: secret = "1123", guess = "0111"
// Output: "1A1B"
// Explanation: Bulls are connected with a '|' and cows are underlined:
// "1123"        "1123"
//   |      or     |
// "0111"        "0111"
// Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
 
// Constraints:

// 1 <= secret.length, guess.length <= 1000
// secret.length == guess.length
// secret and guess consist of digits only.

// Solution
class BullsCows {

    public String getHint(String secret, String guess) {
        // Map to store the frequency of each character in the secret number
        Map<Character, Integer> bulls = new HashMap<>();
        // Set to keep track of characters already identified as bulls
        Set<Character> bullsSet = new HashSet<>();

        // Count the frequency of each character in the secret number
        for (int i = 0; i < secret.length(); i++) {
            char bull = secret.charAt(i);
            int frequency = bulls.getOrDefault(bull, 0);
            frequency++;
            bulls.put(bull, frequency);
            bullsSet.add(bull);
        }

        // Variables to store the total number of bulls and cows
        int bullsTotal = 0;
        int cows = 0;

        // Check for bulls and update the frequency map
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                bullsTotal++;
                int frequency = bulls.get(guess.charAt(i));
                frequency--;
                bulls.put(guess.charAt(i), frequency);
            }
        }

        // Check for cows among the remaining characters
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                continue;
            } else if (bullsSet.contains(guess.charAt(i))) {
                int frequency = bulls.get(guess.charAt(i));
                if (frequency > 0) {
                    cows++;
                    frequency--;
                    bulls.put(guess.charAt(i), frequency);
                }
            }
        }

        // Construct and return the hint string
        return String.valueOf(bullsTotal) + "A"  + String.valueOf(cows) + "B";
    }
}
