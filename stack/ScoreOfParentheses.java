package stack;
/*
    Given a balanced parentheses string s, return the score of the string.

    The score of a balanced parentheses string is based on the following rule:

    "()" has score 1.
    AB has score A + B, where A and B are balanced parentheses strings.
    (A) has score 2 * A, where A is a balanced parentheses string.
    

    Example 1:

    Input: s = "()"
    Output: 1
    Example 2:

    Input: s = "(())"
    Output: 2
    Example 3:

    Input: s = "()()"
    Output: 2
    

    Constraints:

    2 <= s.length <= 50
    s consists of only '(' and ')'.
    s is a balanced parentheses string.

    problem link: https://leetcode.com/problems/score-of-parentheses/

    Approach: Here we can see that the score of a balanced parentheses string is based on the following rule:
              "()" has score 1.
              AB has score A + B, where A and B are balanced parentheses strings.
              (A) has score 2 * A, where A is a balanced parentheses string.
              So we can use stack to keep track of the score of the string. Whenever we get '(',
              we will push 0 to the stack and whenever we get ')', we will pop the top element from the stack and
              calculate the score based on the above rules and push it back to the stack. Finally,
              we will return the sum of all elements in the stack as the final score.
*/

import java.util.Stack;

class ScoreOfParentheses {
    // --------------------- optimal approach --------------------
    class Solution {
        public int scoreOfParentheses(String s) {
            int score = 0;
            int depth = 0;
            
            for(int i=0; i < s.length(); i++){
                char ch = s.charAt(i);
                
                if(ch == '(')
                    depth++;
                else {
                    depth--;
                    
                    if(s.charAt(i-1) == '(') {
                        score += 1 << depth;
                    }
                }
            }
            
            return score;
        }
    }

    // ---------------------- without space ---------------------
    
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(0);
            } else {
                int innerScore = stack.pop();
                int outerScore = stack.pop();
                stack.push(outerScore + Math.max(2 * innerScore, 1));
            }
        }

        return stack.pop();
    }
}