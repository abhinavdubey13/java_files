package personal.LC_premium;

/**
 * leetcode id : 766
 *
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 *
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements
 *
 */


/**
 *
 * each cell must be equal to its diagonal left top cell
 */

public class x7_toeplitz_matrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
        boolean ans = new Solution_7().function(matrix);
        System.out.println(ans);
    }
}


class Solution_7 {

    boolean function(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                int curr = matrix[i][j];
                int left_diagonal = matrix[i - 1][j - 1];
                if (curr != left_diagonal) {
                    return false;
                }
            }
        }
        return true;
    }

}
