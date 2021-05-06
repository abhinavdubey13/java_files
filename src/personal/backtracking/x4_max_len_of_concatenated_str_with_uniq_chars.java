package personal.backtracking;

import java.util.*;


/**
 * Leetcode id : 1239
 *
 * Given an array of strings arr.
 * String s is a concatenation of a sub-sequence of arr which have unique characters.
 *
 * Return the maximum possible length of s
 *
 * =========
 * example :
 * =========
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 *
 *
 */


/**
 * ===========
 * approach :
 * ===========
 * personal.backtracking
 *
 * there are 2^n combinations
 *
 * we check for max length only if we reach nth index in arr
 *
 */

public class x4_max_len_of_concatenated_str_with_uniq_chars {

    public static void main(String[] args) {

//        List<String> arr = new LinkedList<>(Arrays.asList("cha", "r", "act", "ers"));

        List<String> arr = new LinkedList<>(Arrays.asList("un", "iq", "ue"));

        int answer = new Solution4().function(arr);
        System.out.println(answer);

    }
}

class Solution4 {

    int MAX_LEN;
    Set<Character> hset;

    int function(List<String> arr) {
        MAX_LEN = 0;
        hset = new HashSet<>();

        //base condition
        if (arr.size() < 2) {
            return arr.size() == 1 ? arr.get(0).length() : 0;
        }

        ///0th element can be included/excluded
        dfs(0, true, arr);
        dfs(0, false, arr);
        return MAX_LEN;
    }


    void dfs(int idx, boolean include, List<String> arr) {

        if (idx == arr.size()) {
            MAX_LEN = Math.max(MAX_LEN, hset.size());
            return;
        }

        char[] str = arr.get(idx).toCharArray();
        boolean was_added = false;

        //if current element is to be included
        if (include) {
            boolean invalid_addition = false;
            int i = 0;

            //try adding char 1-by-1 ,
            // if any char already in set , we need to undo the addition
            for (; i < str.length; i++) {
                boolean uniq = hset.add(str[i]);
                if (!uniq) {
                    invalid_addition = true;
                    break;
                }
            }

            //removal logic
            if (invalid_addition) {
                i--;
                for (; i >= 0; i--) {
                    hset.remove(str[i]);
                }
            }

            //if current string is successfullt added to set
            else {
                was_added = true;
            }


        }

        //next element can either be added/excluded from set
        dfs(idx + 1, true, arr);
        dfs(idx + 1, false, arr);


        //BACK_TRACKING
        //if current string was added in set
        //we need to remove it on personal.backtracking step
        if (was_added) {
            for (char i : str) {
                hset.remove(i);
            }
        }


    }


}
