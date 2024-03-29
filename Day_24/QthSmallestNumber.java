// Problem Statement:
// You are given a 0-indexed array of strings nums, where each string is of equal length and consists of only digits.

// You are also given a 0-indexed 2D integer array queries where queries[i] = [ki, trimi]. For each queries[i], you need to:

// Trim each number in nums to its rightmost trimi digits.
// Determine the index of the kith smallest trimmed number in nums. If two trimmed numbers are equal, the number with the lower index is considered to be smaller.
// Reset each number in nums to its original length.
// Return an array answer of the same length as queries, where answer[i] is the answer to the ith query.

// Note:

// To trim to the rightmost x digits means to keep removing the leftmost digit, until only x digits remain.
// Strings in nums may contain leading zeros.
 
// Example 1:

// Input: nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
// Output: [2,2,1,0]
// Explanation:
// 1. After trimming to the last digit, nums = ["2","3","1","4"]. The smallest number is 1 at index 2.
// 2. Trimmed to the last 3 digits, nums is unchanged. The 2nd smallest number is 251 at index 2.
// 3. Trimmed to the last 2 digits, nums = ["02","73","51","14"]. The 4th smallest number is 73.
// 4. Trimmed to the last 2 digits, the smallest number is 2 at index 0.
//    Note that the trimmed number "02" is evaluated as 2.
// Example 2:

// Input: nums = ["24","37","96","04"], queries = [[2,1],[2,2]]
// Output: [3,0]
// Explanation:
// 1. Trimmed to the last digit, nums = ["4","7","6","4"]. The 2nd smallest number is 4 at index 3.
//    There are two occurrences of 4, but the one at index 0 is considered smaller than the one at index 3.
// 2. Trimmed to the last 2 digits, nums is unchanged. The 2nd smallest number is 24.
 
// Constraints:

// 1 <= nums.length <= 100
// 1 <= nums[i].length <= 100
// nums[i] consists of only digits.
// All nums[i].length are equal.
// 1 <= queries.length <= 100
// queries[i].length == 2
// 1 <= ki <= nums.length
// 1 <= trimi <= nums[i].length

// Solution:

class QthSmallestNumber {

	private class Pair {
		String s; // no
		int index;
		Pair(String s, int index) {
			this.s = s;
			this.index = index;
		}
	}

	public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
		Map<Integer, List<Pair>> map = new HashMap<>();
		for(int i=0;i<nums.length;i++) {
			String str = nums[i];
			int n = str.length();
			int l = n;
			for(int j=0;j<n;j++) {
				map.putIfAbsent(l, new ArrayList<>());
				Pair pair = new Pair(str.substring(j), i);
				map.get(l--).add(pair);
			}
		}

//         for(Map.Entry<Integer, List<Pair>> entry: map.entrySet()){
//             System.out.print("Key is "+ entry.getKey());
//             for(Pair pair: entry.getValue()){
//                 System.out.print(" "+ pair.s);
//             }
//             System.out.println();

//         }

		int[] ans = new int[queries.length];

		int idx = 0;

		for(int[] query: queries) {

			int kthElement = query[0];
			int key = query[1];

			List<Pair> list = map.get(key);

			Collections.sort(list, (p1, p2) -> {
				if(p1.s.equals(p2.s))
					return p1.index - p2.index;
				return p1.s.compareTo(p2.s);
			});

			ans[idx++] = list.get(kthElement-1).index;
		}

		return ans;
	}
}
