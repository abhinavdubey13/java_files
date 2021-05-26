package personal.trie;

import java.util.LinkedList;
import java.util.List;

/**
 * leetcode : 745
 *
 * Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.
 *
 * Implement the WordFilter class:
 *
 * 1. WordFilter(string[] words) Initializes the object with the words in the dictionary.
 *
 * 2. f(string prefix, string suffix) Returns the index of the word in the dictionary,
 * which has the prefix prefix and the suffix suffix.
 * If there is more than one valid index, return the largest of them.
 *
 *
 * If there is no such word in the dictionary, return -1.
 *
 */

/**
 *
 * Take "apple" as an example, we will insert add
 * "apple#apple",
 * "pple#apple",
 * "ple#apple",
 * "le#apple",
 * "e#apple",
 * "#apple"
 *
 * into the Trie Tree.
 *
 *
 * If the query is: prefix = "app", suffix = "le",
 * we can find it by querying our trie for
 * "le#app".
 *
 *
 * Also, compared with tradition Trie, we add the attribute weight in class TrieNode.
 *
 */

class TrieNode_7 {
    TrieNode_7[] next;
    int idx;

    TrieNode_7() {
        next = new TrieNode_7[27]; //a-z + #
        idx = -1;
    }

}


class x7_word_with_given_prefix_n_sufix {
    public static void main(String[] args) {

        String[] arr = {"apple"};
        WordFilter wordFilter = new WordFilter(arr);

        //System.out.println(1);


    }
}


class WordFilter {

    TrieNode_7 ROOT;

    public WordFilter(String[] words) {

        ROOT = new TrieNode_7();
        for (int i = 0; i < words.length; i++) {
            String x = words[i] + "#" + words[i];
            add_all(x, i);
        }

        //System.out.println(2);
    }


    public int f(String prefix, String suffix) {

        TrieNode_7 cur = ROOT;
        for (char c : (suffix + '#' + prefix).toCharArray()) {
            int idx = (c == '#') ? 26 : c - 'a';
            if (cur.next[idx] == null) {
                return -1;
            }
            cur = cur.next[idx];
        }
        return cur.idx;

    }


    void add_all(String str, int idx) {
        int i = 0;
        int n = str.length();
        while (str.charAt(i) != '#') {
            add(str.substring(i, n), idx);
            i++;
        }

    }

    void add(String x, int idx) {
        if (x == null || x.length() == 0) return;
        TrieNode_7 ptr = ROOT;
        for (int i = 0; i < x.length(); i++) {
            int trie_idx = (x.charAt(i) == '#') ? 26 : x.charAt(i) - 'a';
            if (ptr.next[trie_idx] == null) {
                ptr.next[trie_idx] = new TrieNode_7();
            }
            ptr.idx = idx;
            ptr = ptr.next[trie_idx];
        }
        ptr.idx = idx;
    }


}