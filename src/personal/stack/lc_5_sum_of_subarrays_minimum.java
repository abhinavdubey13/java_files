package personal.stack;

import java.util.Stack;

/**
 * leetcode : 907
 *
 *
 * Given an array of integers arr, find the sum of min(b),
 *
 * where b ranges over every (contiguous) subarray of arr.
 *
 * Since the answer may be large, return the answer modulo 109 + 7
 *
 *
 *
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17
 *
 */


/**
 *
 * monotone stack
 *
 * https://www.youtube.com/watch?v=Ulb3ixSpE4Y
 *
 *
 * TC=SC=n
 *
 */


public class lc_5_sum_of_subarrays_minimum {

    public static void main(String[] args) {
        //int[] arr = {3, 1, 2, 4};//17
//        int[] arr = {11, 81, 94, 43, 3};//444

        int[] arr = {71, 55, 82, 55};


        int ans = new Solution_5().function(arr);
        System.out.println(ans);
//        for (int i : ans) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
    }
}


class Solution_5 {

    int MOD = 1000000007;

    int function(int[] arr) {
        int n = arr.length;

        if (n < 2) {
            return (n == 1) ? arr[0] : 0;
        }

        int answer = 0;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<int[]> stk = new Stack<>();

        //fill left[] using monotone stack : only greater and equal element will be place on top
        for (int i = 0; i < n; i++) {
            int curr = arr[i];
            int count = 1;
            while (stk.size() > 0 && curr < stk.peek()[0]) {
                count += stk.pop()[1];
            }
            left[i] = count;
            stk.push(new int[]{curr, count});
        }

        stk.clear();

        //fill right[] using monotone stack : only greater and equal element will be place on top
        for (int i = n - 1; i >= 0; i--) {
            int curr = arr[i];
            int count = 1;

            //<= is required here
            while (stk.size() > 0 && curr <= stk.peek()[0]) {
                count += stk.pop()[1];
            }
            right[i] = count;
            stk.push(new int[]{curr, count});
        }


        for (int i = 0; i < n; i++) {
            answer = (answer + arr[i] * left[i] * right[i]) % MOD;
        }
        return answer;

    }
}