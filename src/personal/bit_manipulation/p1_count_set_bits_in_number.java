package personal.bit_manipulation;

/**
 * given a number x , find number of set bits in x
 */

/**
 *
 * ==============
 * Solution -1 :
 * ==============
 * brute force , check all bits
 *
 *
 * =============================================================
 * solution-2 : only count set bits ( kernighan's algorithm)
 * =============================================================
 *
 * https://www.youtube.com/watch?v=I475waWiTK4&list=PL-Jc9J83PIiFJRioti3ZV7QabwoJK6eKe&index=3
 * 1. RMSB mask is also used
 * 2. repeatedly subtract the RMSB mask number from x
 *
 *
 */

public class p1_count_set_bits_in_number {
    public static void main(String[] args) {
        int x = 32;
        int set_bits = new Solution_1().function(x);
        System.out.println(set_bits);
    }
}


class Solution_1 {

    int function(int x) {

        int set_bits = 0;

        while (x > 0) {
            int subtract = x & -x;
            x -= subtract;
            set_bits++;

        }

        return set_bits;

    }

}