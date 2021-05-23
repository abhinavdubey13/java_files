package personal.LC_premium.binary_search;

import java.util.*;

/**
 * leetcode id : 668
 *
 * Nearly everyone has used the Multiplication Table.
 * The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).
 *
 * Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.
 *
 *
 */


/**
 * application of binary search
 *
 * find range , check using binary search if MID is satisfiable answer
 *
 *
 */

public class x5_kth_smallest_number_in_multiplication_table {

    public static void main(String[] args) {

        int row = 3;
        int col = 3;
        int k = 5;
        int ans = new Solution_bs_5().function(row, col, k);
        System.out.println(ans);
    }
}


class Solution_bs_5 {

    int function(int rows, int cols, int k) {
        int low = 1;
        int high = rows * cols;
        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean is_ok = is_okay(mid, rows, cols, k);
            if (is_ok) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    boolean is_okay(int mid, int rows, int cols, int k) {

        int count = 0;
        for (int i = 1; i <= rows; i++) {
            count += Math.min(cols, mid / i);
        }


        return count >= k;

    }

}
