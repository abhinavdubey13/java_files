package backtracking;

import java.util.*;

/**
 * Leetcode id : 1219
 *
 * In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 *
 * Return the maximum amount of gold you can collect under the conditions:
 *
 * 1. Every time you are located in a cell you will collect all the gold in that cell.
 * 2. From your position, you can walk one step to the left, right, up, or down.
 * 3. You can't visit the same cell more than once.
 * 4. you cannot visit a cell with 0 gold.
 * 5. You can start and stop collecting gold from any position in the grid that has some gold.
 *
 * ==========
 * example :
 * ==========
 *
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 *
 *
 */

/**
 * ===========
 * approach :
 * ===========
 *
 * back-tracking
 *
 *
 */

public class x2_maximum_gold {

    public static void main(String[] args) {

//        int[][] grid = {{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        int[][] grid = {{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}};

        int answer = new Solution2().function(grid);
        System.out.println(answer);

    }
}

class Solution2 {

    int function(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] != 0) {
                    boolean[][] vis = new boolean[R][C];
                    int temp = dfs(i, j, R, C, grid, vis);
                    answer = Math.max(answer, temp);
                }
            }
        }

        return answer;
    }

    int dfs(int cx, int cy, int R, int C, int[][] grid, boolean[][] vis) {

        if (cx < 0 || cx >= R || cy < 0 || cy >= C || grid[cx][cy] == 0 || vis[cx][cy]) {
            return 0;
        }

        //mark as visited
        vis[cx][cy] = true;
        int gold_here = grid[cx][cy];

        int down = dfs(cx + 1, cy, R, C, grid, vis);
        int up = dfs(cx - 1, cy, R, C, grid, vis);
        int right = dfs(cx, cy + 1, R, C, grid, vis);
        int left = dfs(cx, cy - 1, R, C, grid, vis);
        int max_of_dirs = Math.max(Math.max(up, down), Math.max(left, right));

        vis[cx][cy] = false;//BACK-TRACKING
        int ans = gold_here + max_of_dirs;
        return ans;
    }
}
