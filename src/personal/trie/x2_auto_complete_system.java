package personal.trie;

import personal.models.TrieNode;

import java.util.*;

/**
 * leetcode :
 */

/**
 *
 */

class TrieNode_2 {
    int eow;
    TrieNode_2[] next;
    Map<String, Integer> words_here;

    TrieNode_2() {
        eow = 0;
        next = new TrieNode_2[27]; //a->z + space : for space use next[26]
        words_here = new HashMap<>();
    }

}


public class x2_auto_complete_system {
    public static void main(String[] args) {
    }
}


class AutocompleteSystem {

    TrieNode_2 ROOT;
    StringBuffer input_till_now;

    public AutocompleteSystem(String[] sentences, int[] times) {
        ROOT = new TrieNode_2();
        input_till_now = new StringBuffer("");

    }

    public List<String> input(char c) {
        if (c == '#') {
            add(input_till_now, 1);
            input_till_now = new StringBuffer(""); //reset input
            return new LinkedList<>();
        }

        input_till_now.append(c);

        return null;

    }


    private void add(StringBuffer s, int count) {
        if (s == null || s.length() == 0) {
            return;
        }
        TrieNode_2 ptr = ROOT;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int idx = (c == ' ') ? 26 : c - 'a';

            if (ptr.next[idx] == null) {
                ptr.next[idx] = new TrieNode_2();
            }

            ptr = ptr.next[idx];
            ptr.words_here.put(s.toString(), count);
        }

        ptr.eow++;
    }
}
