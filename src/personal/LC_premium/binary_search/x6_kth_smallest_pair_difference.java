package personal.LC_premium.binary_search;

import java.util.*;


/**
 * leetcode id : 719
 *
 *
 * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.
 *
 * Example 1:
 * Input:
 * nums = [1,3,1]
 * k = 1
 * Output: 0
 * Explanation:
 * Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 *
 *
 * Note:
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 *
 *
 */



/**
 * application of binary search
 *
 * find range , check using binary search if MID is satisfiable answer
 */

public class x6_kth_smallest_pair_difference {

    public static void main(String[] args) {

        int[] arr = {1, 3, 1};
        int k = 1;
        int ans = new Solution_bs_6().function(arr, k);
        System.out.println(ans);
    }
}


class Solution_bs_6 {

    int function(int[] arr, int k) {

        int n = arr.length;
        Arrays.sort(arr);

        //minimum possible differnce is min of differences of consecutive elements in sorted array
        //max possible difference is diff b/w 1st and last element
        int low = arr[1] - arr[0];
        int high = arr[n - 1] - arr[0];

        for (int i = 0; i < arr.length - 1; i++) {
            int new_diff = arr[i + 1] - arr[i];
            low = Math.min(low, new_diff);
        }


        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean is_ok = is_okay(mid, arr, k);
            if (is_ok) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    boolean is_okay(int mid, int[] arr, int k) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j < arr.length && arr[j] - arr[i] <= mid) {
                j++;
            }
            count += j - i - 1;
        }
        return count >= k;
    }

}
