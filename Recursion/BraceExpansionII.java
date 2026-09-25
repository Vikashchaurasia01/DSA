package Recursion;

import java.util.*;

/*
    Under the grammar given below, strings can represent a set of lowercase words. Let R(expr) denote the set of words the expression represents.

    The grammar can best be understood through simple examples:

    Single letters represent a singleton set containing that word.
    R("a") = {"a"}
    R("w") = {"w"}
    When we take a comma-delimited list of two or more expressions, we take the union of possibilities.
    R("{a,b,c}") = {"a","b","c"}
    R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
    When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
    R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
    R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
    Formally, the three rules for our grammar:

    For every lowercase letter x, we have R(x) = {x}.
    For expressions e1, e2, ... , ek with k >= 2, we have R({e1, e2, ...}) = R(e1) ∪ R(e2) ∪ ...
    For expressions e1 and e2, we have R(e1 + e2) = {a + b for (a, b) in R(e1) × R(e2)}, where + denotes concatenation, and × denotes the cartesian product.
    Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.

    
    Example 1:

    Input: expression = "{a,b}{c,{d,e}}"
    Output: ["ac","ad","ae","bc","bd","be"]
    Example 2:

    Input: expression = "{{a,z},a{b,c},{ab,z}}"
    Output: ["a","ab","ac","z"]
    Explanation: Each distinct word is written only once in the final answer.
    

    Constraints:

    1 <= expression.length <= 60
    expression[i] consists of '{', '}', ','or lowercase English letters.
    The given expression represents a set of words based on the grammar given in the description.


    Approach:
    Break every problem into subproblems and solve them recursively.
    The main idea is to perform union and concatenation of the strings based on the grammar rules.
    I've used a set to store unique words and return the sorted list of words at the end.

*/

class BraceExpressionII {
    String s;
    int n;
    int idx;

    Set<String> getUnit() {
        Set<String> res = new TreeSet<>();

        if(s.charAt(idx) == '{') {
            idx++;
            res = performUnion();
        } else {
            res = new TreeSet<>();
            res.add(String.valueOf(s.charAt(idx)));
        }

        idx++;
        return res;
    }

    Set<String> performConcat() {
        Set<String> res = new TreeSet<>();
        res.add("");

        while(idx < n && (s.charAt(idx) == '{' || s.charAt(idx) >= 'a' && s.charAt(idx) <= 'z')) {
            Set<String> temp = getUnit();

            Set<String> concatRes = new TreeSet<>();
            for(String left : res) {
                for(String right : temp) {
                    concatRes.add(left + right);
                }
            }

            res = concatRes;
        }

        return res;
    }

    Set<String> performUnion() {
        Set<String> res = new TreeSet<>();

        while(true) {
            Set<String> temp = performConcat();
            for(String str : temp) {
                res.add(str);
            }

            if(idx < n && s.charAt(idx) == ',') {
                idx++;
            } else
                break;
        }

        return res;
    }
    public List<String> braceExpansionII(String expression) {
        n = expression.length();
        idx = 0;
        s = expression;

        Set<String> st = performUnion();
        List<String> res = new ArrayList<>();
        for(String str : st) {
            res.add(str);
        }

        // Collections.sort(res);

        return res;
    }
}