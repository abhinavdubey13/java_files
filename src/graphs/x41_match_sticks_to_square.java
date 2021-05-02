package graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * GOOD QUESTION
 *
 * leetcode id : 473
 *
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
 *
 * You want to use all the matchsticks to make one square.
 * You should not break any stick, but you can link them up, and
 * each matchstick must be used exactly one time.
 *
 * Return true if you can make this square and false otherwise.
 *
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
 * using Back-tracking
 *
 * 1. check if sum is multiple of 4
 * 2. maintain 4 sets (array of 4 slots) , each set's sum must be (total_sum/4)
 * 3. backtrack if a particular combination is not matching
 *
 *
 */

class x41_match_sticks_to_square {

    public static void main(String[] args) {
//        int[] sticks = {3, 3, 3, 4, 1, 1, 1};
        int[] sticks = {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};

        boolean answer = new Solution41().function(sticks);
        System.out.println(answer);
    }

}


class Solution41 {

    boolean function(int[] sticks) {

        int sum = 0;

        for (int i : sticks) {
            sum += i;
        }

        if (sum % 4 != 0) {
            return false;
        }

        int[] set_sum = new int[4];

        return dfs(sticks, set_sum, 0, sum / 4);

    }

    boolean dfs(int[] sticks, int[] set_sum, int idx, int sum_required) {

        if (idx == sticks.length) {
            return true;
        }

        int curr = sticks[idx];

        boolean result = false;

        //placing current in set 1
        if (!result && set_sum[0] + curr <= sum_required) {
            set_sum[0] += curr;
            result = dfs(sticks, set_sum, idx + 1, sum_required);

            //if fail , we back track and remove current stick from set 1
            set_sum[0] -= curr;
        }

        if (!result && set_sum[1] + curr <= sum_required) {
            set_sum[1] += curr;
            result = dfs(sticks, set_sum, idx + 1, sum_required);
            set_sum[1] -= curr;

        }

        if (!result && set_sum[2] + curr <= sum_required) {
            set_sum[2] += curr;
            result = dfs(sticks, set_sum, idx + 1, sum_required);
            set_sum[2] -= curr;

        }

        if (!result && set_sum[3] + curr <= sum_required) {
            set_sum[3] += curr;
            result = dfs(sticks, set_sum, idx + 1, sum_required);
            set_sum[3] -= curr;
        }

        return result;

    }


}
