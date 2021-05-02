package graphs;
import java.util.*;


/**
 *
 * leetcode id : 576
 * There is an m x n grid with a ball.
 * The ball is initially at the position [startRow, startColumn].
 * You are allowed to move the ball to one of the four adjacent four cells in the grid (possibly out of the grid crossing the grid boundary).
 * You can apply at most maxMove moves to the ball.
 *
 * Given the five integers m, n, maxMove, startRow, startColumn,
 *
 * return the number of paths to move the ball out of the grid boundary.
 *
 * Since the answer can be very large, return it modulo 109 + 7.
 *
 * =========
 * example :
 * =========
 */


/**
 * =============
 * APPROACH :
 * =============
 *
 * Using DFS and cache
 *
 * if we dont use cache  , we get TLE
 *
 * also , dont use BFS , it gives wrong answer
 *
 *
 */

class x42_move_ball_out_of_boundary {

    public static void main(String[] args) {

//        int R = 1;
//        int C = 3;
//        int moves = 3;
//        int start_x = 0;
//        int start_y = 1;

        int R = 10;
        int C = 10;
        int moves = 11;
        int start_x = 5;
        int start_y = 5;

        int answer = new Solution42().function(R, C, moves, start_x, start_y);
        System.out.println(answer);
    }

}


class Solution42 {
    int MOD = 1000000007;
    Map<String, Integer> cache;


    int function(int R, int C, int moves, int start_x, int start_y) {

        cache = new HashMap<>();
        return dfs(start_x, start_y, moves, R, C);
    }


    int dfs(int cx, int cy, int moves_left, int R, int C) {

        if (cx < 0 || cx >= R || cy < 0 || cy >= C) {
            return (moves_left >= 0) ? 1 : 0;
        }

        if (moves_left <= 0) {
            return 0;
        }

        String key = cx + "-" + cy + "-" + moves_left;
        Integer cache_value = cache.getOrDefault(key, null);
        if (cache_value != null) {
            return cache_value;
        }

        int ways = 0;
        int a = dfs(cx + 1, cy, moves_left - 1, R, C) % MOD;
        int b = dfs(cx - 1, cy, moves_left - 1, R, C) % MOD;
        int c = dfs(cx, cy + 1, moves_left - 1, R, C) % MOD;
        int d = dfs(cx, cy - 1, moves_left - 1, R, C) % MOD;

        ways = (((a + b) % MOD + (c + d) % MOD) % MOD) % MOD;

        cache.put(key, ways);
        return cache.get(key);


    }


}
