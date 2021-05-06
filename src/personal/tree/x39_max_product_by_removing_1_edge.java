package personal.tree;

/**
 * leetcode id : 1339
 *
 * Given a binary personal.tree root. Split the binary personal.tree into two subtrees by removing 1 edge
 *
 * such that the product of the sums of the subtrees are maximized.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 */

/**
 *
 * find sum of all nodes
 *
 * at each node find max of
 * (TOTAL_SUM-lst_sum)*lst_sum , (TOTAL_SUM-rst_sum)*rst_sum
 *
 * like this
 *
 * TC=SC=O(n)
 *
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class x39_max_product_by_removing_1_edge {

}

class Solution39 {

    int TREE_SUM;
    int MAX_PROD;
    int MOD = 1000000007;

    public int maxProduct(TreeNode root) {
        TREE_SUM = 0;
        MAX_PROD = Integer.MIN_VALUE;
        set_tree_sum(root);
        set_max_prod(root);
        return MAX_PROD;
    }

    int set_max_prod(TreeNode curr) {
        if (curr == null) {
            return 0;
        }

        int sum_lst = set_max_prod(curr.left);
        int sum_rst = set_max_prod(curr.right);

        int prod_1 = ((TREE_SUM - sum_lst) * sum_lst) % MOD;
        int prod_2 = ((TREE_SUM - sum_rst) * sum_rst) % MOD;
        MAX_PROD = Math.max(MAX_PROD, Math.max(prod_1, prod_2));

        return curr.val + sum_rst + sum_lst;
    }


    void set_tree_sum(TreeNode curr) {
        if (curr != null) {
            TREE_SUM += curr.val;
            set_tree_sum(curr.left);
            set_tree_sum(curr.right);
        }

    }
}
