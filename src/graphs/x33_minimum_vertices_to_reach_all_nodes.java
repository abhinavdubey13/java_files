package graphs;

import java.util.*;

/**
 *
 * leetcode id : 1557
 *
 * Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.
 *
 * Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique solution exists.
 *
 * Notice that you can return the vertices in any orde
 *
 *
 *
 * =========
 * example :
 * =========
 *
 *
 *
 */

/**
 *
 *
 * =============
 * APPROACH :
 * =============
 *
 * Just return the nodes with no in-degres.
 *
 *
 * Explanation
 * Quick prove:
 *
 * Necesssary condition: All nodes with no in-degree must in the final result,
 * because they can not be reached from
 * All other nodes can be reached from any other nodes.
 *
 * Sufficient condition: All other nodes can be reached from some other nodes.
 * Sufficient condition: All other nodes can be reached from some other nodes.
 *
 *
 */

class x33_minimum_vertices_to_reach_all_nodes {

    public static void main(String[] args) {
        int n = 6;

        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new LinkedList<>(Arrays.asList(0, 1)));
        edges.add(new LinkedList<>(Arrays.asList(0, 2)));
        edges.add(new LinkedList<>(Arrays.asList(2, 5)));
        edges.add(new LinkedList<>(Arrays.asList(3, 4)));
        edges.add(new LinkedList<>(Arrays.asList(4, 2)));

        List<Integer> answer = new Solution().function(n, edges);
        for (Integer i : answer) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}

class Node {
    int i;
    int in;
    int out;

    Node(int i, int in, int o) {
        this.i = i;
        this.in = in;
        out = o;
    }
}

class Solution {

    List<Integer> function(int n, List<List<Integer>> edges) {

        List<Integer> res = new ArrayList<>();

        int[] in = new int[n];

        for (List<Integer> e: edges)
            in[e.get(1)] = 1;

        for (int i = 0; i < n; ++i)
            if (in[i] == 0)
                res.add(i);
        return res;


    }
}
