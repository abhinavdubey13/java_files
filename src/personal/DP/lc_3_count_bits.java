package personal.DP;


/**
 * leetcode id : 338
 *
 * Given an integer num, return an array of the number of 1's in the binary representation of every number in the range [0, num]
 */


/**
 *
 *
 * consider number [2,3] => [10, 11] in binary
 *
 * the next range [4,7]  can we written as = [2*2 , 2*2+1 , 3*2 , 3*2+1]
 *
 * ie each number of prev range is (doubled) , and (doubled + 1)
 *
 * for doubling : we place 0 at last : ie numbers of 1 will be same
 * for double + 1 : we place 1 at last : ie number of 1 will increment by 1
 *
 * ==============
 * TC =  n
 * SC = n
 * ==============
 */


public class lc_3_count_bits {
}


class Solution3 {

    public int[] function(int k) {
        if (k == 0) {
            return new int[]{0};
        }
        if (k == 1) {
            return new int[]{0, 1};
        }
        if (k == 2) {
            return new int[]{0, 1, 1};
        }

        int[] dp = new int[k + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 1; i <= k; i++) {

            int curr_1 = dp[i];
            int _double = 2 * i;
            int _double_plus_1 = 2 * i + 1;

            if (_double <= k) {
                dp[_double] = curr_1;
            }

            if (_double_plus_1 <= k) {
                dp[_double_plus_1] = curr_1 + 1;
            }
        }

        return dp;


    }
}