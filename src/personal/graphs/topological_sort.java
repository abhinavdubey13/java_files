package personal.graphs;

import java.util.*;

/**
 *
 * given directed-acyclic-graph
 *
 * find topological order of nodes
 *
 * number of nodes = n (0 --> n-1)
 *
 */

/**
 *
 * approach : BFS + indegree
 *
 *
 * 1. add to queue those nodes whose in-degree is 0 ,
 *    as they are not dependent on any other node
 *
 * 2. repeat while q.size() > 0
 *       -pop node
 *       -add this to answer , as its indegree is 0
 *       -decrement indegree of all its ngbrs by 1 and if after decrementing ,
 *       the indegree becomes 0 ,  add to queue
 *
 *
 *
 */


public class topological_sort {

    public static void main(String[] args) {
        List<List<Integer>> adj_list = new LinkedList<>();

        // forest graph => expected : 1,2,3,0 (1,2,3 can be in any 0rder)
        adj_list.add(new ArrayList<Integer>(Arrays.asList())); //neighbours of 0 (undirected - edge)
        adj_list.add(new ArrayList<Integer>(Arrays.asList(0))); //neighbours of 1 (undirected - edge)
        adj_list.add(new ArrayList<Integer>(Arrays.asList(0))); //neighbours of 2 (undirected - edge)
        adj_list.add(new ArrayList<Integer>(Arrays.asList(0))); //neighbours of 3 (undirected - edge)

        int num_nodes = adj_list.size();

        List<Integer> answer = new Solution_top_sort().get_topological_order(num_nodes, adj_list);
        for (int i : answer) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}


class Solution_top_sort {

    List<Integer> ans;

    List<Integer> get_topological_order(int num_nodes, List<List<Integer>> adj_list) {

        ans = new LinkedList<>();

        int[] indegree = new int[num_nodes];

        for (int i = 0; i < adj_list.size(); i++) {
            List<Integer> ngbrs_of_i = adj_list.get(i);
            for (Integer n : ngbrs_of_i) {
                indegree[n]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < num_nodes; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (q.size() > 0) {
            int polled = q.poll();
            ans.add(polled);

            List<Integer> ngbrs_of_polled = adj_list.get(polled);
            for (Integer n : ngbrs_of_polled) {
                indegree[n]--;
                if (indegree[n] == 0) {
                    q.add(n);
                }
            }
        }

        return ans;
    }

}