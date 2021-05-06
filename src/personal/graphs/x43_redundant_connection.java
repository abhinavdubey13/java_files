package personal.graphs;


/**
 * leetcode id : 2069
 *
 * In this problem, a personal.tree is an undirected graph that is connected and has no cycles.
 *
 * The given input is a graph that started as a personal.tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
 *
 * Return an edge that can be removed so that the resulting graph is a personal.tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v
 *
 * =========
 * example :
 * =========
 *
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 *
 *
 */


/**
 * =============
 * APPROACH :
 * =============
 *
 * using DSU
 *
 */

class x43_redundant_connection {

    public static void main(String[] args) {


        int[][] edges = {{}};

        int[] answer = new Solution43().function(edges);
        System.out.println(answer);
    }

}

class DSU43 {
    int[] rank;
    int[] parent;

    DSU43(int n) {
        this.rank = new int[n];
        this.parent = new int[n];

        for (int i = 0; i < n; i++) {
            rank[i] = 0;
            parent[i] = i;
        }
    }

    int find(int x) {
        if (x == parent[x]) {
            return parent[x];
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py) {
            return false;
        }

        if (rank[py] <= rank[px]) {
            parent[py] = px;
            rank[px] += (rank[py] == rank[px]) ? 1 : 0;
        } else {
            parent[px] = py;
        }

        return true;

    }
}

class Solution43 {

    int[] function(int[][] edges) {
        int n = 0;
        for (int[] e : edges) {
            n = Math.max(n, Math.max(e[0], e[1]));
        }

        DSU43 dsu = new DSU43(n + 1);

        int[] answer = new int[2];
        for (int[] e : edges) {

            if (dsu.union(e[0], e[1]) == false) {
                answer[0] = e[0];
                answer[1] = e[1];
                break;
            }
        }

        return answer;

    }


}
