package personal.bit_manipulation;

/**
 * given an array in which every number occurs twice ,
 * except 2 numbers (say x , y) , which occur only once
 *
 * print those 2 numbers (x,y)
 *
 * NOTE : can u do it without using extra space ?
 */

/**
 * xor of all elements in array ,  say ALL_XOR
 *
 * then use RMSB mask of the ALL_XOR to find X,Y
 *
 *
 * https://www.youtube.com/watch?v=pnx5JA9LNM4&list=PL-Jc9J83PIiFJRioti3ZV7QabwoJK6eKe&index=8
 *
 *
 */

public class p4_all_repeating_elements_except_2_uniques {
    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 2, 3, 3, 4, 4, 5, 6};
        new Solution_44().function(arr);
    }
}


class Solution_44 {

    void  function(int[] arr) {

        int all_xor = 0;

        for (int i : arr) {
            all_xor ^= i;
        }

        int rmsm_mask = all_xor & -all_xor;
        int x = 0;
        int y = 0;

        for (int i : arr) {
            if ((i & rmsm_mask) == 0) {
                x ^= i;
            } else if ((i & rmsm_mask) == 1) {
                y ^= i;
            }

        }

        System.out.println(x);
        System.out.println(y);


    }


}