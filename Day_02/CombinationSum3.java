// Problem Statement :
// Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

// Only numbers 1 through 9 are used.
// Each number is used at most once.
// Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
  
// Example 1:

// Input: k = 3, n = 7
// Output: [[1,2,4]]
// Explanation:
// 1 + 2 + 4 = 7
// There are no other valid combinations.
// Example 2:

// Input: k = 3, n = 9
// Output: [[1,2,6],[1,3,5],[2,3,4]]
// Explanation:
// 1 + 2 + 6 = 9
// 1 + 3 + 5 = 9
// 2 + 3 + 4 = 9
// There are no other valid combinations.
// Example 3:

// Input: k = 4, n = 1
// Output: []
// Explanation: There are no valid combinations.
// Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
 
// Constraints:

// 2 <= k <= 9
// 1 <= n <= 60

//Solution
class CombinationSum3 {
    // Main method to find combinations that sum up to the target
    public List<List<Integer>> combinationSum3(int k, int n) {
        // Initialize the result list to store valid combinations
        List<List<Integer>> result = new ArrayList<>();
        
        // Call the backtrack method to find combinations
        backtrack(result, new ArrayList<>(), k, n, 1);
        
        // Return the final result
        return result;
    }

    // Backtracking method to explore combinations
    private void backtrack(List<List<Integer>> result, List<Integer> current, int k, int target, int start) {
        // Base case: if k and target are both 0, add the current combination to the result
        if (k == 0 && target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Combinations by iterating through numbers from 'start' to 9
        for (int i = start; i <= 9; i++) {
            // Pruning: If the current number is greater than the remaining target, break the loop
            if (i > target) {
                break;
            }
            
            current.add(i);
            
            // Recursively call the backtrack method with updated parameters
            backtrack(result, current, k - 1, target - i, i + 1);
            
            // Backtrack: Remove the last added number to explore other combinations
            current.remove(current.size() - 1);
        }
    }
}
