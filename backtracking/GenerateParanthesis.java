package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    Example 1:

    Input: n = 3
    Output: ["((()))","(()())","(())()","()(())","()()()"]
    Example 2:

    Input: n = 1
    Output: ["()"]
    

    Constraints:

    1 <= n <= 8

    approach: I used simple backtracking to solve this problem, and the most important part is to keep track of the
    balance of the parentheses, and if the balance is negative, we can return from the recursion.
    Also, if the length of the string is equal to 2*n, we can check if the balance is zero, and if it is,
    we can add the string to the list and return else only return.
*/
class GenerateParanthesis {
    int N;
    private void solve(int n, List<String> list, StringBuilder sb, int balance) {
        
        if(balance < 0 || n < 0) return;

        if(sb.length() == N*2) {
            if(balance == 0)
                list.add(sb.toString());
            return;
        }

        sb.append('(');
        solve(n - 1, list, sb, balance + 1);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(')');
        solve(n, list, sb, balance - 1);
        sb.deleteCharAt(sb.length() - 1);
    }
    public List<String> generateParenthesis(int n) {
        N = n;
        List<String> list = new ArrayList<>();

        solve(n, list, new StringBuilder(), 0);

        return list;
    }
}