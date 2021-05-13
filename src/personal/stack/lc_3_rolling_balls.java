package personal.stack;

import java.util.Stack;

/**
 * leetcode : 735
 *
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size,
 *
 * and the sign represents its direction (positive meaning right, negative meaning left).
 *
 * Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions.
 *
 * If two asteroids meet, the smaller one will explode.
 *
 * If both are the same size, both will explode.
 *
 * Two asteroids moving in the same direction will never meet
 *
 *
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 *
 */


/**
 *
 *
 * simple stack problem
 *
 * collision will no happen if :
 * 1. curr and top have same direction
 * 2. curr is going right , top is going left
 *
 *
 * collision case :
 * curr goes left , top goes right
 *
 */


public class lc_3_rolling_balls {

    public static void main(String[] args) {

        //int[] arr = {5, 10, -5};
        //int[] arr = {10,2,-5};
        //int[] arr = {-2, -1, 1, 2};
        //int[] arr = {8, -8};
        int[] arr = {-2, -2, 1, -2};


        int[] ans = new Solution_3().function(arr);

        for (int i : ans) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}


class Solution_3 {

    int[] function(int[] arr) {
        int n = arr.length;
        Stack<Integer> stk = new Stack<>();

        if (n == 0) {
            return new int[]{};
        }

        if (n == 1) {
            return new int[]{arr[0]};
        }

        stk.push(arr[0]);

        for (int i = 1; i < n; i++) {

            int curr = arr[i];
            if (stk.empty()) {
                stk.push(curr);
            }

            //no collision
            else if (stk.peek() < 0 && curr < 0 || stk.peek() > 0 && curr > 0 || stk.peek() < 0 && curr > 0) {
                stk.push(curr);
            }

            //collision
            else if (stk.peek() > 0 && curr < 0) {


                while (stk.size() > 0 && stk.peek() > 0 && curr < 0 ) {
                    if (stk.peek() > Math.abs(curr)) {
                        curr = 0;
                        break;
                    } else if (stk.peek() == Math.abs(curr)) {
                        curr = 0;
                        stk.pop();
                        break;
                    } else if (stk.peek() < Math.abs(curr)) {
                        stk.pop();
                    }
                }

                if (curr != 0) {
                    stk.push(curr);
                }


            }

        }


        if (stk.size() == 0) {
            return new int[]{};
        }

        int[] answer = new int[stk.size()];
        int i = answer.length - 1;
        while (stk.size() > 0) {
            answer[i] = stk.pop();
            i--;
        }
        return answer;

    }
}