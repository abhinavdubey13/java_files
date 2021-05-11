package personal.DP;

/**
 * leetcode : 413
 *
 *
 * An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 *
 * For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 *
 *
 *
 */

/**
 *
 *
 * i) We need minimum 3 indices to make arithmetic progression,
 * ii) So start at index 2, see if we got two diffs same, so we get a current 1 arith sequence
 * iii) At any index i, if we see it forms arith seq with former two, that means running (curr) sequence gets extended upto this index, at the same time we get one more sequence (the three numbers ending at i), so curr++. Any time this happens, add the curr value to total sum.
 * iv) Any time we find ith index does not form arith seq, make currently running no of seqs to zero.
 *
 *
 * ============
 * TC = n
 * SC = 1
 * ==========
 */


public class lc_8_count_subarrays_with_consecutive_elements_having_same_diff {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int ans = new Solution8().function(arr);
        System.out.println(ans);
    }
}


class Solution8 {


    public int function(int[] arr) {

        int n = arr.length;

        if (n < 3) {
            return 0;
        }

        int ans = 0;
        int curr = 0;

        for (int i = 2; i < n; i++) {

            int diff_1 = arr[i] - arr[i - 1];
            int diff_2 = arr[i - 1] - arr[i - 2];
            if (diff_1 == diff_2) {
                curr++;
                ans += curr;
            } else {
                curr = 0;
            }

        }

        return ans;

    }
}