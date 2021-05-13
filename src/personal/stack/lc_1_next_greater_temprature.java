package personal.stack;
import java.util.*;

/**
 * leetcode : 739
 *
 *
 * Given a list of daily temperatures temperatures,
 * return a list such that, for each day in the input,
 * tells you how many days you would have to wait until a warmer temperature.
 *
 *
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures temperatures =
 * [73, 74, 75, 71, 69, 72, 76, 73], your output should be
 * [1, 1, 4, 2, 1, 1, 0, 0].
 *
 *
 */


/**
 * using stack (keep idx + value_at_that_index)
 *
 * similar to next greater element
 *
 *
 *
 */


public class lc_1_next_greater_temprature {

    public static void main(String[] args) {
        int[] temp = {73, 74, 75, 71, 69, 72, 76, 73};

        int[] ans = new Solution_1().function(temp);

        for (int i : ans) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}


class Solution_1 {

    int[] function(int[] temp) {
        int n = temp.length;

        int[] ans = new int[n];

        //we maintain a strictly decreasing stack
        Stack<int[]> stk = new Stack<>();

        for (int i = 0; i < n; i++) {
            //if empty stack or stk-top-value is more than curr-val
            if (stk.empty() || stk.peek()[1] >= temp[i]) {
                stk.push(new int[]{i, temp[i]});
            } else {

                while (stk.size() > 0 && stk.peek()[1] < temp[i]) {
                    int[] popped = stk.pop();
                    int days = i - popped[0];
                    ans[popped[0]] = days;
                }

                stk.push(new int[]{i, temp[i]});

            }
        }

        while (stk.size() > 0) {
            int[] popped = stk.pop();
            ans[popped[0]] = 0;
        }

        return ans;
    }
}