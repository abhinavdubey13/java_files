package personal.IE;

import java.util.*;

/**
 * Given an array consisting of only prime numbers, remove all duplicate numbers from it
 *
 * A[] = {2,2,3,3,7,5}
 * Output: 2 3 7 5
 * Explanation: After removing the duplicate
 * 2 and 3 we get 2 3 7 5
 *
 * Retain only the first occurrence of the duplicate element.
 *
 * The elements in the returning array should be in the same order as they appear in the original array.
 */

/**
 *
 * using set and index iterator
 *
 */


public class gfg_1_amzn_remove_duplicates_from_array_of_primes {

    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 5};
        int idx = new Solution_1().function(arr);

        for (int i = 0; i <= idx; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();


    }
}


class Solution_1 {

    int function(int[] arr) {

        int n = arr.length;

        if (n < 2) {
            return n;
        }


        int i = 0;
        int j = 1;
        Set<Integer> hset = new HashSet<>();
        hset.add(arr[0]);


        while (j < n) {
            int arr_j = arr[j];
            if (hset.contains(arr_j)) {
                j++;
            } else {
                //swap (i+1)  and j
                swap(arr, i + 1, j);
                j++;
                i++;
                hset.add(arr_j);
            }
        }

        return i;
    }


    void swap(int[] arr, int i, int j) {
        if (i >= 0 && i < arr.length && j >= 0 && j < arr.length) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }

}
