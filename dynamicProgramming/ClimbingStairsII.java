// package dynamicprogramming;

/*
    You are climbing a staircase with n + 1 steps, numbered from 0 to n.
    You are also given a 1-indexed integer array costs of length n, where costs[i] is the cost of step i.
    From step i, you can jump only to step i + 1, i + 2, or i + 3. The cost of jumping from step i to step j is defined as: costs[j] + (j - i)2
    You start from step 0 with cost = 0.
    Return the minimum total cost to reach step n.

    Example 1:
    Input: n = 4, costs = [1,2,3,4]
    Output: 13
    Explanation:
    One optimal path is 0 → 1 → 2 → 4
    Jump	Cost Calculation	Cost
    0 → 1	costs[1] + (1 - 0)2 = 1 + 1	2
    1 → 2	costs[2] + (2 - 1)2 = 2 + 1	3
    2 → 4	costs[4] + (4 - 2)2 = 4 + 4	8
    Thus, the minimum total cost is 2 + 3 + 8 = 13

    Example 2:
    Input: n = 4, costs = [5,1,6,2]
    Output: 11
    Explanation:
    One optimal path is 0 → 2 → 4
    Jump	Cost Calculation	Cost
    0 → 2	costs[2] + (2 - 0)2 = 1 + 4	5
    2 → 4	costs[4] + (4 - 2)2 = 2 + 4	6
    Thus, the minimum total cost is 5 + 6 = 11

    Example 3:
    Input: n = 3, costs = [9,8,3]
    Output: 12
    Explanation:
    The optimal path is 0 → 3 with total cost = costs[3] + (3 - 0)2 = 3 + 9 = 12

    Constraints:
    1 <= n == costs.length <= 10^5​​​​​​​
    1 <= costs[i] <= 10^4

    Approach: We can use dynamic programming to solve this problem. We can create a dp array where dp[i] represents the minimum cost to reach step i. We can initialize dp[0] = 0 and then iterate from n to 1, calculating the minimum cost for each step based on the previous three steps. The final answer will be stored in dp[0].
*/

import java.util.Arrays;

class ClimbingStairsII {
    class Solution {
        int memo[];

        private int findMinCost(int n, int[] costs, int i) {
            if (i >= n) {
                return 0;
            }

            if (memo[i] != -1)
                return memo[i];

            int oneStep = Integer.MAX_VALUE, twoStep = Integer.MAX_VALUE, threeStep = Integer.MAX_VALUE;
            if (i + 1 <= n) {
                int next = i + 1;
                oneStep = costs[next - 1] + 1 + findMinCost(n, costs, i + 1);
            }

            if (i + 2 <= n) {
                int next = i + 2;
                twoStep = costs[next - 1] + 4 + findMinCost(n, costs, i + 2);
            }

            if (i + 3 <= n) {
                int next = i + 3;
                threeStep = costs[next - 1] + 9 + findMinCost(n, costs, i + 3);
            }

            return memo[i] = Math.min(oneStep, Math.min(twoStep, threeStep));

        }

        public int climbStairs(int n, int[] costs) {
            memo = new int[n + 1];
            Arrays.fill(memo, -1);

            return findMinCost(n, costs, 0);
        }
    }

    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int oneStep = Integer.MAX_VALUE, twoStep = Integer.MAX_VALUE, threeStep = Integer.MAX_VALUE;
            if (i + 1 <= n) {
                int next = i + 1;
                oneStep = costs[next - 1] + 1 + dp[i + 1];
            }

            if (i + 2 <= n) {
                int next = i + 2;
                twoStep = costs[next - 1] + 4 + dp[i + 2];
            }

            if (i + 3 <= n) {
                int next = i + 3;
                threeStep = costs[next - 1] + 9 + dp[i + 3];
            }

            dp[i] = Math.min(oneStep, Math.min(twoStep, threeStep));
        }

        return dp[0];
    }
}
