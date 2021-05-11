package personal.DP;

/**
 * leetcode : 1504
 *
 * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones
 *
 * Input: mat = [[1,0,1],
 *               [1,1,0],
 *               [1,1,0]]
 * Output: 13
 * Explanation:
 * There are 6 rectangles of side 1x1.
 * There are 2 rectangles of side 1x2.
 * There are 3 rectangles of side 2x1.
 * There is 1 rectangle of side 2x2.
 * There is 1 rectangle of side 3x1.
 * Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
 *
 */

/**
 *
 * https://www.youtube.com/watch?v=hrd-MEcZkOI&ab_channel=CodingBeast
 *
 * calculate for each 1-D row in the matrix first , what are the number of submatrices possible
 * containing all 1's , in that row
 *
 * after that consider each cell of the matrix
 * for each cell , find the depth which we can go , to extend the sub-matrix beginning at that index
 *
 *
 *
 * ============
 * TC = r*r*c
 * SC = 1
 * ==========
 *
 */


public class lc_7_count_submatrices_with_all_1s {

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
        int ans = new Solution7().function(grid);
        System.out.println(ans);
    }
}


class Solution7 {


    public int function(int[][] matrix) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        for (int i = 0; i < ROWS; i++) {
            //start column end
            for (int j = COLS - 2; j >= 0; j--) {
                //curr-cell = 1 + right-cell
                matrix[i][j] = (matrix[i][j] == 0) ? 0 : 1 + matrix[i][j + 1];
            }
        }

        int count = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                int min_width_of_matrix = matrix[i][j];

                for (int height = i; height < ROWS; height++) {

                    min_width_of_matrix = Math.min(min_width_of_matrix, matrix[height][j]);
                    count += min_width_of_matrix;

                }
            }
        }


        return count;
    }
}