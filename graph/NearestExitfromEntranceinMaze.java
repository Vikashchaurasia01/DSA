package graph;

/*
    You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.

    In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.

    Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.

    

    Example 1:


    Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
    Output: 1
    Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
    Initially, you are at the entrance cell [1,2].
    - You can reach [1,0] by moving 2 steps left.
    - You can reach [0,2] by moving 1 step up.
    It is impossible to reach [2,3] from the entrance.
    Thus, the nearest exit is [0,2], which is 1 step away.
    Example 2:


    Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
    Output: 2
    Explanation: There is 1 exit in this maze at [1,2].
    [1,0] does not count as an exit since it is the entrance cell.
    Initially, you are at the entrance cell [1,0].
    - You can reach [1,2] by moving 2 steps right.
    Thus, the nearest exit is [1,2], which is 2 steps away.
    Example 3:


    Input: maze = [[".","+"]], entrance = [0,0]
    Output: -1
    Explanation: There are no exits in this maze.
    

    Constraints:

    maze.length == m
    maze[i].length == n
    1 <= m, n <= 100
    maze[i][j] is either '.' or '+'.
    entrance.length == 2
    0 <= entrancerow < m
    0 <= entrancecol < n
    entrance will always be an empty cell.
*/

import java.util.LinkedList;
import java.util.Queue;

public class NearestExitfromEntranceinMaze {

    // Tried using DFS but got TLE coz 2^100 is a very huge value
    class Solution {
        int[] entrance;

        private int dfs(char[][] maze, int row, int col) {
            // 1. Out of bounds check
            if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
                return 100000;
            }

            // 2. Wall or already visited check
            if (maze[row][col] == '+') {
                return 100000;
            }

            // 3. Exit check: must be on the border AND NOT the entrance
            boolean isBorder = (row == 0 || col == 0 || row == maze.length - 1 || col == maze[0].length - 1);
            boolean isEntrance = (row == entrance[0] && col == entrance[1]);

            if (isBorder && !isEntrance) {
                return 0; // Found a valid exit, 0 additional steps needed from here
            }

            // Mark current cell as visited
            maze[row][col] = '+';

            int left = dfs(maze, row, col - 1);
            int right = dfs(maze, row, col + 1);
            int up = dfs(maze, row - 1, col);
            int down = dfs(maze, row + 1, col);

            // Backtrack
            maze[row][col] = '.';

            int minSteps = Math.min(left, Math.min(right, Math.min(up, down)));
            return minSteps >= 100000 ? 100000 : 1 + minSteps;
        }

        public int nearestExit(char[][] maze, int[] entrance) {
            this.entrance = entrance;
            int res = dfs(maze, entrance[0], entrance[1]);
            return res >= 100000 ? -1 : res;
        }
    }

    // To find the shortest path, always use BFS, IT TAKES MIN STPS TO FIND THE MIN STEPS.

    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length, n = maze[0].length;
        int row = entrance[0];
        int col = entrance[1];

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] { row, col });
        maze[row][col] = '+';

        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        int steps = 0;
        while (!que.isEmpty()) {
            int size = que.size();

            while (size-- > 0) {
                int[] pair = que.poll();
                int i = pair[0];
                int j = pair[1];

                if ((i == 0 || j == 0 || i == m - 1 || j == n - 1) && (i != row || j != col)) {
                    return steps;
                }

                for (int[] dir : directions) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];

                    if (ni < 0 || ni >= m || nj < 0 || nj >= n || maze[ni][nj] == '+')
                        continue;

                    que.add(new int[] { ni, nj });
                    maze[ni][nj] = '+';
                }
            }
            steps++;
        }

        return -1;
    }

}
