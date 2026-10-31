// package dynamicProgramming;

public class TwistedMirrorPathCount {
    class Solution {
        int m, n;
        Integer[][][] memo;
        private final int M = 1_000_000_007;

        public int uniquePaths(int[][] grid) {
            m = grid.length;
            n = grid[0].length;

            int[][][] dp = new int[m + 1][n + 1][2];
            dp[m - 1][n - 1][0] = 1;
            dp[m - 1][n - 1][1] = 1;

            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {

                    if (i == m - 1 && j == n - 1)
                        continue;

                    for (int dir = 0; dir < 2; dir++) {
                        int right = 0, down = 0;
                        if (grid[i][j] == 1) {
                            if (dir == 0) { // previously it was down
                                right = dp[i][j + 1][1];
                            } else { // previously it was right
                                down = dp[i + 1][j][0];
                            }
                        } else {
                            right = dp[i][j + 1][1];
                            down = dp[i + 1][j][0];
                        }

                        dp[i][j][dir] = (right % M + down % M) % M;
                    }
                }
            }

            return dp[0][0][0];
        }
    }

    int m, n;
    Integer[][][] memo;
    private final int M = 1_000_000_007;

    private int solve(int[][] grid, int i, int j, int dir) {
        if (i >= m || j >= n) {
            return 0;
        }

        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        if (memo[i][j][dir] != null) {
            return memo[i][j][dir];
        }

        int right = 0, down = 0;
        if (grid[i][j] == 1) {
            if (dir == 0) { // previously it was down
                right = solve(grid, i, j + 1, 1);
            } else { // previously it was right
                down = solve(grid, i + 1, j, 0);
            }
        } else {
            right = solve(grid, i, j + 1, 1);
            down = solve(grid, i + 1, j, 0);
        }

        return memo[i][j][dir] = (right % M + down % M) % M;
    }

    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        memo = new Integer[m][n][2];

        return solve(grid, 0, 0, 0) % M; // grid, i, j, dir -> down / right
    }
}
