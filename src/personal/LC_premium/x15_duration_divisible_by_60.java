package personal.LC_premium;

import java.util.*;


/**
 * leetcode id : 1010
 *
 *
 * You are given a list of songs where the ith song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.
 * Formally, we want the number of indices i, j such that
 * i < j with (time[i] + time[j]) % 60 == 0.
 *
 *
 *
 * Example 1:
 *
 * Input: time = [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 *
 *
 * Example 2:
 * Input: time = [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 */


/**
 *
 * using freq map
 *
 */

public class x15_duration_divisible_by_60 {

    public static void main(String[] args) {

        int[] arr = {30, 20, 150, 100, 40};
//        int[] arr = {60, 60, 60};
        int ans = new Solution_15().function(arr);

        System.out.println(ans);

    }
}


class Solution_15 {

    int function(int[] arr) {

        Map<Integer, Integer> hmap = new HashMap<>();

        for (int i : arr) {
            int remainder = i % 60;
            hmap.put(remainder, 1 + hmap.getOrDefault(remainder, 0));
        }

        int ans = 0;

        for (int i : arr) {
            int remainder = i % 60;
            int freq = hmap.getOrDefault(remainder, 0);
            int anti_freq = hmap.getOrDefault(60 - remainder, 0);


            if (remainder == 0 || remainder == 30) {
                ans += (freq * (freq - 1)) / 2;
                hmap.remove(remainder);
            } else if (remainder > 0 && freq > 0 && anti_freq > 0) {
                if (remainder != 30) {
                    ans += freq * anti_freq;
                    hmap.remove(remainder);
                    hmap.remove(60 - remainder);
                }
            }

        }

        return ans;


    }

}
