package personal.stack;

import java.util.Stack;

/**
 * leetcode : 946
 *
 * Given two sequences pushed and popped with distinct values,
 * return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack
 *
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 *
 */


/**
 *
 * code all cases
 *
 */


public class lc_4_verify_stack_sequence {

    public static void main(String[] args) {

        //expected : true
//        int[] push = {1, 2, 3, 4, 5};
//        int[] pop = {4, 5, 3, 2, 1};

        //expected : false
//        int[] push = {1, 2, 3, 4, 5};
//        int[] pop = {4, 3, 5, 1, 2};

        //expected : true
        int[] push = {1, 0};
        int[] pop = {1, 0};


        boolean ans = new Solution_4().function(push, pop);

        System.out.println(ans);

//        for (int i : ans) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
    }
}


class Solution_4 {

    boolean function(int[] push, int[] pop) {

        Stack<Integer> stk = new Stack<>();
        int n = push.length;
        int pop_idx = 0;
        int push_idx = 1;

        stk.push(push[0]);
        while (true) {

            //if all elements are processed and stack is empty at last
            if (pop_idx == n && stk.size() == 0) {
                break;
            }

            //if top and pop[i] match
            else if (stk.size() > 0 && stk.peek() == pop[pop_idx]) {
                stk.pop();
                pop_idx++;
            }

            //1. if top and pop[i] donot match
            //2. stack is empty , with push elements left , OR
            //then push
            else if (push_idx < n && stk.size() == 0 || push_idx < n && stk.size() > 0 && stk.peek() != pop[pop_idx]) {
                stk.push(push[push_idx]);
                push_idx++;
            }

            //all other cases are inavalid
            else {
                return false;
            }


        }

        return true;

    }
}