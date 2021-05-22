package personal.LC_premium;

/**
 * leetcode id : 695
 *
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.)
 *
 * You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 *
 */


/**
 *
 * using DFS
 *
 */

public class x12_max_area_of_island {

    public static void main(String[] args) {

        int[][] matrix = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        int ans = new Solution_12().function(matrix);
        System.out.println(ans);

    }
}


class Solution_12 {

    int ANSWER;
    int ROW;
    int COL;

    int function(int[][] matrix) {

        ANSWER = 0;
        ROW = matrix.length;
        COL = matrix[0].length;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (matrix[i][j] == 1) {
                    int temp = dfs(i, j, matrix);
                    ANSWER = Math.max(ANSWER, temp);
                }
            }
        }

        return ANSWER;
    }


    int dfs(int x, int y, int[][] matrix) {
        if (!is_safe(x, y) || matrix[x][y] == 0) {
            return 0;
        }

        matrix[x][y] = 0;
        int area = 1;
        area += dfs(x + 1, y, matrix);
        area += dfs(x - 1, y, matrix);
        area += dfs(x, y + 1, matrix);
        area += dfs(x, y - 1, matrix);
        return area;
    }


    boolean is_safe(int x, int y) {
        return x >= 0 && x < ROW && y >= 0 && y < COL;
    }

}
