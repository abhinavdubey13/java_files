package personal.DP;

import java.util.*;

/**
 * leetcode : 740
 *
 * Given an array nums of integers, you can perform operations on the array.
 *
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
 *
 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
 */

/**
 *
 *
 * convert this problem to excluding_prev and including_prev type
 *
 *
 * ============
 * TC = n
 * SC = n
 * ==========
 */


public class lc_9_max_sum_delete_and_earn {

    public static void main(String[] args) {
//        int[] arr = {2, 2, 3, 3, 3, 4};
        int[] arr = {1,1,1,2,4,5,5,5,6};

        int ans = new Solution9().function(arr);
        System.out.println(ans);
    }
}


class Solution9 {
    public int function(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> hmap = new HashMap<>();


        for (int i : arr) {
            hmap.put(i, 1 + hmap.getOrDefault(i, 0));
            set.add(i);
        }

        int[][] helper = new int[2][set.size()];

        int idx = 0;
        for (int i : set) {
            helper[0][idx] = hmap.get(i);
            helper[1][idx] = i;
            idx++;
        }


        int excl = 0;
        int incl = helper[0][0] * helper[1][0];
        int ans = Math.max(excl, incl);

        for (int i = 1; i < helper[0].length; i++) {
            int prev_incl = incl;
            int prev_excl = excl;

            boolean can_incl_prev_num = helper[1][i - 1] + 1 != helper[1][i];

            excl = Math.max(prev_excl, prev_incl);
            incl = helper[0][i]*helper[1][i] + ((can_incl_prev_num)?Math.max(prev_excl,prev_incl):prev_excl);
        }

         ans= Math.max(excl, incl);
        return ans;

    }
}