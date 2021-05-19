package personal.LC_premium;

import java.util.*;

/**
 * leetcode id : 1007
 *
 *
 * In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino. (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 *
 * We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
 *
 * Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are the same.
 *
 * If it cannot be done, return -1.
 */


/**
 *
 * find number to swap for
 *
 * then count occurance of that number in top[] and bottom[]
 *
 * return n-Max(occurnace);
 *
 */

public class x5_minimum_domino_rotation_for_equal_row {

    public static void main(String[] args) {

//        int[] top = {2, 1, 2, 4, 2, 2};
//        int[] bottom = {5, 2, 6, 2, 3, 2};

//        int[] top = {1, 2, 1, 1, 1, 2, 2, 2};
//        int[] bottom = {2, 1, 2, 2, 2, 2, 2, 2};

        int[] top = {2, 1, 1, 1, 2, 2, 2, 1, 1, 2};
        int[] bottom = {1, 1, 2, 1, 1, 1, 1, 2, 1, 1};

        int ans = new Solution_5().function(top, bottom);
        System.out.println(ans);

    }
}


class Solution_5 {

    int function(int[] top, int[] bottom) {
        int n = top.length;

        if (n < 2) {
            return 0;
        }

        int required_num = top[0];
        int max_freq = 1;
        Map<Integer, Integer> hmap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int freq = 1 + hmap.getOrDefault(top[i], 0);
            if (freq > max_freq) {
                max_freq = freq;
                required_num = top[i];
            }
            hmap.put(top[i], freq);

            freq = 1 + hmap.getOrDefault(bottom[i], 0);
            if (freq > max_freq) {
                max_freq = freq;
                required_num = bottom[i];
            }
            hmap.put(bottom[i], freq);
        }


        int count_top = 0;
        int count_bottom = 0;

        for (int i = 0; i < n; i++) {
            if (top[i] != required_num && bottom[i] != required_num) {
                return -1;
            }
            count_top += (top[i] == required_num) ? 1 : 0;
            count_bottom += (bottom[i] == required_num) ? 1 : 0;
        }

        return n - Math.max(count_bottom, count_top);
    }

}
