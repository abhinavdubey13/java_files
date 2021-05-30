package personal.CP.CP_qstion;

import personal.aaa_shared.Reader;

import java.util.*;

/**
 * code : DEQUEUE
 *
 *
 * You are given a double-ended queue Q. Initially, it contains elements Q1,Q2,…,QM in this order. Each of these elements is an integer between 1 and N (inclusive) and each integer between 1 and N (inclusive) occurs in the queue at least once.
 *
 * We want to pop some (possibly zero) elements from the front of the queue
 * and some (not necessarily the same number, possibly zero) elements from the back.
 * Among all the popped elements, each integer between 1 and N (inclusive) should appear at least once.
 * Find the smallest possible total number of elements we need to pop
 *
 *
 *
 */

/**
 *
 * https://www.youtube.com/watch?v=T5jzmqEEXmU
 *
 * 0.store last-index of all elements in map
 *
 * 1.now WKT final answer = prefix_len + suffix_len
 *
 * 2.where prefix/suffix_len represent the number of elements to be picked from front and back respy.
 *
 * 3. fix prefix_len , and for each fixed prefix_len , try to find optimal suffix_len
 *
 * prefix_len will start from 1....M
 *
 *
 * maintain sorted set containing the indices of elements which need to pick from rear end of array
 *
 *
 *
 *
 */

public class cc2_minimum_popping {

    public static void main(String[] args) {
        Reader r = new Reader();
        int t = r.nextInt();
        Solution_2 s = new Solution_2();

        while (t-- > 0) {
            int n = r.nextInt();
            int m = r.nextInt();
            int[] arr = new int[m];

            for (int i = 0; i < m; i++) {
                arr[i] = r.nextInt();
            }

            int ans = s.fun(n, m, arr);
            System.out.println(ans);
        }
    }
}


class Solution_2 {

    int fun(int n, int arr_len, int[] arr) {

        if (n == 0) {
            return 0;
        }

        //store last index of all numbers
        Map<Integer, Integer> hmap = new HashMap<>();
        for (int i = 0; i < arr_len; i++) {
            hmap.put(arr[i], i);
        }

        TreeSet<Integer> idx_list = new TreeSet<>();

        for (Map.Entry<Integer, Integer> e : hmap.entrySet()) {
            idx_list.add(e.getValue());
        }


        int prefix_len = 0;
        int suffix_len = arr_len - idx_list.first();
        int ans = prefix_len + suffix_len;

        for (prefix_len = 1; prefix_len <= arr_len; prefix_len++) {
            int ele = arr[prefix_len - 1];

            int last_idx = hmap.get(ele);

            if (idx_list.contains(last_idx)) {
                idx_list.remove(last_idx);
                int new_len = prefix_len + ((idx_list.size() > 0) ? (arr_len - idx_list.first()) : 0);
                ans = Math.min(ans, new_len);
            }

        }

        return ans;

    }


}