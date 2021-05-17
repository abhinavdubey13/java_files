package personal.IE;

import java.util.Stack;


/**
 * https://www.geeksforgeeks.org/form-minimum-number-from-given-sequence/
 *
 *
 * Given a pattern containing only I’s and D’s. I for increasing and D for decreasing.
 *
 * Devise an algorithm to print the minimum number following that pattern.
 *
 * Digits from 1-9 and digits can’t repeat.
 *
 * NOTE : input string's lenght will be minimum 1 , and maximum 8
 */


/**
 *
 * using stack
 *
 * https://www.youtube.com/watch?v=GOCbsY7Arw4
 *
 * 1. number of chars in output = 1 + chars in input
 *
 * 2. break points on 'i'
 *
 *
 *
 *
 *
 *
 *
 */


public class gfg_6_amzn_sequence_using_D_and_i {
    public static void main(String[] args) {

        //String input = "ddiddi"; // 3216547
        //String input = "idid"; // 13254
        String input = "iiddd"; //126543

        String output = new Solution_6().function(input);
        System.out.println(output);

    }
}


class Solution_6 {


    String function(String input) {

        int num = 1;
        Stack<Integer> stk = new Stack<>();
        String output = "";

        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);

            //if we see an 'd'
            //we push , increment
            if (curr == 'd') {
                stk.push(num);
                num++;
            }

            //if we see an 'i'
            //we push , increment , empty stack
            else {
                stk.push(num);
                num++;
                while (stk.size() > 0) {
                    output += String.valueOf(stk.pop());
                }
            }
        }

        //at last
        //we push , empty stack
        stk.push(num); // this is done for input-string = 'd'
        while (stk.size() > 0) {
            output += String.valueOf(stk.pop());
        }


        return output;
    }


}
