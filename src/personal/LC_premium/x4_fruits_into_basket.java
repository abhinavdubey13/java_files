package personal.LC_premium;

import java.util.*;

/**
 * leetcode id : 904
 */


/**
 *
 * sliding window concept
 *
 *
 * reduces to => find max length of all subarrays having atmost 2 distinct elements
 *
 */

public class x4_fruits_into_basket {

    public static void main(String[] args) {

        int[] arr = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};

        int ans = new Solution_4().function(arr);

        System.out.println(ans);

    }
}


class Solution_4 {

    int function(int[] arr) {
        int w_start = 0;
        int w_end = 0;
        int maxLength = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (; w_end < arr.length; w_end++) {
            map.put(arr[w_end], w_end);
            if (map.size() > 2) {
                int minIndex = Collections.min(map.values());
                map.remove(arr[minIndex]);
                w_start = minIndex + 1;
            }
            maxLength = Math.max(maxLength, w_end - w_start + 1);
        }
        return maxLength;


    }

}
