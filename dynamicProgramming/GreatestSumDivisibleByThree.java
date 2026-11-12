package dynamicprogramming;

/*
    Given an integer array nums, return the maximum possible sum of elements of the array such that it is divisible by three.

    Example 1:
    Input: nums = [3,6,5,1,8]
    Output: 18
    Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).

    Example 2:
    Input: nums = [4]
    Output: 0
    Explanation: Since 4 is not divisible by 3, do not pick any number.

    Example 3:
    Input: nums = [1,2,3,4,4]
    Output: 12
    Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
    
    Constraints:
    1 <= nums.length <= 4 * 10^4
    1 <= nums[i] <= 10^4
*/

import java.util.Arrays;

public class GreatestSumDivisibleByThree {
    class Solution {
        int[][] memo;

        private int solve(int[] nums, int n, int idx, int rem) {
            if (idx >= n)
                return rem == 0 ? 0 : Integer.MIN_VALUE;

            if (memo[idx][rem] != -1) {
                return memo[idx][rem];
            }

            int take = nums[idx] + solve(nums, n, idx + 1, (rem + nums[idx]) % 3);
            int skip = solve(nums, n, idx + 1, rem);

            return memo[idx][rem] = Math.max(take, skip);
        }

        public int maxSumDivThree(int[] nums) {
            int n = nums.length;

            memo = new int[n + 1][3];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return solve(nums, n, 0, 0);
        }
    }
}