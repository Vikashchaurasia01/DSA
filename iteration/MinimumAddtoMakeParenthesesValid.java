package iteration;

/*
    Given a string s of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.

    Formally, a parentheses string is valid if and only if:
    It is the empty string, or
    It can be written as AB (A concatenated with B), where A and B are valid strings, or
    It can be written as (A), where A is a valid string.

    Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

    Example 1:

    Input: s = "())"
    Output: 1
    Example 2:

    Input: s = "((("
    Output: 3
    Example 3:

    Input: s = "()"
    Output: 0
    Example 4:

    Input: s = "()))(("
    Output: 4

    Constraints:

    1 <= s.length <= 1000
    s[i] is either '(' or ')'.
*/

class MinimumAddtoMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        int bal = 0;
        int res = 0;
        
        for(char ch : s.toCharArray()){
            if(ch == '('){
                if(bal < 0) {
                    res += Math.abs(bal);
                    bal = 0;
                }
                bal++;
            }
            else bal--;
        }
        
        return res += Math.abs(bal);
    }
}