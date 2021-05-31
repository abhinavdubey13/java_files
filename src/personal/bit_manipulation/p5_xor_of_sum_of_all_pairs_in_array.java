package personal.bit_manipulation;

/**
 * given an array , find xor of sum of all possible pairs
 *
 * in O(n) time
 * and constant space
 */

/**
 * [a,b,c]
 *
 * pairs :
 * a+a , a+b , a+c ,
 * b+a , b+a , b+c ,
 * c+a , c+b , c+c
 *
 * a+b and b+a will cancel out, similarly all non-diagonals will cancel
 * we have to find  : [2a ^ 2b ^ 2c]
 */

public class p5_xor_of_sum_of_all_pairs_in_array {
    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 2, 3, 3, 4, 4, 5, 6};
        int ans = new Solution_45().function(arr);
        System.out.println(ans);
    }
}


class Solution_45 {

    int function(int[] arr) {

        int ans = 0;

        for (int i : arr) {
            ans = (ans) ^ (2 * i);
        }

        return ans;


    }


}