package stack;
/*
    Given a valid parentheses string s, return the nesting depth of s. The nesting depth is the maximum number of nested parentheses.

    

    Example 1:

    Input: s = "(1+(2*3)+((8)/4))+1"

    Output: 3

    Explanation:

    Digit 8 is inside of 3 nested parentheses in the string.

    Example 2:

    Input: s = "(1)+((2))+(((3)))"

    Output: 3

    Explanation:

    Digit 3 is inside of 3 nested parentheses in the string.

    Example 3:

    Input: s = "()(())((()()))"

    Output: 3

    

    Constraints:

    1 <= s.length <= 100
    s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
    It is guaranteed that parentheses expression s is a VPS.

    problem link: 
*/

// import java.util.Stack;

class MaximumNestingDepthOfTheParentheses {

    // -------------------Using Stack-------------------

    // public int maxDepth(String s) {
    //     Stack<Character> st = new Stack<>();
        
    //     int maxDepth = 0;
        
    //     for(char ch : s.toCharArray()) {
    //         if(ch == '(')
    //             st.push(ch);
    //         else if(ch == ')'){
    //             maxDepth = Math.max(maxDepth, st.size());
    //             st.pop();
    //         }
    //     }
        
    //     return maxDepth;
    // }

    // -------------------- Without Stack -------------------

    public int maxDepth(String s) {
        // Stack<Character> st = new Stack<>();
        int depth = 0;
        int maxDepth = 0;
        
        for(char ch : s.toCharArray()) {
            if(ch == '('){
                // st.push(ch);
                depth++;
            }
            else if(ch == ')'){
                maxDepth = Math.max(maxDepth, depth);
                depth--;
            }
        }
        
        return maxDepth;
    }
}