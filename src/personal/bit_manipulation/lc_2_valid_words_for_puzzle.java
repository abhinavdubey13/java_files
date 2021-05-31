package personal.bit_manipulation;

import java.util.*;


/**
 * leetcode : 1178
 *
 *
 * With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
 * word contains the first letter of puzzle.
 * For each letter in word, that letter is in puzzle.
 *
 *
 * For example, if the puzzle is "abcdefg",
 * then valid words are "faced", "cabbage", and "baggage";
 * while invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).
 *
 *
 *
 * Return an array answer, where answer[i] is the number of words in the given word list words that are valid with respect to the puzzle puzzles[i].
 */


/**
 *
 * https://www.youtube.com/watch?v=9jV0CC_C26s&list=PL-Jc9J83PIiFJRioti3ZV7QabwoJK6eKe&index=7
 *
 * bit manipulation
 *
 */

public class lc_2_valid_words_for_puzzle {
    public static void main(String[] args) {

        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};

        List<Integer> ans = new Solution_5().findNumOfValidWords(words, puzzles);

        if (ans != null)
            for (int i : ans)
                System.out.print(i + " ");

        System.out.println();
    }
}


class Solution_5 {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

        Map<Character, List<Integer>> hmap = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            hmap.put((char) ('a' + i), new LinkedList<>());
        }

        for (String word : words) {
            //form mask for this work
            int wmask = 0;
            for (char c : word.toCharArray()) {
                int times = c - 'a';
                wmask = wmask | (1 << times);
            }

            //put this mask against every character's list
            //character occuring in word
            for (char c : word.toCharArray()) {
                hmap.get(c).add(wmask);
            }
        }


        List<Integer> ans = new LinkedList<>();

        for (String puzzle : puzzles) {

            //form mask for this puzzle
            int pmask = 0;
            for (char c : puzzle.toCharArray()) {
                int times = c - 'a';
                pmask = pmask | (1 << times);
            }


            //get all words which contain the first char of puzzle
            char first = puzzle.charAt(0);
            List<Integer> words_to_chek = hmap.get(first);
            Set<Integer> hset = new HashSet<>();

            int count = 0;
            for (int i : words_to_chek) {
                //avoid counting duplicates
                if (hset.contains(i)) {
                    continue;
                }
                hset.add(i);
                if ((i & pmask) == i) {
                    count++;
                }

            }
            ans.add(count);


        }

        return ans;

    }


}