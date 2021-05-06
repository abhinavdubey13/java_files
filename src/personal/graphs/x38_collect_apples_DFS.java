package personal.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode id : 1443
 *
 * Given an undirected personal.tree consisting of n vertices numbered from 0 to n-1,
 * which has some apples in their vertices.
 * You spend 1 second to walk over one edge of the personal.tree.
 * Return the minimum time in seconds you have to spend to collect all apples in the personal.tree,
 * starting at vertex 0 and coming back to this vertex.
 *
 * The edges of the undirected personal.tree are given in the array edges, where edges[i] = [ai, bi]
 * means that exists an edge connecting the vertices ai and bi.
 * Additionally, there is a boolean array hasApple,
 * where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
 *
 * =========
 * example :
 * =========
 */

/**
 * =============
 * APPROACH :
 * =============
 * using DFS
 *
 * if a node u contains an apple then
 * all edges in the path from the root to the node have to be used forward and backward (2 times).
 *
 *
 *
 */

class x38_collect_apples_DFS {

    public static void main(String[] args) {

        //int n = 7;
        //int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        //List<Boolean> has_apples = new LinkedList<>(Arrays.asList(false, false, true, false, true, true, false));

        int n = 4;
        int[][] edges = {{0, 2}, {0, 3}, {1, 2}};
        List<Boolean> has_apples = new LinkedList<>(Arrays.asList(false, true, false, false));

        int answer = new Solution38().function(n, edges, has_apples);
        System.out.println(answer);
    }

}


class Solution38 {


    int DISTANCE;

    int function(int n, int[][] edges, List<Boolean> has_apples) {
        //form adjacency list
        List<List<Integer>> graph = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        boolean[] vis = new boolean[n];
        DISTANCE = 0;
        dfs(graph, 0, has_apples, vis);
        return DISTANCE;
    }

    boolean dfs(List<List<Integer>> graph, int curr, List<Boolean> has_apples, boolean[] vis) {
        vis[curr] = true;
        List<Integer> ngbrs = graph.get(curr);

        //initially we check the curr node itself
        boolean this_node_has_apple_in_its_subtree = has_apples.get(curr);

        for (Integer next : ngbrs) {
            if (!vis[next]) {
                boolean child_has_apples = dfs(graph, next, has_apples, vis);
                DISTANCE += child_has_apples ? 2 : 0;
                this_node_has_apple_in_its_subtree = this_node_has_apple_in_its_subtree || child_has_apples;
            }
        }

        return this_node_has_apple_in_its_subtree;
    }
}
