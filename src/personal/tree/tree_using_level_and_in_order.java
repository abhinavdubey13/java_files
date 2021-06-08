package personal.tree;

import personal.aaa_shared.TreeNode;

import java.util.*;

/**
 *
 */

/**
 *
 * https://www.youtube.com/watch?v=f5nv35xfMuA
 *
 *
 */


public class tree_using_level_and_in_order {

    public static void main(String[] args) {
        System.out.println("hello");
    }

}

class Solution {


    TreeNode function(List<Integer> level, List<Integer> inorder) {
        int n = inorder.size();
        TreeNode ans = fun(level, inorder, 0, n - 1);
        return ans;
    }

    TreeNode fun(List<Integer> level, List<Integer> inorder, int in_start, int in_end) {

        if (in_start > in_end || level.size() == 0) {
            return null;
        }

        int node_val = level.get(0);
        TreeNode root = new TreeNode(node_val);

        if (in_start == in_end) {
            return root;
        }


        int mid = get_idx_from_inorder(inorder, in_start, in_end, node_val);

        //--------for LST-------------------
        Set<Integer> lft_set = new HashSet<>();
        List<Integer> left_level_order = new LinkedList<>();
        for (int i = in_start; i < mid; i++) {
            lft_set.add(inorder.get(i));
        }

        for (int i = 0; i < level.size(); i++) {
            if (lft_set.contains(level.get(i))) {
                left_level_order.add(level.get(i));
            }
        }


        //--------for RST-------------------
        Set<Integer> rght_set = new HashSet<>();
        List<Integer> right_level_order = new LinkedList<>();
        for (int i = in_start; i < mid; i++) {
            rght_set.add(inorder.get(i));
        }


        for (int i = 0; i < level.size(); i++) {
            if (rght_set.contains(level.get(i))) {
                right_level_order.add(level.get(i));
            }
        }


        //----now make recursive calls
        root.left = fun(left_level_order, inorder, in_start, mid - 1);
        root.right = fun(right_level_order, inorder, mid + 1, in_end);


        return root;


    }


    int get_idx_from_inorder(List<Integer> inorder, int i, int j, int val) {
        for (int itr = i; itr <= j; itr++) {
            if (inorder.get(itr) == val) {
                return itr;
            }
        }

        return -1;
    }
}
