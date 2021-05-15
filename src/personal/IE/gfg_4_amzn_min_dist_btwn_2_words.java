package personal.IE;

/**
 *
 * Given a list of words followed by two words,
 *
 * the task to find the minimum distance between the given two words in the list of words
 *
 *
 *
 * S = {"geeks", "for", "geeks", "contribute",
 *      "practice"}
 *
 * word1 = "geeks"
 * word2 = "practice"
 * Output: 2
 * Explanation: Minimum distance between the
 * words "geeks" and "practice" is 2
 *
 *
 * NOTE :  word1 can occur before/after word2
 *
 */

/**
 *
 * keep index of last occurance of word1 and word2
 * initially both are -1
 *
 *
 * and if both are not -1 : find absolute distance
 *
 *
 *
 */


public class gfg_4_amzn_min_dist_btwn_2_words {
    public static void main(String[] args) {
    }
}


class Solution_4 {

    public int shortestDistance(String[] words, String word1, String word2) {

        if (word1.equals(word2)) {
            return 0;
        }

        int idx_1 = -1, idx_2 = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                idx_1 = i;
            } else if (words[i].equals(word2)) {
                idx_2 = i;
            }
            if (idx_1 != -1 && idx_2 != -1) {
                min = Math.min(min, Math.abs(idx_1 - idx_2));
            }
        }

        return min;
    }

}
