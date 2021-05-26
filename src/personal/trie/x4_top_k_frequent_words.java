package personal.trie;


import java.util.*;

/**
 * leetcode : 692
 *
 *
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest.
 *
 * If two words have the same frequency,
 * then the word with the lower alphabetical order comes first.
 *
 *
 *
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 *
 *
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 * with the number of occurrence being 4, 3, 2 and 1 respectively
 *
 *
 */

/**
 *
 * 1. form hashmap of frequncy
 * 2. insert words according to the freq. in an [] of trie node
 *
 * ie. all words with frequncy 4 will go into the same slot
 * likewise ...
 *
 * 3. derive all words in lexicographical order , in decresing order of freq.
 *
 * ie. get all words with freq 4 , in lexicographical order
 * then get all words with freq 3 , in lexicographical order ..
 *
 *
 *
 *
 *
 */

class TrieNode_4 {
    int eow;
    TrieNode_4[] next;
    String word_here;

    TrieNode_4() {
        eow = 0;
        next = new TrieNode_4[26];
        word_here = "";
    }

}


public class x4_top_k_frequent_words {
    public static void main(String[] args) {

        String[] sentences = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        List<String> list = new Solution_4().topKFrequent(sentences, k);

        for (String x : list) {
            System.out.println(x);
        }

    }
}


class Solution_4 {

    TrieNode_4[] ROOT_ARR;

    public List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> freq_map = new HashMap<>();
        int max_freq = 0;
        for (String s : words) {
            freq_map.put(s, 1 + freq_map.getOrDefault(s, 0));
            max_freq = Math.max(max_freq, freq_map.get(s));
        }

        ROOT_ARR = new TrieNode_4[max_freq + 1];

        for (Map.Entry<String, Integer> entry : freq_map.entrySet()) {
            int freq = entry.getValue();
            if (ROOT_ARR[freq] == null) {
                ROOT_ARR[freq] = new TrieNode_4();
            }
            add_to_bucket(ROOT_ARR[freq], entry.getKey());
        }


        List<String> ans = new LinkedList<>();

        for (int i = max_freq; i >= 1 && k > 0; i--) {
            List<String> words_with_freq_i = new LinkedList<>();
            get_words(ROOT_ARR[i], words_with_freq_i);

            if (words_with_freq_i.size() < k) {
                ans.addAll(words_with_freq_i);
                k -= words_with_freq_i.size();
            } else {
                for (int j = 0; j < k; j++) {
                    ans.add(words_with_freq_i.get(j));
                }
                break;
            }
        }


        return ans;
    }

    void get_words(TrieNode_4 curr, List<String> list_to_return) {
        if (curr == null) {
            return;
        }

        if (curr.eow > 0) {
            list_to_return.add(curr.word_here);
        }

        for (int i = 0; i < 26; i++) {
            get_words(curr.next[i], list_to_return);
        }

    }

    void add_to_bucket(TrieNode_4 root, String s) {
        TrieNode_4 ptr = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = c - 'a';
            if (ptr.next[idx] == null) {
                ptr.next[idx] = new TrieNode_4();
            }
            ptr = ptr.next[idx];
        }
        ptr.eow++;
        ptr.word_here = s;
    }

}