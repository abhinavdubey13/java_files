package personal.bit_manipulation;

/**
 * setting and checking bits at a given position
 *
 *
 * given a number n , and another number k (bit-position)
 *
 * 1. set k'th bit of n
 * 2. reset k'th bit of n
 * 3. toggle k'th bit of n
 * 4. check if k'th bit is set (print true if it's set)
 *
 */

/**
 *
 * for each operation we
 *  a. select appropriate bit operator
 *  b. form a mask
 *
 *
 * 1. to set k'th bit
 *    => operator = OR
 *    => mask = place 1 at k'th position , rest all are 0
 *
 *
 * 2. to reset k'th bit
 *    => operator = AND
 *    => mask = place 0 at k'th position , rest all are 1
 *
 *
 *
 * 3. to toggle k'th bit
 *     => operator = XOR
 *     => mask = place 1 at k'th position , rest all are 0
 *
 *
 * 4. to check k'th bit = 1
 *       => operator = AND
 *       => mask = place 1 at k'th position , rest all are 0
 *       => if AND gives 0 , it means kth bit was 0 already
 *
 *
 *
 *
 */


public class concept_1 {

    public static void main(String[] args) {

        int k = 3;
        int n = 17; ///00.....10001

        //for n=17 if we set 3rd bit from LSB => 21
        //for n=17 if we reset 3rd bit from LSB => 17
        //for n=17 if we toggle 3rd bit from LSB => 21
        //for n=17 is 3rd bit from LSB set => no


        int shifts = k - 1;
        int set_mask = (1 << shifts);
        int reset_mask = ~(1 << shifts);
        int toggle_mask = (1 << shifts);
        int check_mask = (1 << shifts);

        System.out.println(n | set_mask);
        System.out.println(n & reset_mask);
        System.out.println(n ^ toggle_mask);
        System.out.println((n & check_mask) != 0);


    }
}
