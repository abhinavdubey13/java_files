package personal.bit_manipulation;

/**
 * given a number n ,
 *
 * n people numbered from 1->n are standing in a queue , kth person is eliminated (k=2)
 *
 * print the survivor
 */

/**
 * for k = 2 , we have
 *
 * 1. survivor = (2*L + 1)
 * 2. n = 2^x + L
 * 3. take L from here
 *
 *
 * example-1 :
 * n=17
 * n=16+1
 * L=1
 * survivor = 2*1+1 = 3
 *
 */

public class p2_circle_kill_with_k_equals_2 {
    public static void main(String[] args) {
        //int n = 5; //expected = 3

        int n= 17;
        int survivor = new Solution_2().function(n);
        System.out.println(survivor);
    }
}


class Solution_2 {

    int function(int n) {
        int pow_of_2 = get(n);
        int L = n - pow_of_2;
        return (2 * L + 1);
    }

    int get(int n) {
        int pow = 1;
        while (2 * pow <= n) {
            pow = pow * 2;
        }
        return pow;
    }

}