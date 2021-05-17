package personal.IE;

import java.util.*;


/**
 *
 * https://www.geeksforgeeks.org/find-array-strings-can-chained-form-circle-set-2/
 *
 * Given an array of strings,
 * find if the given strings can be chained to form a circle.
 * A string X can be put before another string Y in a circle if the last character of X is the same
 * as the first character of Y.
 *
 */


/**
 *
 * using DFS
 *
 */


public class gfg_8_vzry_check_if_circle_of_string_possible {
    public static void main(String[] args) {

//        String[] arr = {"geek", "king"}; //true
//        String[] arr = {"for", "geek", "rig", "kaf"}; //true
//        String[] arr = {"aab", "bac", "aaa", "cda"}; //true
//        String[] arr = {"aaa", "bbb", "baa", "aab"};//true
//        String[] arr = {"abc", "efg", "cde", "ghi", "ija"};//true

        String[] arr = {"ijk", "kji", "abc", "cba"};//false


        boolean answer = new Solution_8().function(arr);
        System.out.println(answer);
    }
}


class Solution_8 {

    Map<String, Integer> freq_map;
    Map<Character, List<String>> starts_with_map;


    boolean function(String[] arr) {

        if (arr == null || arr.length == 0 || arr.length == 1) {
            if (arr.length == 1) {
                return (arr[0].charAt(0) == arr[0].charAt(arr[0].length() - 1));
            }
            return true;
        }

        init_maps(arr);

        String start = arr[0].charAt(0) + "" + arr[0].charAt(arr[0].length() - 1);
        boolean ans = dfs(start, arr.length);
        return ans;

    }


    boolean dfs(String curr, int words_left) {

        if (words_left == 0) {
            return true;
        }

        //neighbour which start which the last char of curr
        List<String> ngbrs = starts_with_map.get(curr.charAt(1));

        for (String n : ngbrs) {
            int freq = freq_map.getOrDefault(n, -1);

            if (freq > 0) {
                freq_map.put(n, freq - 1);
                boolean res = dfs(n, words_left - 1);

                if (res)
                    return true;
                else
                    freq_map.put(n, freq);
            }
        }


        return false;
    }

    void init_maps(String[] arr) {
        freq_map = new HashMap<>();
        starts_with_map = new HashMap<>();

        for (String s : arr) {
            String key_1 = s.charAt(0) + "" + s.charAt(s.length() - 1);
            char key_2 = s.charAt(0);

            //map-1
            freq_map.put(key_1, 1 + freq_map.getOrDefault(key_1, 0));

            //map-2
            if (!starts_with_map.containsKey(key_2)) {
                starts_with_map.put(key_2, new LinkedList<>());
            }
            starts_with_map.get(key_2).add(key_1);
        }


    }

}
