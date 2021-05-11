package personal.DP;
import java.util.*;

/**
 *
 * leetcode : 1130
 *
 * Given an array arr of positive integers, consider all binary trees such that:
 *
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
 * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
 *
 *
 *
 *
 */

/**
 *
 * dp on trees
 *
 *
 * consider all partitions
 *
 * [i,j] = [i,k] , [k+1,j] will be 2 partions  i<=k<j
 *
 *
 * https://www.youtube.com/watch?v=pYs3qj42h3c&ab_channel=faadcoder
 *
 *
 * =========
 * TC=SC=n
 * ==========
 *
 */

public class lc_5_min_cost_tree_from_leaf_values {

    public static void main(String[] args) {

        //expected :  74
        //int[] arr = {7,6, 2, 4};

        //expected : 500
        int[] arr = {15, 13, 5, 3, 15};


        int ans = new Solution5().function(arr);
        System.out.println(ans);
    }
}


class Solution5 {

    Map<String, int[]> dp;

    public int function(int[] arr) {

        int n = arr.length;

        //only 1 internal node possible in this case
        if (n == 2) {
            return arr[0] * arr[1];
        }
        dp = new HashMap<>();
        int[] ans = dfs(0, n - 1, arr);
        return ans[1];
    }

    int[] dfs(int start, int end, int[] arr) {

        if (start == end) {
            return new int[]{arr[start], 0};
        }

        String key = start + "@" + end;

        int max_leaf = Integer.MIN_VALUE;
        int min_sum = Integer.MAX_VALUE;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        for (int i = start; i < end; i++) {
            int[] left = dfs(start, i, arr);
            int[] right = dfs(i + 1, end, arr);
            int sum_here = left[0] * right[0] + left[1] + right[1];

            max_leaf = Math.max(max_leaf, Math.max(left[0], right[0]));
            min_sum = Math.min(min_sum, sum_here);
        }


        dp.put(key, new int[]{max_leaf, min_sum});
        return dp.get(key);

    }

}