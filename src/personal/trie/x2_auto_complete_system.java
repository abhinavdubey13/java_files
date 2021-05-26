package personal.trie;


import java.util.*;

/**
 * leetcode : 642
 *
 * Design a search autocomplete system for a search engine.
 * Users may input a sentence (at least one word and end with a special character '#').
 *
 * You are given a string array sentences and an integer array times both of length n where sentences[i] is a previously typed sentence and times[i] is the corresponding number of times the sentence was typed. For each input character except '#', return the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed.
 *
 * Here are the specific rules:
 *
 * 1. The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * 2. The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same hot degree, use ASCII-code order (smaller one appears first).
 * 3. If less than 3 hot sentences exist, return as many as you can.
 * 4. When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 *
 *
 *
 *
 * Implement the AutocompleteSystem class:
 *
 * AutocompleteSystem(String[] sentences, int[] times) Initializes the object with the sentences and times arrays.
 * List<String> input(char c) This indicates that the user typed the character c.
 * Returns an empty array [] if c == '#' and stores the inputted sentence in the system.
 *
 * Returns the top 3 historical hot sentences that have the same prefix as the part of
 * the sentence already typed. If there are fewer than 3 matches, return them all.
 *
 *
 *
 */

/**
 *
 * Only thing more than a normal Trie is added a map of sentence
 *
 * to count in each of the Trie node to facilitate process of getting top 3 results.
 *
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

        String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
        List<String> param_1 = obj.input('i');

        for (String x : param_1) {
            System.out.println(x);
        }

    }
}


class Pair {
    String str;
    int count;

    Pair(String str, int c) {
        this.str = str;
        this.count = c;
    }
}

class AutocompleteSystem {

    TrieNode_2 ROOT;
    StringBuffer input_till_now;

    public AutocompleteSystem(String[] sentences, int[] times) {
        ROOT = new TrieNode_2();
        input_till_now = new StringBuffer("");

        for (int i = 0; i < times.length; i++) {
            add(new StringBuffer(sentences[i]), times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            add(input_till_now, 1);
            input_till_now = new StringBuffer(""); //reset input
            return new LinkedList<>();
        }

        input_till_now.append(c);

        //traverse the word in trie
        TrieNode_2 ptr = ROOT;
        for (int i = 0; i < input_till_now.length(); i++) {
            char curr = input_till_now.charAt(i);
            int idx = (curr == ' ') ? 26 : curr - 'a';
            if (ptr.next[idx] == null) {
                return new LinkedList<>();
            }
            ptr = ptr.next[idx];
        }

        //make heap of the words at that index
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.count == b.count ? a.str.compareTo(b.str) : -1 * (a.count - b.count));

        for (String s : ptr.words_here.keySet()) {
            pq.add(new Pair(s, ptr.words_here.get(s)));
        }

        List<String> result = new LinkedList<>();

        for (int i = 0; i < 3 && pq.size() > 0; i++) {
            result.add(pq.poll().str);
        }

        return result;

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

            //EXTRA STEP
            ptr.words_here.put(s.toString(), count);
        }
        ptr.eow++;
    }
}
