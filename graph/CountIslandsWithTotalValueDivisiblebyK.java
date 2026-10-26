import java.util.LinkedList;
import java.util.Queue;

/*
    You are given an m x n matrix grid and a positive integer k. An island is a group of positive integers (representing land) that are 4-directionally connected (horizontally or vertically).

    The total value of an island is the sum of the values of all cells in the island.

    Return the number of islands with a total value divisible by k.

    Example 1:

    Input: grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k = 5

    Output: 2

    Explanation:

    The grid contains four islands. The islands highlighted in blue have a total value that is divisible by 5, while the islands highlighted in red do not.

    Example 2:

    Input: grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3

    Output: 6

    Explanation:

    The grid contains six islands, each with a total value that is divisible by 3.

    Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 1000
    1 <= m * n <= 10^5
    0 <= grid[i][j] <= 10^6
    1 <= k <= 10^6
*/

public class CountIslandsWithTotalValueDivisiblebyK {

    // Using DFS
    class Solution {
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int m, n;

        private long checkValidIsland(int[][] grid, int i, int j) {
            if (i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == 0) {
                return 0;
            }

            long sum = grid[i][j];
            grid[i][j] = 0;

            for (int[] dir : directions) {
                int ni = i + dir[0];
                int nj = j + dir[1];

                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    sum += checkValidIsland(grid, ni, nj);
                }
            }

            return sum;
        }

        public int countIslands(int[][] grid, int k) {
            m = grid.length;
            n = grid[0].length;

            int resCount = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] != 0 && (int) (checkValidIsland(grid, i, j) % k) == 0) {
                        resCount++;
                    }
                }
            }

            return resCount;
        }
    }


    // Using BFS
    int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    int m, n;

    private boolean checkValidIsland(int[][] grid, int i, int j, int k) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] { i, j });
        long sum = 0;

        while (!que.isEmpty()) {
            int[] pair = que.poll();
            int i_ = pair[0];
            int j_ = pair[1];

            if (grid[i_][j_] == 0)
                continue;

            sum += grid[i_][j_];
            grid[i_][j_] = 0;

            for (int[] dir : directions) {
                int ni = i_ + dir[0];
                int nj = j_ + dir[1];

                if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] != 0) {
                    que.offer(new int[] { ni, nj });
                }
            }
        }

        return sum % k == 0;
    }

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;

        int resCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0 && checkValidIsland(grid, i, j, k)) {
                    resCount++;
                }
            }
        }

        return resCount;
    }
}
