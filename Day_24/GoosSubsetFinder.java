// Problem Statement:

// You are given an integer array nums. We call a subset of nums good if its product can be represented as a product of one or more distinct prime numbers.

// For example, if nums = [1, 2, 3, 4]:
// [2, 3], [1, 2, 3], and [1, 3] are good subsets with products 6 = 2*3, 6 = 2*3, and 3 = 3 respectively.
// [1, 4] and [4] are not good subsets with products 4 = 2*2 and 4 = 2*2 respectively.
// Return the number of different good subsets in nums modulo 109 + 7.

// A subset of nums is any array that can be obtained by deleting some (possibly none or all) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.

// Example 1:

// Input: nums = [1,2,3,4]
// Output: 6
// Explanation: The good subsets are:
// - [1,2]: product is 2, which is the product of distinct prime 2.
// - [1,2,3]: product is 6, which is the product of distinct primes 2 and 3.
// - [1,3]: product is 3, which is the product of distinct prime 3.
// - [2]: product is 2, which is the product of distinct prime 2.
// - [2,3]: product is 6, which is the product of distinct primes 2 and 3.
// - [3]: product is 3, which is the product of distinct prime 3.
// Example 2:

// Input: nums = [4,2,3,15]
// Output: 5
// Explanation: The good subsets are:
// - [2]: product is 2, which is the product of distinct prime 2.
// - [2,3]: product is 6, which is the product of distinct primes 2 and 3.
// - [2,15]: product is 30, which is the product of distinct primes 2, 3, and 5.
// - [3]: product is 3, which is the product of distinct prime 3.
// - [15]: product is 15, which is the product of distinct primes 3 and 5.
 

// Constraints:

// 1 <= nums.length <= 105
// 1 <= nums[i] <= 30

// Solution:
class Solution {
    static int MOD = 1_000_000_000 + 7;

    // These numbers contain duplicate factors (e.g 4, 8, 9, 25), will be excluded
    static List<Integer> excludes = new ArrayList<>();

    // Distinct prime factors of composites
    // e.g 6 = 2 * 3, 15 = 3 * 5, 30 = 2 * 3 * 5
    static List<Integer>[] factors = new List[31];

    // Coprime numbers permutation
    // Coprime means some composites don't have common factor and can coexist
    // e.g. 14 = 2 * 7 and 15 = 3 * 5
    static List<int[]> coprimes_pmt = new ArrayList<>();

    static {
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        int[] masks = new int[31];

        for (int i = 4; i <= 30; i++) {
            // exclude 4, 8, 9, 25 ...
            if (i % 4 == 0 || i % 9 == 0 || i % 25 == 0) {
                excludes.add(i);
                continue;
            }

            if (primes.contains(i)) {
                continue;
            }

            // Set distinct prime factors of composites
            for (int j = 0; j < primes.size(); j++) {
                if (i % primes.get(j) == 0) {
                    if (factors[i] == null) {
                        factors[i] = new ArrayList<>();
                    }
                    factors[i].add(primes.get(j));
                    masks[i] |= (1 << j);
                }
            }
        }

        // Recursively build coprime permutation
        buildCoprimes(0, masks, 0, new int[]{});
    }

    static void buildCoprimes(int mask, int[] masks, int num, int[] prev) {
        for (; num < masks.length; num++) {
            if (masks[num] > 0 && (mask & masks[num]) == 0) {
                int[] arr = Arrays.copyOf(prev, prev.length + 1);
                arr[prev.length] = num;
                coprimes_pmt.add(arr);
                buildCoprimes(mask | masks[num], masks, num + 1, arr);
            }
        }
    }

    public int numberOfGoodSubsets(int[] nums) {

        int[] prime_count = new int[31];
        int[] composite_count = new int[31];

        for (int num : nums) {
            prime_count[num]++;
        }

        // exclude numbers having duplicate factors, like 4, 8, 9, 25...
        for (int ex : excludes) {
            prime_count[ex] = 0;
        }

        // split prime numbers and composite numbers
        for (int i = 0; i < prime_count.length; i++) {
            if (factors[i] != null) {
                composite_count[i] = prime_count[i];
                prime_count[i] = 0;
            }
        }

        // sum result for prime numbers
        long result = sum(prime_count, null);

        // sum result for coprime numbers
        for (int[] coprimes : coprimes_pmt) {
            long count_mul = 1;
            for (int composite : coprimes) {
                count_mul *= composite_count[composite];
            }

            if (count_mul > 0) {
                result = (result + (sum(prime_count, coprimes) + 1) * count_mul) % MOD;
            }
        }

        // Each `1` will double the result
        while (prime_count[1] > 0) {
            result = (result * 2) % MOD;
            prime_count[1]--;
        }

        return (int) result;
    }

    int sum(int[] prime_count, int[] coprimes) {
        int[] dp = Arrays.copyOf(prime_count, prime_count.length);

        // Exclude prime factors of coprime numbers
        if (coprimes != null) {
            for (int composite : coprimes) {
                for (int factor : factors[composite]) {
                    dp[factor] = 0;
                }
            }
        }

        for (int i = 3; i <= 29 ; i++) {
            dp[i] = (int) ((dp[i - 1] + 1L * dp[i - 1] * dp[i] + dp[i]) % MOD);
        }
        return dp[29];
    }
}
