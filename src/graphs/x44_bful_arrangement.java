package graphs;

import java.util.*;


/**
 * leetcode id : 526
 *
 * Suppose you have n integers labeled 1 through n.
 * A permutation of those n integers perm (1-indexed) is considered a beautiful arrangement
 * if for every i (1 <= i <= n), either of the following is true:
 *
 * 1. perm[i] is divisible by i.
 * 2. i is divisible by perm[i].
 *
 * Given an integer n,
 * return the number of the beautiful arrangements that you can construct.
 *
 * =========
 * example :
 * =========
 *
 * Input: n = 2
 * Output: 2
 * Explanation:
 * The first beautiful arrangement is [1,2]:
 * The second beautiful arrangement is [2,1]:
 *
 *
 */


/**
 * =============
 * APPROACH :
 * =============
 *
 * using back tracking
 *
 * try each number at each position
 *
 * maintain a set of used numbers
 *
 */

class x44_bful_arrangement {

    public static void main(String[] args) {
        int n = 10;
        int answer = new Solution44().function(n);
        System.out.println(answer);
    }

}


class Solution44 {

    int COUNT = 0;
    Set<Integer> hset;

    int function(int n) {
        hset = new HashSet<>();
        COUNT = 0;
        dfs(1, n);
        return COUNT;
    }


    void dfs(int idx, int n) {

        if (idx > n) {
            COUNT++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!hset.contains(i) && (idx % i == 0 || i % idx == 0)) {
                hset.add(i);
                dfs(idx + 1, n);
                hset.remove(i);
            }
        }

    }


}
