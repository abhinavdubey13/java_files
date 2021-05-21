package personal.LC_premium;

import java.util.*;

/**
 * leetcode id : 472
 *
 *
 * Given an array of strings words (without duplicates),
 * return all the concatenated words in the given list of words.
 *
 * A concatenated word is defined as a string that is comprised entirely of
 * at least two shorter words in the given array.
 */


/**
 * solution-1 : using tries
 *
 *
 * solution-2 : using DP
 *
 * similar to word break problem : given dictionary ,
 * check if text can be broken into all words of dictionary
 *
 * If you do know one optimized solution for above question is using DP,
 * this problem is just one more step further.
 * We iterate through each word and see if it can be formed by using other words.
 *
 * Of course it is also obvious that a word can only be formed by words shorter than it.
 * So we can first sort the input by length of each word, and only try to form one word by using words in front of it.
 */


class TrieNode {
    TrieNode[] children;
    int end_of_word;

    TrieNode() {
        this.children = new TrieNode[26];
    }
}


public class x8_concatenated_words {

    public static void main(String[] args) {

        String[] arr = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        List<String> ans = new Solution_8_trie().function(arr);

        for (String s : ans) {
            System.out.println(s);
        }

    }
}


class Solution_8_trie {

    List<String> function(String[] arr) {
        List<String> res = new ArrayList<>();
        TrieNode root = new TrieNode();

        // construct Trie tree
        for (String s : arr) {
            insert(s, root);
        }


        // test word is a concatenated word or not
        for (String word : arr) {
            if (word.length() == 0) {
                continue;
            }
            boolean x = search(word, 0, root, 0);
            if (x) {
                res.add(word);
            }
        }
        return res;
    }


    // count_of_concatenated_words means how many words during the search path
    boolean search(String s, int idx, TrieNode root, int count_of_concatenated_words) {

        TrieNode curr = root;
        int n = s.length();
        for (int i = idx; i < n; i++) {
            char x = s.charAt(i);
            int trie_idx = x - 'a';
            if (curr.children[trie_idx] == null) {
                return false;
            }

            if (curr.children[trie_idx].end_of_word > 0) {
                if (i == n - 1) {
                    return count_of_concatenated_words > 0;
                }

                if (search(s, i + 1, root, count_of_concatenated_words + 1)) {
                    return true;
                }
            }

            curr = curr.children[trie_idx];
        }

        return false;

    }

    void insert(String s, TrieNode root) {
        if (s == null || s.length() == 0) return;
        TrieNode ptr = root;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (ptr.children[idx] == null) {
                ptr.children[idx] = new TrieNode();
            }
            ptr = ptr.children[idx];
        }
        ptr.end_of_word++;
    }

}


class Solution_8_DP {

    List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> dictionary = new HashSet<>();

        //sort according to lenghth
        Arrays.sort(words, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        for (int i = 0; i < words.length; i++) {
            if (canForm(words[i], dictionary)) {
                result.add(words[i]);
            }
            dictionary.add(words[i]);
        }

        return result;
    }


    boolean canForm(String word, Set<String> dictionary) {
        if (dictionary.isEmpty()) return false;
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (!dp[j]) continue;
                if (dictionary.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }

}
