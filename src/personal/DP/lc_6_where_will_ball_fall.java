package personal.DP;

/**
 *
 * leetcode : 1706
 *
 *
 * You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the top and bottom sides.
 *
 * Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball to the right or to the left.
 *
 * A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and is represented in the grid as 1.
 * A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and is represented in the grid as -1.
 * We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out of the bottom. A ball gets stuck if it hits a "V" shaped pattern between two boards or if a board redirects the ball into either wall of the box.
 *
 * Return an array answer of size n where answer[i] is the column that the ball falls out of at the bottom after dropping the ball from the ith column at the top, or -1 if the ball gets stuck in the box.
 *
 *
 */

/**
 *
 * DP + DFS
 *
 * at each cell : we have to check right and left cells for getting stuck in V or right/left wall
 *
 *
 *
 * TC=r+c
 * SC=r*c
 *
 */


public class lc_6_where_will_ball_fall {

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1}, {-1, -1, -1, -1, -1}};

        int[] ans = new Solution6().findBall(grid);
        for(int i : ans){
            System.out.print(i + " ");
        }

        System.out.println();
    }
}


class Solution6 {

    int[][] dp;
    int r;
    int c;

    public int[] findBall(int[][] g) {
        r = g.length;
        c = g[0].length;
        dp = new int[r][c];
        int[] ans = new int[c];

        for (int col = 0; col < c; col++) {
            ans[col] = dfs(0, col, g);
        }

        return ans;

    }


    int dfs(int x, int y, int[][] g) {
        if (x < 0 || x >= r) {
            return y;
        }

        if (y < 0 || y >= c) {
            return -1;
        }

        if (dp[x][y] != 0) {
            return dp[x][y];
        }


        int curr = g[x][y];
        int right = (y + 1 < c) ? g[x][y + 1] : -1;
        int left = (y - 1 >= 0) ? g[x][y - 1] : 1;

        int answer = -1;
        if (curr == 1) {
            answer = (right == -1) ? -1 : dfs(x + 1, y + 1, g);
        } else if (curr == -1) {
            answer = (left == 1) ? -1 : dfs(x + 1, y - 1, g);
        }

        dp[x][y] = answer;
        return dp[x][y];

    }
}