// Problem Statement:

// You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.

// Return a list answer of size 2 where:

// answer[0] is a list of all players that have not lost any matches.
// answer[1] is a list of all players that have lost exactly one match.
// The values in the two lists should be returned in increasing order.

// Note:

// You should only consider the players that have played at least one match.
// The testcases will be generated such that no two matches will have the same outcome.
 
// Example 1:
// Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
// Output: [[1,2,10],[4,5,7,8]]
// Explanation:
// Players 1, 2, and 10 have not lost any matches.
// Players 4, 5, 7, and 8 each have lost one match.
// Players 3, 6, and 9 each have lost two matches.
// Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
  
// Example 2:
// Input: matches = [[2,3],[1,3],[5,4],[6,4]]
// Output: [[1,2,5,6],[]]
// Explanation:
// Players 1, 2, 5, and 6 have not lost any matches.
// Players 3 and 4 each have lost two matches.
// Thus, answer[0] = [1,2,5,6] and answer[1] = [].
 
// Constraints:

// 1 <= matches.length <= 105
// matches[i].length == 2
// 1 <= winneri, loseri <= 105
// winneri != loseri
// All matches[i] are unique.

// Solution:

class LossesFinder {

     // Finds and categorizes winners and losers from a 2D array representing matches.
    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> ans = new ArrayList<>();

        // HashMap to store the count of matches lost by each player.
        HashMap<Integer, Integer> map = new HashMap<>();

        // Iterate through matches to update the HashMap.
        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];

            // Update count of matches lost for the loser.
            if (map.containsKey(loser)) {
                map.put(loser, map.get(loser) + 1);
            }

            // Initialize count for the winner if not present.
            if (!map.containsKey(winner)) {
                map.put(winner, 0);
            }

            // Initialize count for the loser if not present.
            if (!map.containsKey(loser)) {
                map.put(loser, 1);
            }
        }

        // Lists to store winners and losers.
        ArrayList<Integer> winners = new ArrayList<>();
        ArrayList<Integer> losers = new ArrayList<>();

        // Iterate through the HashMap to categorize players as winners or losers.
        map.forEach((key, value) -> {
            if (value == 0) {
                winners.add(key);
            }
            if (value == 1) {
                losers.add(key);
            }
        });

        // Sort winners and losers lists.
        Collections.sort(winners);
        Collections.sort(losers);

        // Add winners and losers lists to the result.
        ans.add(winners);
        ans.add(losers);

        return ans;
    }
}
