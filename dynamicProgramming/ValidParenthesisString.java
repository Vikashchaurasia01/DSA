package dynamicProgramming;

/*
    Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

    The following rules define a valid string:

    Any left parenthesis '(' must have a corresponding right parenthesis ')'.
    Any right parenthesis ')' must have a corresponding left parenthesis '('.
    Left parenthesis '(' must go before the corresponding right parenthesis ')'.
    '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
    

    Example 1:

    Input: s = "()"
    Output: true
    Example 2:

    Input: s = "(*)"
    Output: true
    Example 3:

    Input: s = "(*))"
    Output: true
    Example 4:

    Input: s = "("
    Output: false
    

    Constraints:

    1 <= s.length <= 100
    s[i] is '(', ')' or '*'.

    problem link: https://leetcode.com/problems/valid-parenthesis-string/

    Approach: Here as we can see that '*' have three options, either it should be null, left paranthesis or right paranthesis.
              if we get char as '(', then we will increase the balance by 1,
              if we get char as ')', then we will decrease the balance by 1 and if we get char as '*',
              then we will check for all three options using recursive approach to explore all the paths and return the valid res from any path if we get.
*/

public class ValidParenthesisString {
    Boolean[][] memo;

    private boolean solve(String s, int idx, int bal) {
        if(idx >= s.length()) return bal == 0;

        if(bal < 0) return false;

        if(memo[idx][bal] != null)
            return memo[idx][bal];

        char ch = s.charAt(idx);
        if(ch == '(') {
            return memo[idx][bal] = solve(s, idx + 1, bal + 1);
        }

        if(ch == ')') {
            return memo[idx][bal] = solve(s, idx + 1, bal - 1);
        }

        boolean left = solve(s, idx + 1, bal + 1);
        boolean right = solve(s, idx + 1, bal - 1);
        boolean nullStr = solve(s, idx + 1, bal);

        return memo[idx][bal] = left || right || nullStr;
    }

    public boolean checkValidString(String s) {
        memo = new Boolean[101][101];
        return solve(s, 0, 0); // string, idx, bal
    }
}
