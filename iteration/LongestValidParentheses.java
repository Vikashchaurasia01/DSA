package iteration;

/*
    Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed)parentheses substring.

    Example 1:

    Input: s = "(()"
    Output: 2
    Explanation: The longest valid parentheses substring is "()".
    Example 2:

    Input: s = ")()())"
    Output: 4
    Explanation: The longest valid parentheses substring is "()()".
    Example 3:

    Input: s = ""
    Output: 0

    Constraints:

    0 <= s.length <= 3 * 104
    s[i] is '(', or ')'
*/

// ------------------------- brute force approach -------------------------
// Time complexity: O(n^2)
// Space complexity: O(1)
// Approach: We can use two nested loops to check all the substrings of the given string.

class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        
        for(int open =0; open<s.length(); open++) {
            if(s.charAt(open) == ')') {
                continue;
            }
            int balance = 0;
            for(int close = open; close < s.length(); close++) {
                if(s.charAt(close) == '('){
                    balance++;
                } else balance--;
                
                if(balance < 0) break;
                
                if(balance == 0)
                    maxLen = Math.max(maxLen, close-open+1);
            }
        }
        
        return maxLen;
    }
}

// ------------------------- optimal approach -------------------------
// Time complexity: O(n)
// Space complexity: O(1)

// Approach: We can use two counters to keep track of the number of open and close parentheses.
// We can traverse the string from left to right and then from right to left. In each traversal,
// we will update the counters and check if they are equal. If they are equal,
// we will update the maximum length of valid parentheses. If the close counter exceeds the open counter,
// we will reset both counters to zero.

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        int n = s.length();
        
        // left to right
        int open = 0, close = 0;
        for(int i =0; i < n; i++) {
            if(s.charAt(i) == '(')
                open++;
            else
                close++;

            if(open == close) {
                maxLen = Math.max(maxLen, open + close);
            }

            if(close > open) {
                close = 0;
                open = 0;
            }
        }

        // right to left
        open = 0;
        close = 0;
        for(int i = n - 1; i >= 0; i--) {
            if(s.charAt(i) == '(')
                open++;
            else
                close++;

            if(open == close) {
                maxLen = Math.max(maxLen, open + close);
            }

            if(close < open) {
                close = 0;
                open = 0;
            }
        }
        
        return maxLen;
    }
}
