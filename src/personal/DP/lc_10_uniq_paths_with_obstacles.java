package personal.DP;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * leetcode : 63
 *
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and space is marked as 1 and 0 respectively in the grid
 *
 *
 *
 */

/**
 *
 * convert obstacle cells to -1 and the solve
 *
 * ============
 * TC = r*c
 * SC = 1
 * ==========
 */


public class lc_10_uniq_paths_with_obstacles {

    public static void main(String[] args) {

        int[][] arr = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};

        int ans = new Solution10().function(arr);
        System.out.println(ans);
    }
}


class Solution10 {
    public int function(int[][] arr) {
        int r = arr.length;
        int c = arr[0].length;

        if (r == 1 && c == 1) {
            return arr[0][0] == 0 ? 1 : 0;
        }

        if (arr[0][0] == 1 || arr[r - 1][c - 1] == 1) return 0;

        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                arr[i][j] = (arr[i][j] == 1) ? -1 : 0;


        //first column
        for (int i = 1; i < r; i++)
            if (arr[i][0] == -1) {
                arr[i][0] = -1;
            } else if (arr[i - 1][0] == -1) {
                arr[i][0] = -1;
            } else {
                arr[i][0] = 1;
            }

        //first row
        for (int i = 1; i < c; i++)
            if (arr[0][i] == -1) {
                arr[0][i] = -1;
            } else if (arr[0][i - 1] == -1) {
                arr[0][i] = -1;
            } else {
                arr[0][i] = 1;
            }


        //rest of the matrix
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (arr[i][j] != -1) {
                    arr[i][j] += (arr[i - 1][j] != -1) ? arr[i - 1][j] : 0;
                    arr[i][j] += (arr[i][j - 1] != -1) ? arr[i][j - 1] : 0;
                }
            }
        }

        return arr[r - 1][c - 1];


    }
}