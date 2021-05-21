package personal.LC_premium;

import java.util.*;

/**
 * leetcode id : 490
 *
 *
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
 * The ball can go through the empty spaces by rolling up, down, left or right,
 * but it won't stop rolling until hitting a wall.
 * When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze,
 * the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 */


/**
 *
 *
 * using BFS
 *
 * here instead of adding immediate neigbours , we need to keep rolling
 *
 *
 */

public class x9_ball_in_maze {

    public static void main(String[] args) {

        //true
//        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
//        int[] start = {0, 4};
//        int[] end = {4, 4};

        //false
        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] end = {3, 2};

        boolean ans = new Solution_9().function(maze, start, end);
        System.out.println(ans);

    }
}


class Solution_9 {

    int[] x_ngbr = {-1, 1, 0, 0};
    int[] y_ngbr = {0, 0, -1, 1};
    int R;
    int C;

    boolean function(int[][] maze, int[] start, int[] end) {

        R = maze.length;
        C = maze[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[R][C];
        q.add(start);
        vis[start[0]][start[1]] = true;

        while (q.size() > 0) {
            int[] popped = q.poll();
            for (int i = 0; i < x_ngbr.length; i++) {
                int nx = popped[0];
                int ny = popped[1];

                //keep rolling untill we can
                while (is_safe(nx, ny) && maze[nx][ny] == 0) {
                    nx += x_ngbr[i];
                    ny += y_ngbr[i];
                }

                //we stopped either outside the maze OR on stone
                //so come back to cell with value = 0
                nx -= x_ngbr[i];
                ny -= y_ngbr[i];

                if (!vis[nx][ny]) {
                    if (nx == end[0] && ny == end[1]) {
                        return true;
                    }
                    vis[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }


        return false;
    }



    boolean is_safe(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
