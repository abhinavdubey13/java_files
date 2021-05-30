package personal.bit_manipulation;

/**
 * given a number N , find a mask in which
 *
 *
 * only the right-most-set-bit of N is set in the mask as well ,
 * rest all bits are 0 in mask
 *
 *
 */

/**
 *
 *
 *
 * mask = N & -N
 *
 * => -N : 2's compliment of N
 * => 2's complement means ,
 *    all bits before RMSB in N will be toggled
 *    and all bits after RMSB are 0 in N already , and will be 0 in 2's compliment also
 *
 * => when we AND these 2 , we have a RMSB mask
 *
 *
 *
 *
 *
 */


public class concept_2_rmsb_mask {
    public static void main(String[] args) {
        int n = 14;
        int mask = (n & -n);
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(mask));
    }
}
