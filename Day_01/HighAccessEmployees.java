//Problem Statement:
// You are given a 2D 0-indexed array of strings, access_times, with size n. For each i where 0 <= i <= n - 1, access_times[i][0] represents the name of an employee, and access_times[i][1] represents the access time of that employee. All entries in access_times are within the same day.
// The access time is represented as four digits using a 24-hour time format, for example, "0800" or "2250".
// An employee is said to be high-access if he has accessed the system three or more times within a one-hour period.
// Times with exactly one hour of difference are not considered part of the same one-hour period. For example, "0815" and "0915" are not part of the same one-hour period.
// Access times at the start and end of the day are not counted within the same one-hour period. For example, "0005" and "2350" are not part of the same one-hour period.
// Return a list that contains the names of high-access employees with any order you want.

// Example 1:

// Input: access_times = [["a","0549"],["b","0457"],["a","0532"],["a","0621"],["b","0540"]]
// Output: ["a"]
// Explanation: "a" has three access times in the one-hour period of [05:32, 06:31] which are 05:32, 05:49, and 06:21.
// But "b" does not have more than two access times at all.
// So the answer is ["a"].

// Example 2:

// Input: access_times = [["d","0002"],["c","0808"],["c","0829"],["e","0215"],["d","1508"],["d","1444"],["d","1410"],["c","0809"]]
// Output: ["c","d"]
// Explanation: "c" has three access times in the one-hour period of [08:08, 09:07] which are 08:08, 08:09, and 08:29.
// "d" has also three access times in the one-hour period of [14:10, 15:09] which are 14:10, 14:44, and 15:08.
// However, "e" has just one access time, so it can not be in the answer and the final answer is ["c","d"].

// Constraints:

// 1 <= access_times.length <= 100
// access_times[i].length == 2
// 1 <= access_times[i][0].length <= 10
// access_times[i][0] consists only of English small letters.
// access_times[i][1].length == 4
// access_times[i][1] is in 24-hour time format.
// access_times[i][1] consists only of '0' to '9'.

//Solution
import java.util.*;

public class HighAccessEmployees {
    // Function to find high-access employees based on access times
    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        // Map to store access times for each employee
        Map<String, PriorityQueue<String>> map = new HashMap();

        // Populate the map with employee names and their access times
        for (List<String> a : access_times) {
            String name = a.get(0), time = a.get(1);
            map.computeIfAbsent(name, k -> new PriorityQueue()).offer(time);
        }

        // List to store high-access employees
        List<String> res = new ArrayList();

        // Iterate through the map to find high-access employees
        for (String n : map.keySet()) {
            PriorityQueue<String> pq = map.get(n);

            // Skip employees with less than 3 access times
            if (pq.size() < 3) continue;

            int prev2 = Integer.valueOf(pq.poll());
            int prev1 = Integer.valueOf(pq.poll());

            // Check for high-access employees
            while (!pq.isEmpty()) {
                int curr = Integer.valueOf(pq.poll());
                if (curr - prev2 < 100) {
                    res.add(n);
                    break;
                }
                prev2 = prev1;
                prev1 = curr;
            }
        }

        return res;
    }
}
