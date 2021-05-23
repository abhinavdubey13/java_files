package personal.LC_premium.binary_search;

/**
 * leetcode id : 410
 *
 * Given an array nums which consists of non-negative integers and an integer m,
 *
 * you can split the array into m non-empty continuous subarrays.
 *
 * Write an algorithm to minimize the largest sum among these m subarrays.
 *
 *
 *
 * Example 1:
 * Input: nums = [7,2,5,10,8], m = 2
 * Output: 18
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 *
 *
 * Example 2:
 * Input: nums = [1,2,3,4,5], m = 2
 * Output: 9
 *
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

public class x3_split_array_for_largest_sum {

    public static void main(String[] args) {
        int[] arr = {7, 2, 5, 10, 8};
        int splits = 2;
        int ans = new Solution_bs_3().function(arr, splits);
        System.out.println(ans);
    }
}


class Solution_bs_3 {

    int function(int[] arr, int splits) {

        int[] range = get_range(arr);

        int low = range[0];
        int high = range[1];

        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean is_ok = is_okay(mid, arr, splits);
            if (is_ok) {
                high = mid;
            } else {
                low = mid + 1;
            }

        }


        return low;
    }

    int[] get_range(int[] arr) {
        int max_num = 0;
        int sum = 0;
        for (int i : arr) {
            max_num = Math.max(max_num, i);
            sum += i;
        }
        return new int[]{max_num, sum};
    }

    boolean is_okay(int sum_limit, int[] arr, int splits) {

        int splits_required_with_curr_limit = 1;
        int total_of_grup = 0;

        for (int i : arr) {
            if (total_of_grup + i > sum_limit) {
                splits_required_with_curr_limit++;
                total_of_grup = 0;
            }
            total_of_grup += i;
        }

        return (splits_required_with_curr_limit <= splits);
    }

}
