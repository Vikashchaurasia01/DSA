// package backtracking;
/*
    Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

    Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.

    Example 1:

    Input: s = "()())()"
    Output: ["(())()","()()()"]
    Example 2:

    Input: s = "(a)())()"
    Output: ["(a())()","(a)()()"]
    Example 3:

    Input: s = ")("
    Output: [""]
    
    Constraints:

    1 <= s.length <= 25
    s consists of lowercase English letters and parentheses '(' and ')'.
    There will be at most 20 parentheses in s.

    problem link: https://leetcode.com/problems/remove-invalid-parentheses/

    Approach: At first I've find the open and close parentheses that are invalid, and then I used backtracking to solve this
    problem, and the most important part is to keep track of the balance of the parentheses, and if the balance is negative,
    we can return from the recursion. Also, if the length of the string is equal to the length of the original string minus
    the number of invalid parentheses, we can check if the balance is zero, and if it is, we can add the string to the set and return else only return.
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RemoveInvalidParentheses {
    int open, close;
    Set<String> set = new HashSet<>();
    private void countNumberOfInvalidParanthesis(String s) {
        int bal = 0;

        for(char ch : s.toCharArray()) {
            if(ch >= 'a' && ch <= 'z') continue;

            if(ch == '(') {
                open++;
            } else {
                open--;
            }

            if(open < 0) {
                close++;
                open = 0;
            }
        }
    }

    private void solve(String s, int idx, StringBuilder sb, int bal, int open, int close) {
        if(bal < 0)
            return;

        if(idx == s.length()) {
            if(bal == 0) {
                set.add(sb.toString());
            }
            return;
        }

        if(open > 0 && s.charAt(idx) == '(') {
            solve(s, idx+1, sb, bal, open - 1, close);
        }

        if(close > 0 && s.charAt(idx) == ')') {
            solve(s, idx+1, sb, bal, open, close - 1);
        }
        if(s.charAt(idx) == '(') {
            bal++;
        } else if(s.charAt(idx) == ')')
            bal--;

        sb.append(s.charAt(idx));
        solve(s, idx+1, sb, bal, open, close);
        sb.deleteCharAt(sb.length() - 1);
    }
    public List<String> removeInvalidParentheses(String s) {
        open = 0;
        close = 0;

        countNumberOfInvalidParanthesis(s);

        List<String> res = new ArrayList<>();

        solve(s, 0, new StringBuilder(), 0, open, close);

        // System.out.println(open + " " + close);

        for(String str : set) {
            res.add(str);
        }

        return res;
    }
}