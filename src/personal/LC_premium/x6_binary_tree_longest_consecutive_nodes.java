package personal.LC_premium;

import personal.models.TreeNode;


/**
 * leetcode id : 298
 *
 *
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 *
 * The longest consecutive path needs to be from parent to child (cannot be the reverse).
 *
 *
 * example :
 *
 * Input: root = [1,null,3,2,4,null,null,null,5]
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 *
 */


/**
 *
 * using DFS
 *
 */

public class x6_binary_tree_longest_consecutive_nodes {

    public static void main(String[] args) {


    }
}


class Solution_6 {

    int ANSWER;

    int function(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }
        ANSWER = 0;
        dfs(root.left, root, 1);
        dfs(root.right, root, 1);

        return ANSWER;

    }

    void dfs(TreeNode curr, TreeNode parent, int len_till_parent) {
        if (curr == null) {
            return;
        }

        int len_here = 1;
        if (curr.data == parent.data + 1) {
            len_here = len_till_parent + 1;
        }

        ANSWER = Math.max(ANSWER , len_here);
        dfs(curr.left,curr,len_here);
        dfs(curr.right,curr,len_here);

    }

}
