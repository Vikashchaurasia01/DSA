package iteration;

/*
    Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:

    Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
    Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
    In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.

    For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
    You can insert the characters '(' and ')' at any position of the string to balance it if needed.

    Return the minimum number of insertions needed to make s balanced.

    Example 1:

    Input: s = "(()))"
    Output: 1
    Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to add one more ')' at the end of the string to be "(())))" which is balanced.
    Example 2:

    Input: s = "())"
    Output: 0
    Explanation: The string is already balanced.
    Example 3:

    Input: s = "))())("
    Output: 3
    Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
    
    Constraints:

    1 <= s.length <= 105
    s consists of '(' and ')' only.

    problem link: https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/

    Approach:
*/

class MinimumInsertionstoBalanceaParenthesesString {
    // wrong approach
    public int minInsertions(String s) {
        int open = 0, close = 0, res = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                open += 2;
            } else {
                close++;
            }

            if(close > open) {
                res += 1;
                open += 2; 
            }
        }

        return res += open - close;
    }

    // wrong approach
    public int minInsertion1(String s) {
        int open = 0, close = 0, res = 0;

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                if(open > close && s.charAt(i-1) == ')') {
                    res += open - close;
                    close += open - close;
                }
                open += 2;
            } else {
                close++;
            }

            if(close > open) {
                res += 1;
                open += 2; 
            }
        }

        return res += open - close;
    }

    // correct approach
    public int minInsertion(String s) {
        int close = 0, res = 0;

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                close += 2;
                if(close % 2 == 1) {
                    res++;
                    close--;
                }
            } else {
                close--;

                if(close < 0) {
                    res++;
                    close += 2;
                }
            }
        }

        return res + close;
    }
}
