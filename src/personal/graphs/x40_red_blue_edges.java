package personal.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * GOOD QUESTION
 *
 * leetcode id : 1129
 *
 * Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.
 *
 * Each [i, j] in red_edges denotes a red directed edge from node i to node j.
 * Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.
 *
 * Return an array answer of length n, where each answer[X] is the
 * length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).
 *
 * =========
 * example :
 * =========
 */

/**
 * =============
 * APPROACH :
 * =============
 * using BFS
 *
 *
 * form 2 graph : red and blue using the respective edges
 *
 * do BFS 2 times : initially start with each color as the first edge
 *
 * for each BFS , we need to maintain 2 visited array
 * vis-red[]
 * vis-blue[]
 *
 * a node can be visted twice by using 2 seperate edges
 *
 *
 *
 */

class x40_red_blue_edges {

    public static void main(String[] args) {
//        int n = 3;
//        int[][] red = {{0, 1}};
//        int[][] blue = {{1, 2}};

//        int n = 3;
//        int[][] red = {{0, 1}, {1, 2}, {2, 1}, {1, 0}};
//        int[][] blue = {{0, 1}, {1, 2}, {2, 1}, {1, 0}};

        //we need 2 visited[]
        int n = 5;
        int[][] red = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        int[][] blue = {{1, 2}, {2, 3}, {3, 1}};

        int[] answer = new Solution40().function(n, red, blue);
        for (int i : answer) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}


class Solution40 {

    int RED = 1;
    int BLUE = 0;

    int[] function(int n, int[][] red, int[][] blue) {

        List<List<Integer>> graph_red = new LinkedList<>();
        List<List<Integer>> graph_blue = new LinkedList<>();

        form_graph(n, graph_red, red);
        form_graph(n, graph_blue, blue);

        int[] dist_1 = new int[n];
        int[] dist_2 = new int[n];

        Arrays.fill(dist_1, Integer.MAX_VALUE);
        Arrays.fill(dist_2, Integer.MAX_VALUE);

        bfs(n, RED, graph_red, graph_blue, dist_1);
        bfs(n, BLUE, graph_red, graph_blue, dist_2);

        for (int i = 0; i < n; i++) {
            dist_1[i] = Math.min(dist_1[i], dist_2[i]);
        }

        for (int i = 0; i < n; i++) {
            dist_1[i] = dist_1[i] == Integer.MAX_VALUE ? -1 : dist_1[i];
        }

        return dist_1;
    }


    void bfs(int n, int start_color, List<List<Integer>> graph_red, List<List<Integer>> graph_blue, int[] dist) {
        Queue<int[]> q = new LinkedList<>();

        //{curr_node , dist_from_0 , color_to_reach_here}
        q.add(new int[]{0, 0, start_color});
        boolean[] vis_blue = new boolean[n];
        boolean[] vis_red = new boolean[n];


        dist[0] = 0;
        if (start_color == RED) {
            vis_red[0] = true;
        } else {
            vis_blue[0] = true;
        }

        while (q.size() > 0) {
            int[] polled = q.poll();
            int node = polled[0];
            int dist_from_0 = polled[1];
            int next_color = 1 - polled[2];
            dist[node] = Math.min(dist[node], dist_from_0);

            List<Integer> ngbrs = (next_color == RED) ? graph_red.get(node) : graph_blue.get(node);

            for (Integer nxt : ngbrs) {
                if (next_color == RED && !vis_red[nxt] || next_color == BLUE && !vis_blue[nxt]) {

                    if (next_color == RED) {
                        vis_red[nxt] = true;
                    } else {
                        vis_blue[nxt] = true;
                    }

                    q.add(new int[]{nxt, dist_from_0 + 1, next_color});
                }
            }

        }


    }


    void form_graph(int n, List<List<Integer>> graph, int[][] edges) {
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
        }
    }

}
