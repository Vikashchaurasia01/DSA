/*
    You are given a string s that consists of lower case English letters and brackets.

    Reverse the strings in each pair of matching parentheses, starting from the innermost one.

    Your result should not contain any brackets.

    

    Example 1:

    Input: s = "(abcd)"
    Output: "dcba"
    Example 2:

    Input: s = "(u(love)i)"
    Output: "iloveu"
    Explanation: The substring "love" is reversed first, then the whole string is reversed.
    Example 3:

    Input: s = "(ed(et(oc))el)"
    Output: "leetcode"
    Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
    

    Constraints:

    1 <= s.length <= 2000
    s only contains lower case English characters and parentheses.
    It is guaranteed that all parentheses are balanced.

    problem link: https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/description/?envType=daily-question&envId=2026-09-27

    Approach: I've used stack data structure to solve this problem.
    I iterate through the string and whenever I encounter a opening bracket,
    I pushed that index into stack and whenever I encounter closing bracket, I pop the index of last opening bracket from the stack. Finally, I reverse the substring from opening to closing bracket and repeat the same until iteration gets over.

*/

import java.util.Stack;

class ReverseSubstringsBetweenEachPairofParentheses {
    private void reverse(StringBuilder sb, int start, int end) {
        while(start < end){
            char temp = sb.charAt(start);
            sb.setCharAt(start++, sb.charAt(end));
            sb.setCharAt(end--, temp);
        }
    }
    public String reverseParentheses(String s) {
        Stack<Integer> openBracket = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                openBracket.push(sb.length());
            } else if(ch == ')') {
                int len = openBracket.pop();
                reverse(sb, len, sb.length()-1);
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}

