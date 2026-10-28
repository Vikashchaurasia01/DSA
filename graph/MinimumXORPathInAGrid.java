import java.util.Arrays;

/*
    You are given a 2D integer array grid of size m * n.
    You start at the top-left cell (0, 0) and want to reach the bottom-right cell (m - 1, n - 1).
    At each step, you may move either right or down.
    The cost of a path is defined as the bitwise XOR of all the values in the cells along that path, including the start and end cells.
    Return the minimum possible XOR value among all valid paths from (0, 0) to (m - 1, n - 1).

    Example 1:
    Input: grid = [[1,2],[3,4]]
    Output: 6

    Explanation:
    There are two valid paths:
    (0, 0) → (0, 1) → (1, 1) with XOR: 1 XOR 2 XOR 4 = 7
    (0, 0) → (1, 0) → (1, 1) with XOR: 1 XOR 3 XOR 4 = 6
    The minimum XOR value among all valid paths is 6.

    Example 2:
    Input: grid = [[6,7],[5,8]]
    Output: 9

    Explanation:
    There are two valid paths:
    (0, 0) → (0, 1) → (1, 1) with XOR: 6 XOR 7 XOR 8 = 9
    (0, 0) → (1, 0) → (1, 1) with XOR: 6 XOR 5 XOR 8 = 11
    The minimum XOR value among all valid paths is 9.

    Example 3:
    Input: grid = [[2,7,5]]
    Output: 0

    Explanation:
    There is only one valid path:

    (0, 0) → (0, 1) → (0, 2) with XOR: 2 XOR 7 XOR 5 = 0
    The XOR value of this path is 0, which is the minimum possible.

    Constraints:
    1 <= m == grid.length <= 1000
    1 <= n == grid[i].length <= 1000
    m * n <= 1000
    0 <= grid[i][j] <= 1023​
*/

public class MinimumXORPathInAGrid {
    class Solution {
        int m, n;

        public int minCost(int[][] grid) {
            m = grid.length;
            n = grid[0].length;

            int[][][] dp = new int[m][n][1024];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(dp[i][j], Integer.MAX_VALUE);
                }
            }

            for (int currXor = 0; currXor < 1024; currXor++) {
                dp[m - 1][n - 1][currXor] = currXor ^ grid[m - 1][n - 1];
            }

            int xor = 0;
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (i == m - 1 && j == n - 1) {
                        continue;
                    }

                    for (int currXor = 0; currXor < 1024; currXor++) {
                        int updatedXor = currXor ^ grid[i][j];
                        int minXor = Integer.MAX_VALUE;

                        if (i + 1 < m) {
                            minXor = Math.min(minXor, dp[i + 1][j][updatedXor]);
                        }

                        if (j + 1 < n) {
                            minXor = Math.min(minXor, dp[i][j + 1][updatedXor]);
                        }

                        dp[i][j][currXor] = minXor;
                    }
                }
            }

            return dp[0][0][0];
        }
    }

    int m, n;
    Integer[][][] memo;

    private int getMinCost(int[][] grid, int i, int j, int xor) {
        if (i >= m || j >= n) {
            return Integer.MAX_VALUE;
        }

        if (memo[i][j][xor] != null) {
            return memo[i][j][xor];
        }

        int updatedXor = xor ^ grid[i][j];

        if (i == m - 1 && j == n - 1) {
            return memo[i][j][xor] = updatedXor;
        }

        int right = getMinCost(grid, i, j + 1, updatedXor);
        int down = getMinCost(grid, i + 1, j, updatedXor);

        return memo[i][j][xor] = Math.min(right, down);
    }

    public int minCost(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        memo = new Integer[m + 1][n + 1][1024];

        return getMinCost(grid, 0, 0, 0);
    }
}
