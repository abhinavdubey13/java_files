package personal.LC_premium.binary_search;

import java.util.*;

/**
 * leetcode id : 1482
 *
 *
 * Given an integer array bloomDay, an integer m and an integer k.
 *
 * We need to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
 *
 * The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
 *
 * Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.
 *
 *
 */


/**
 * application of binary search
 *
 *
 *  find range , check using binary search if MID is satisfiable answer
 *
 */

public class x4_minimum_days_for_bouquets {

    public static void main(String[] args) {
        int[] arr = {1, 10, 3, 10, 2};
        int bouquets_required = 3;
        int consecutive = 1;
        int ans = new Solution_bs_4().function(arr, bouquets_required, consecutive);
        System.out.println(ans);
    }
}


class Solution_bs_4 {

    int function(int[] arr, int bouquets_required, int consecutive) {

        if (arr.length < bouquets_required * consecutive) {
            return -1;
        }

        int[] range = get_range(arr);

        int low = range[0];
        int high = range[1];

        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean is_ok = is_okay(mid, arr, bouquets_required, consecutive);
            if (is_ok) {
                high = mid;
            } else {
                low = mid + 1;
            }

        }


        return low;
    }

    int[] get_range(int[] arr) {
        int max_num = arr[0];
        int min_num = arr[0];
        for (int i : arr) {
            max_num = Math.max(max_num, i);
            min_num = Math.min(min_num, i);
        }
        return new int[]{min_num, max_num};
//        return new int[]{1, (int) 1e9};
    }

    boolean is_okay(int days_to_wait, int[] arr, int bouquets_required, int consecutive) {
        List<Integer> patch = new LinkedList<>();
        int pre = 0;
        for (int i : arr) {
            if (i <= days_to_wait) {
                pre += 1;
            } else {
                if (pre > 0)
                    patch.add(pre);
                pre = 0;
            }
        }

        if (pre > 0) {
            patch.add(pre);
        }

        int bq_made = 0;
        int num = bouquets_required * consecutive;

        for (int i : patch) {
            if (i >= num) {
                return true;
            } else if (i >= consecutive) {
                int satisfiable = (int) Math.floor((double) i / consecutive);
                bq_made += satisfiable;
            }
        }


        return bq_made >= bouquets_required;


    }

}
