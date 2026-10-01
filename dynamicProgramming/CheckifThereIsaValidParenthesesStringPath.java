package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
    A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:

    It is ().
    It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
    It can be written as (A), where A is a valid parentheses string.
    You are given an m x n matrix of parentheses grid. A valid parentheses string path in the grid is a path satisfying all of the following conditions:

    The path starts from the upper left cell (0, 0).
    The path ends at the bottom-right cell (m - 1, n - 1).
    The path only ever moves down or right.
    The resulting parentheses string formed by the path is valid.
    Return true if there exists a valid parentheses string path in the grid. Otherwise, return false.

    Example 1:

    Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
    Output: true
    Explanation: The above diagram shows two possible paths that form valid parentheses strings.
    The first path shown results in the valid parentheses string "()(())".
    The second path shown results in the valid parentheses string "((()))".
    Note that there may be other valid parentheses string paths.
    
    Example 2:

    Input: grid = [[")",")"],["(","("]]
    Output: false
    Explanation: The two possible paths form the parentheses strings "))(" and ")((". Since neither of them are valid parentheses strings, we return false.
    
    Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 100
    grid[i][j] is either '(' or ')'.
*/

// ------------------------ recursion + memoization -----------------------
public class CheckifThereIsaValidParenthesesStringPath {
    int m, n;
    Map<String, Boolean> memo;
    private boolean checkParentheses(String s) {
        Stack<Character> st = new Stack<>();

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                st.push(ch);
            } else {
                if(st.isEmpty())
                    return false;
                st.pop();
            }
        }

        return st.isEmpty();
    }
    private boolean solve(char[][] grid, int i, int j, int open) {
        if(i >= m || j >= n) {
            return false;
        }

        open += grid[i][j] == '(' ? 1 : -1;

        if(open < 0)
            return false;
            
        if(i == m-1 && j == n-1){
            return open == 0;
        }

        String key = open + "_" + i + "_" + j;

        if(memo.containsKey(key))
            return memo.get(key);


        boolean down = solve(grid, i+1, j, open);
        boolean right = solve(grid, i, j+1, open);

        boolean res = down || right;
        memo.put(key, res);

        return res;
    }
    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        memo = new HashMap<>();
        return solve(grid, 0, 0, 0);
    }
}

// -------------------------- Bottom-up -------------------------
class Solution {
    int m, n;
    boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        if(grid[0][0] == ')') return false;

        memo = new boolean[101][101][201];
        // return solve(grid, 0, 0, 0);

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int open = 0; open <= i + j + 1; open++) {
                    if (i == m - 1 && j == n - 1) {
                        memo[i][j][open] = (open == 0);
                        continue;
                    }

                    if(i+1 < m){
                        int nextOpen = grid[i+1][j] == '(' ? open + 1 : open - 1;
                        if(nextOpen >= 0){
                            memo[i][j][open] = memo[i][j][open] || memo[i+1][j][nextOpen];
                        }
                    }

                    if(j+1 < n) {
                        int nextOpen = grid[i][j+1] == '(' ? open + 1 : open - 1;
                        if(nextOpen >= 0)
                            memo[i][j][open] = memo[i][j][open] || memo[i][j+1][nextOpen];
                    }
                }
            }
        }

        return memo[0][0][1];
    }
}
