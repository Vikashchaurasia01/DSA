import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
    We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

    Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.

    Example 1:
    Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
    Output: true
    Explanation: The first group has [1,4], and the second group has [2,3].

    Example 2:
    Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
    Output: false
    Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.
    

    Constraints:
    1 <= n <= 2000
    0 <= dislikes.length <= 10^4
    dislikes[i].length == 2
    1 <= ai < bi <= n
    All the pairs of dislikes are unique.

    Approach:
    1. We can model this problem as a graph coloring problem, where each person is a node and each dislike relationship is an edge between two nodes.
    2. We can use a BFS or DFS approach to try to color the graph using two colors (representing the two groups).
    3. If we can color the graph without any two adjacent nodes having the same color, then it is possible to split the group into two groups. Otherwise, it is not possible.
*/

public class PossibleBipartition {
    class Solution {
    private boolean bfs(int i, int[] colors, Map<Integer, List<Integer>> adj) {
        Queue<Integer> que = new LinkedList<>();
        que.add(i);
        colors[i] = 1;

        while (!que.isEmpty()) {
            int node = que.poll();
            int color = colors[node];

            for (int neig : adj.getOrDefault(node, Collections.emptyList())) {
                if (colors[neig] == color)
                    return false;

                if (colors[neig] == -1) {
                    que.add(neig);
                    colors[neig] = (color + 1) % 2;
                }
            }
        }

        return true;
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int[] edge : dislikes) {
            int u = edge[0];
            int v = edge[1];

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int[] colors = new int[n + 1];
        Arrays.fill(colors, -1);

        for (int i = 1; i <= n; i++) {
            if (colors[i] == -1) {
                boolean flag = bfs(i, colors, adj);
                if (!flag)
                    return false;
            }
        }

        return true;
    }
}
}
