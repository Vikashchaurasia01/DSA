
/*
    A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.

    For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
    A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to split it into s = A + B, with A and B nonempty valid parentheses strings.

    Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk, where Pi are primitive valid parentheses strings.

    Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.

    Example 1:

    Input: s = "(()())(())"
    Output: "()()()"
    Explanation: 
    The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
    After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
    Example 2:

    Input: s = "(()())(())(()(()))"
    Output: "()()()()(())"
    Explanation: 
    The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
    After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
    Example 3:

    Input: s = "()()"
    Output: ""
    Explanation: 
    The input string is "()()", with primitive decomposition "()" + "()".
    After removing outer parentheses of each part, this is "" + "" = "".

    Constraints:

    1 <= s.length <= 105
    s[i] is either '(' or ')'.
    s is a valid parentheses string.

    problem link: https://leetcode.com/problems/remove-outermost-parentheses/

    Approach: The approach is to keep track of the balance of the parentheses, and if the balance is zero,
    we can add the character to the string builder, else we can skip it.
*/

class RemoveOutermostParentheses {
    public String removeOuterParentheses(String s) {
        int bal = 0;
        StringBuilder sb = new StringBuilder();

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                if(bal != 0) sb.append(ch);

                bal++;
            } else {
                bal--;

                if(bal != 0) sb.append(ch);
            }
        }

        return sb.toString();
    }
}


class Solution {
    public String removeOuterParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                if(!stack.isEmpty()) sb.append(ch);

                stack.push(ch);
            } else {
                stack.pop();

                if(!stack.isEmpty()) sb.append(ch);
            }
        }

        return sb.toString();
    }
}