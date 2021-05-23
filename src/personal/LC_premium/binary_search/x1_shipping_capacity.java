package personal.LC_premium.binary_search;

/**
 * leetcode id : 1011
 *
 *
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 *
 * The ith package on the conveyor belt has a weight of weights[i].
 * Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will
 * result in all the packages on the conveyor belt being shipped within days days.
 *
 *
 * EXAMPLE-1
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 * Note that the cargo must be shipped in the order given,
 * so using a ship of capacity 14 and splitting the packages into parts like
 * (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 *
 *
 */


/**
 *
 * application of binary search
 *
 * https://www.youtube.com/watch?v=pbjsegnnVbw&list=PLgT5F5jYnV8O0m_Wj73EQWO_Ra4u-Hruu&index=2
 *
 *
 * 1. find the range of valid answers
 *    in this case [min,max] = [max_of_all_wts , sum_of_wts]
 *
 * 2. using binary search , pick-up a weight and check if its a valid answer
 *
 *
 * 3. break only when low=high
 *
 *
 *
 *
 * ==============
 * NOTE
 * =============
 *
 * the gist behind the binary search problems is to ask yourself:
 *
 * 1- Do I know the exact range of possible responses?
 * 2- Can I check quickly enough if a response is correct?
 * 3- Every time I check a response, do I remove all lesser or greater options?
 *
 *
 */

public class x1_shipping_capacity {

    public static void main(String[] args) {
        int[] wt = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;

        int ans = new Solution_bs_1().function(wt, days);

        System.out.println(ans);

    }
}


class Solution_bs_1 {

    int function(int[] arr, int days) {

        int[] range = get_range(arr);

        int low = range[0];
        int high = range[1];

        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean is_ok = is_okay(mid, arr, days);
            if (is_ok) {
                high = mid;
            } else {
                low = mid + 1;
            }

        }


        return low;
    }

    int[] get_range(int[] arr) {
        int max_wt = 0;
        int total_wt = 0;
        for (int i : arr) {
            max_wt = Math.max(max_wt, i);
            total_wt += i;
        }

        return new int[]{max_wt, total_wt};
    }

    boolean is_okay(int per_day_wt_limit, int[] arr, int days) {

        int days_required_with_curr_limit = 1; //do not begin with zero
        int curr_grup_wt = 0;

        for (int i : arr) {
            if (curr_grup_wt + i > per_day_wt_limit) {
                days_required_with_curr_limit++;
                curr_grup_wt = 0;
            }
            curr_grup_wt += i;
        }

        return (days_required_with_curr_limit <= days);
    }

}
