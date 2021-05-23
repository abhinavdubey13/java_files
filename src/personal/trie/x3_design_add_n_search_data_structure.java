package personal.trie;

import personal.models.TrieNode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode : 211
 *
 *
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * 1. WordDictionary() Initializes the object.
 *
 * 2. void addWord(word) Adds word to the data structure, it can be matched later.
 *
 * 3. bool search(word) Returns true if there is any string in the data structure that matches
 * word or false otherwise.
 *
 * NOTE : word may contain dots '.' where dots can be matched with any letter.
 *
 *
 */

/**
 * using trie
 *
 * for '.' we need to check all children
 *
 * when we reach last char , we need to check E_O_W > 0
 *
 */


public class x3_design_add_n_search_data_structure {
    public static void main(String[] args) {

        WordDictionary obj = new WordDictionary();
        obj.addWord("bad");
        obj.addWord("dad");
        obj.addWord("mad");
        System.out.println(obj.search("pad"));
        System.out.println(obj.search("bad"));
        System.out.println(obj.search(".ad"));
        System.out.println(obj.search("b.."));


    }
}

class WordDictionary {

    TrieNode ROOT;

    public WordDictionary() {
        ROOT = new TrieNode();
    }

    public void addWord(String word) {
        if (word == null || word.length() == 0) return;
        TrieNode ptr = ROOT;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (ptr.children[idx] == null) {
                ptr.children[idx] = new TrieNode();
            }
            ptr = ptr.children[idx];
        }

        ptr.end_of_word++;

    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;
        return search_util(word, 0, ROOT);

    }


    private boolean search_util(String word, int word_idx, TrieNode node) {
        int n = word.length();
        if (word_idx == n) {
            return node.end_of_word > 0;
        }


        TrieNode ptr = node;

        char curr = word.charAt(word_idx);

        if (curr == '.') {
            boolean result = false;
            for (int i = 0; i < 26; i++) {
                if (ptr.children[i] != null) {
                    boolean status = search_util(word, word_idx + 1, ptr.children[i]);
                    result = result | status;
                }
            }
            return result;
        } else {

            int idx = curr - 'a';
            if (ptr.children[idx] == null) {
                return false;
            }
            return search_util(word, word_idx + 1, ptr.children[idx]);
        }


    }
}