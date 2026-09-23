/*
    problem link: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/

    1658. Minimum Operations to Reduce X to Zero
    Example 1:

    Input: nums = [1,1,4,2,3], x = 5
    Output: 2
    Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
    Example 2:

    Input: nums = [5,6,7,8,9], x = 4
    Output: -1
    Example 3:

    Input: nums = [3,2,20,1,1,3], x = 10
    Output: 5
    Explanation: The optimal solution is to remove the last three elements and the first two elements
    (5 operations in total) to reduce x to zero.

*/


/* User Brute force approach to solve this problem using recursion + memoization, but it's not efficient for large inputs and the given constraints are huge */
/* ------------------ Naive Approach--------------------- */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimumOperationsToReduceXtoZero {
    Map<String, Integer> memo;
    private int solve(int[] nums, int x, int start, int end) {
        if(x == 0)
            return 0;
        
        if(x < 0 || start > end) {
            return Integer.MAX_VALUE;
        }

        String key = "" + x + "_" + start + "_" + end;

        if(memo.containsKey(key))
            return memo.get(key);

        int takeStart = Integer.MAX_VALUE;
        if(nums[start] <= x) {
            int count = solve(nums, x - nums[start], start + 1, end);
            if(count != Integer.MAX_VALUE)
                takeStart = 1 + count;
        }

        int takeEnd = Integer.MAX_VALUE;
        if(nums[end] <= x) {
            int count = solve(nums, x - nums[end], start, end - 1);
            if(count != Integer.MAX_VALUE)
                takeEnd = 1 + count;
        }

        int count = Math.min(takeEnd, takeStart);
        memo.put(key, count);
        return count;
    }
    public int minOperations(int[] nums, int x) {
        memo = new HashMap<>();
        int res = solve(nums, x, 0, nums.length-1);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}


/* ------------------ Optimized Approach--------------------- */
/*
    This approach uses a sliding window technique to find the longest subarray with sum equal to (total_sum - x), 
    which minimizes the number of operations needed to reduce x to zero.

    Basically what we want here, we have to remove the elements from first and last,
    so the left sub array is contiguous and that sum will be totalSUm - x, 
    for finding the longest subarray sum equal to totalSum - x, we can use sliding window technique, 
    and the answer will be n - length of that subarray.
    
*/

class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        long totalSum = Arrays.stream(nums).sum();

        int res = Integer.MAX_VALUE;
        int target = (int)totalSum - x;

        int i = 0, j = 0;
        int sum = 0;
        for(; j < n; j++) {
            sum += nums[j];
            while(i <= j && sum > target) {
                sum -= nums[i];
                i++;
            }

            if(sum == target) {
                res = Math.min(res, n - (j - i + 1));
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}