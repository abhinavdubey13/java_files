package personal.stack;

import java.util.*;

/**
 * leetcode : 456
 *
 *
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers
 * nums[i], nums[j] and nums[k] such that :
 *
 * 1. i < j < k  , and
 * 2. nums[i] < nums[k] < nums[j].
 *
 * Return true if there is a 132 pattern in nums, otherwise, return false
 *
 *
 *
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 *
 */


/**
 * using stack and running min[]
 *
 *
 *
 */


public class lc_2_132_sequence {

    public static void main(String[] args) {
        //int[] arr = {10, 20, 5, 9, 7};
        //int[] arr = {3, 5, 0, 3, 4};
        int[] arr = {3, 1, 4, 2};

        boolean ans = new Solution_2().function(arr);


        System.out.println(ans);
    }
}


class Solution_2 {

    boolean function(int[] arr) {
        int n = arr.length;
        Stack<Integer> stk = new Stack<>();
        int[] min = new int[n];

        min[0] = arr[0];

        //min[i] = min(arr[0],arr[1],arr[2]......arr[i])
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(arr[i], min[i - 1]);
        }

        for (int i = n - 1; i >= 0; i--) {

            int min_i = min[i];
            int curr_i = arr[i];
            if (curr_i > min_i) {

                //from stack : remove all elements right of i'th index
                //which are lesser/equal  min_i
                while (stk.size() > 0 && stk.peek() <= min_i) {
                    stk.pop();
                }

                //found 132 pattern : bcz due to above step
                //stack will have only strictly greater elements than min_i
                //so only i condition needs to be checked
                if (stk.size() > 0 && curr_i > stk.peek()) {
                    return true;
                }

                stk.push(curr_i);
            }

        }


        return false;

    }
}