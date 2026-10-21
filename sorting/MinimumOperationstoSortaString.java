/*
    You are given a string s consisting of lowercase English letters.

    In one operation, you can select any substring of s that is not the entire string and sort it in non-descending alphabetical order.

    Return the minimum number of operations required to make s sorted in non-descending order. If it is not possible, return -1.

    

    Example 1:

    Input: s = "dog"

    Output: 1

    Explanation:​​​​​​​

    Sort substring "og" to "go".
    Now, s = "dgo", which is sorted in ascending order. Thus, the answer is 1.
    Example 2:

    Input: s = "card"

    Output: 2

    Explanation:

    Sort substring "car" to "acr", so s = "acrd".
    Sort substring "rd" to "dr", making s = "acdr", which is sorted in ascending order. Thus, the answer is 2.
    Example 3:

    Input: s = "gf"

    Output: -1

    Explanation:

    It is impossible to sort s under the given constraints. Thus, the answer is -1.
    

    Constraints:

    1 <= s.length <= 105
    s consists of only lowercase English letters.
*/

public class MinimumOperationstoSortaString {
    class Solution {

        public int minOperations(String s) {
            int n = s.length();

            boolean sorted = true;
            char minChar = s.charAt(0);
            char maxChar = s.charAt(0);

            for (int i = 1; i < n; i++) {
                char current = s.charAt(i);

                minChar = (char) Math.min(minChar, current);
                maxChar = (char) Math.max(maxChar, current);

                if (current < s.charAt(i - 1)) {
                    sorted = false;
                }
            }

            // Case 1: String is already sorted
            if (sorted) {
                return 0;
            }

            // Case 2: An unsorted length-2 string cannot be sorted
            // because sorting both characters would mean sorting the entire string.
            if (n == 2) {
                return -1;
            }

            // Case 3: Minimum is already at the beginning,
            // or maximum is already at the end.
            if (s.charAt(0) == minChar ||
                    s.charAt(n - 1) == maxChar) {
                return 1;
            }

            // Case 4: Minimum or maximum exists at an internal position.
            for (int i = 1; i < n - 1; i++) {
                if (s.charAt(i) == minChar ||
                        s.charAt(i) == maxChar) {
                    return 2;
                }
            }

            // Case 5: Minimum is only at the end and
            // maximum is only at the beginning.
            return 3;
        }
    }
}
