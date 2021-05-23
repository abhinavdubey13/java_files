package personal.LC_premium.binary_search;

/**
 * leetcode id : 875
 *
 *
 * Koko loves to eat bananas.
 * There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k.
 * Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas,
 * she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but
 * still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 *
 *
 * Example 1:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 *
 * Example 2:
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 * Example 3:
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 */


/**
 *
 * application of binary serach
 *
 * find range , check using binary search if MID is satisfiable answer
 *
 *
 */

public class x2_coco_eating_bananas {

    public static void main(String[] args) {
//        int[] piles = {3, 6, 7, 11};
//        int hours = 8;

        int[] piles = {30, 11, 23, 4, 20};
        int hours = 5;

        int ans = new Solution_bs_2().function(piles, hours);

        System.out.println(ans);

    }
}


class Solution_bs_2 {

    int function(int[] arr, int hours) {

        int[] range = get_range(arr);

        int low = range[0];
        int high = range[1];

        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean is_ok = is_okay(mid, arr, hours);
            if (is_ok) {
                high = mid;
            } else {
                low = mid + 1;
            }

        }


        return low;
    }

    int[] get_range(int[] arr) {
        //here we cannot return sum of all banana piles as max
        //bcz it might over flow the limit
        return new int[]{1, 1000000007};
    }

    boolean is_okay(int per_hour_limit, int[] arr, int hours) {

        int hours_required_with_curr_limit = 0;

        for (int i : arr) {
            if (i <= per_hour_limit) {
                hours_required_with_curr_limit += 1;
            } else {
                int h = (int) Math.ceil((double) i / per_hour_limit);
                hours_required_with_curr_limit += h;
            }
        }

        return (hours_required_with_curr_limit <= hours);
    }

}
