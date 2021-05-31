package personal.bit_manipulation;

/**
 * given an array , find minimum possible (xor of pairs)
 *
 *
 * example : {a,b,c}
 *
 * pairs :
 * (a,b) , (a,c) , (b,c)
 *
 * print the pair with minimum xor
 *
 * NOTE : here we have not considers , (a,a) , (b,b) , (c,c) as pair
 */

import java.util.Arrays;

/**
 * sort and calculate xor of consecutive pair ONLY
 *
 * other pairs will have greater XOR
 */

public class p6_min_of_xor_pairs {
    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 2, 3, 3, 4, 4, 5, 6};
        int ans = new Solution_46().function(arr);
        System.out.println(ans);
    }
}


class Solution_46 {

    int function(int[] arr) {


        Arrays.sort(arr);

        int min_xor = Integer.MAX_VALUE;

        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            int prev = arr[i - 1];
            int xor = curr ^ prev;

            min_xor = Math.max(min_xor, xor);
        }


        return min_xor;


    }


}