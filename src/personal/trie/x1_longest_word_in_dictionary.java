package personal.trie;

import personal.aaa_shared.TrieNode;

import java.util.*;

/**
 *
 * leetcode : 720
 *
 * Given an array of strings words representing an English Dictionary,
 * return the longest word in words that
 * can be built one character at a time by other words in words.
 *
 * If there is more than one possible answer,
 * return the longest word with the smallest lexicographical order.
 *
 * If there is no answer, return the empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["w","wo","wor","worl","world"]
 * Output: "world"
 * Explanation: The word "world" can be built one character at a time by
 * "w", "wo", "wor", and "worl".
 *
 *
 * Example 2:
 * Input: words = ["a","banana","app","appl","ap","apply","apple"]
 * Output: "apple"
 * Explanation: Both "apply" and "apple" can be built from other words in the dictionary.
 * However, "apple" is lexicographically smaller than "apply"
 *
 */

/**
 *
 *
 * using trie
 *
 *
 * basically it boils down to ,
 * for each word , check if prefix is already in trie or not
 *
 *
 *
 */


public class x1_longest_word_in_dictionary {

    public static void main(String[] args) {

//        String[] arr = {"world", "w", "wo", "wor", "worl"};

        String[] arr = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        String ans = new Solution_1().function(arr);
        System.out.println(ans);


    }
}


class Solution_1 {


    String function(String[] words) {

        if (words.length == 1) {
            return words[0].length() == 1 ? words[0] : "";
        }

        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length())
                    return o1.length() - o2.length();
                return o1.compareTo(o2);
            }
        });


        TrieNode root = new TrieNode();
        String answer = "";
        for (String w : words) {
            boolean status = insert(w, root);
            if (status) {
                //lexico graphical order
                if (answer.length() == w.length()) {
                    int val = answer.compareTo(w);
                    if (val >= 0) {
                        answer = w;
                    }
                }

                // larger length
                else {
                    answer = (answer.length() < w.length()) ? w : answer;
                }
            }
        }

        return answer;


    }


    boolean insert(String s, TrieNode root) {
        if (s == null || s.length() == 0) return false;
        TrieNode ptr = root;
        boolean bool = true;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';

            //CHECKING HERE
            if (i > 0 && ptr != null) {
                bool &= ptr.end_of_word > 0;
            }

            if (ptr.children[idx] == null) {
                ptr.children[idx] = new TrieNode();
            }
            ptr = ptr.children[idx];
        }

        ptr.end_of_word++;
        return (n == 1) ? true : bool;
    }
}