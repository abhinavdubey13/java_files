package personal.DP;

import java.util.*;

/**
 *
 * leetcode id : 1314
 *
 *
 * Given a m x n matrix mat and an integer k, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for:
 *
 * i - k <= r <= i + k,
 * j - k <= c <= j + k, and
 * (r, c) is a valid position in the matrix.
 *
 *
 * Example 1:
 *
 * Input:
 * k = 1 ,
 * matrix = [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 *
 *
 * Output: [[12,21,16],[27,45,33],[24,39,28]]
 *
 */


/**
 * ===================================
 * concept : prefix sum in 2-D array
 * https://computersciencesource.wordpress.com/2010/09/03/computer-vision-the-integral-image/
 * ===================================
 *
 * consider if this qstion was for 1-D array
 *
 * it would be like :
 * given an arr[] and k ,
 * for each element arr[i] is this array find sum of   : arr[i-k].......arr[i+k]
 * return ans[]
 *
 *
 *
 * to solve this : we would first find prefix sum of arr[] : in dp[n+1]
 * now for each i : range = [i-k,i+k]
 *
 * so ans[i] = dp[i+k]-dp[i-k]
 *
 *
 * we apply similar concept with 2D array too
 *
 *
 * ==============
 * TC = SC = R*C
 * ==============
 *
 */


public class lc_1_matrix_block_sum {
}


class Solution1 {

    public int[][] function(int[][] mat, int k) {

        int row = mat.length;
        int col = mat[0].length;

        //prefix[i][j] stores sum of all cell left and above cell[i][j]
        //creating extra row and column will help us in calculating answer
        //if we move back k steps : and k>cols : we will not need extra check for this
        int[][] prefix_sum = new int[row + 1][col + 1];


        //fill prefix sum
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int left_cell = prefix_sum[i + 1][j];
                int top_cell = prefix_sum[i][j + 1];
                int diagonal_cell = prefix_sum[i][j];
                int curr = mat[i][j];
                prefix_sum[i + 1][j + 1] = left_cell + top_cell - diagonal_cell + curr;
            }
        }

        int[][] ans = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                int r1 = Math.max(0, i - k);
                int c1 = Math.max(0, j - k);

                int r2 = Math.min(row, i + k + 1);
                int c2 = Math.min(col, j + k + 1);

                int sum_included = prefix_sum[r2][c2] + prefix_sum[r1][c1];
                int sum_excluded = prefix_sum[r2][c1] + prefix_sum[r1][c2];

                ans[i][j] = sum_included - sum_excluded;

            }
        }

        return ans;


    }
}