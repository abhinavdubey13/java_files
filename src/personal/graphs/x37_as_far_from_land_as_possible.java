package personal.graphs;

import java.util.*;

/**
 * leetcode id : 1162
 *
 *
 * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land,
 *
 * find a water cell such that its distance to the nearest land cell is maximized, and return the distance.
 *
 * If no land or water exists in the grid, return -1.
 *
 * =========
 * example :
 * =========
 *
 *
 */

/**
 * =============
 * APPROACH :
 * =============
 *
 * using BFS
 *
 * collect all 1's , then use BFS and keep marking 0 distance
 *
 * maintain count of max distance
 *
 *
 */

class x37_as_far_from_land_as_possible {

    public static void main(String[] args) {

        //expected : 2
        //int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};

        //expected : 4
        int[][] grid = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        int answer = new Solution37().function(grid);
        System.out.println(answer);
    }

}


class Solution37 {

    int[] x_ngbr = {-1, 1, 0, 0};
    int[] y_ngbr = {0, 0, -1, 1};

    int function(int[][] grid) {

        Queue<int[]> q = new LinkedList<>();
        int R = grid.length;
        int C = grid[0].length;
        boolean[][] vis = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 1) {

                    //x,y,distance from nearest 1
                    //since we are collecting only 1's : distance from 1 is 0
                    q.add(new int[]{i, j, 0});
                    vis[i][j] = true;

                }
            }
        }

        if (q.size() == 0) {
            return -1;
        }

        int ans = Integer.MIN_VALUE;
        while (q.size() > 0) {
            int[] polled = q.poll();
            int cx = polled[0];
            int cy = polled[1];
            int dist_from_nearest_1 = polled[2];

            for (int i = 0; i < x_ngbr.length; i++) {
                int nx = cx + x_ngbr[i];
                int ny = cy + y_ngbr[i];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && !vis[nx][ny]) {
                    q.add(new int[]{nx, ny, dist_from_nearest_1 + 1});
                    vis[nx][ny] = true;
                    ans = Math.max(ans, dist_from_nearest_1 + 1);
                }
            }
        }

        return (ans == Integer.MIN_VALUE) ? -1 : ans;

    }
}
