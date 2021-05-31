package personal.bit_manipulation;

/**
 * given a number n ,
 *
 * n people numbered from 1->n are standing in a queue , kth person is eliminated (k>2)
 *
 * print the survivor
 */

/**
 * for k > 2 , we have to use recursion
 *
 * https://www.youtube.com/watch?v=dzYq5VEMZIg
 *
 *
 * for next iteration , consider n-1 ppl
 *
 * and convert to current mapping
 *
 */

public class p3_circle_kill_with_k_generalized {
    public static void main(String[] args) {

        int n = 14;
        int k = 2;
        int survivor = new Solution_3().function(n, k);
        System.out.println(survivor);
    }
}


class Solution_3 {

    int function(int n, int k) {
        if (n == 1) {
            return 1;
        }
        int prev_survivor = function(n - 1, k);
        int curr_survivor = ((prev_survivor + k - 1) % n) + 1;
        return curr_survivor;
    }


}