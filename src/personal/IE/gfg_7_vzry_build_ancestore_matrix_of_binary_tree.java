package personal.IE;

import java.util.Stack;


/**
 *
 * Given a Binary Tree where all values are from 0 to n-1.
 *
 * Construct an ancestor matrix mat[n][n]. Ancestor matrix is defined as below
 *
 * mat[i][j] = 1 if i is ancestor of j
 * mat[i][j] = 0, otherwise
 *
 *
 * NOTE : a node is NOT an ancestor of itself
 *
 *
 *
 * EXAMPLE :
 * Input: Root of below Binary Tree.
 *            5
 *         /    \
 *        1      2
 *       /  \    /
 *      0    4  3
 * Output: 0 0 0 0 0 0
 *         1 0 0 0 1 0
 *         0 0 0 1 0 0
 *         0 0 0 0 0 0
 *         0 0 0 0 0 0
 *         1 1 1 1 1 0
 *
 */


/**
 *
 *
 * post order traversal
 *
 *
 * TC = n*n
 * SC = log(ht)
 *
 *
 *
 *
 *
 */


public class gfg_7_vzry_build_ancestore_matrix_of_binary_tree {
    public static void main(String[] args) {

//        Node root = new Node(5);
//        root.left = new Node(1);
//        root.left.left = new Node(0);
//        root.left.right = new Node(4);
//        root.right = new Node(2);
//        root.right.left = new Node(3);
//        int n = 6;

        Node root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(2);
        int n = 3;

        int[][] arr = new Solution_7().function(root, n);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }

    }
}


class Solution_7 {

    int[][] answer;

    int[][] function(Node root, int n) {
        answer = new int[n][n];
        dfs(root);
        return answer;
    }

    void dfs(Node curr) {

        //null and leaf node
        if (curr == null || curr.left == null && curr.right == null) {
            return;
        }


        dfs(curr.left);
        dfs(curr.right);

        if (curr.left != null) {
            //all left grand children of curr
            for (int col = 0; col < answer[0].length; col++) {
                if (answer[curr.left.data][col] == 1) {
                    answer[curr.data][col] = 1;
                }
            }

            //left-child of curr
            answer[curr.data][curr.left.data] = 1;
        }


        if (curr.right != null) {
            //all right grand children of curr
            for (int col = 0; col < answer[0].length; col++) {
                if (answer[curr.right.data][col] == 1) {
                    answer[curr.data][col] = 1;
                }
            }
            //right-child of curr
            answer[curr.data][curr.right.data] = 1;
        }


    }


}
