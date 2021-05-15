package personal.IE;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a BST and an integer.
 *
 * Find the least absolute difference between any node value of the BST and the given integer.
 */

/**
 * need to use BST property here
 */

class Node_2 {

    int data;
    Node_2 left, right;

    Node_2(int d) {
        data = d;
        left = null;
        right = null;

    }

}

public class gfg_2_amzn_closest_ele_in_BST {
    public static void main(String[] args) {
    }
}


class Solution_2 {

    static int ANSWER;

    static int function(Node_2 root, int k) {

        //only 1 node in tree
        if (root.left == null && root.right == null) {
            return Math.abs(k - root.data);
        }

        ANSWER = Integer.MAX_VALUE;
        dfs(root, k);
        return ANSWER;
    }


    static void dfs(Node_2 curr, int k) {
        if (curr != null) {
            ANSWER = Math.min(ANSWER, Math.abs(k - curr.data));
            if (k >= curr.data)
                dfs(curr.right, k);
            else
                dfs(curr.left, k);
        }
    }


}
