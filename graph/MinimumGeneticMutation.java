package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
    A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.

    Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.

    For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
    There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.

    Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.

    Note that the starting point is assumed to be valid, so it might not be included in the bank.

    Example 1:

    Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
    Output: 1
    Example 2:

    Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
    Output: 2

    Constraints:

    0 <= bank.length <= 10
    startGene.length == endGene.length == bank[i].length == 8
    startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T']

    Approach: I'VE USED level order BFS TRAVERSAL AND TRIED EVERY POSSIBLE STRINGS TO REACH TO THE GOAL
*/

public class MinimumGeneticMutation {
    class Solution {
        public int minMutation(String startGene, String endGene, String[] bank) {
            Set<String> set = new HashSet<>();
            for (String s : bank) {
                set.add(s);
            }

            char[] chars = { 'A', 'C', 'G', 'T' };
            Queue<String> que = new LinkedList<>();
            Set<String> vis = new HashSet<>();
            int level = 0;

            que.add(startGene);
            vis.add(startGene);

            while (!que.isEmpty()) {
                int size = que.size();

                while (size-- > 0) {
                    String s = que.poll();

                    if (s.equals(endGene))
                        return level;

                    char[] sChar = s.toCharArray();
                    for (char ch : chars) {
                        for (int i = 0; i < sChar.length; i++) {
                            char originalChar = sChar[i];

                            sChar[i] = ch;
                            String str = new String(sChar);
                            if (!vis.contains(str) && set.contains(str)) {
                                que.add(str);
                                vis.add(str);
                            }

                            sChar[i] = originalChar;
                        }
                    }
                }
                level++;
            }

            return -1;
        }
    }
}
