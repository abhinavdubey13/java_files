package personal.String;
import java.util.*;

/**
 *
 * leetcode :  1451
 *
 * Given a sentence text (A sentence is a string of space-separated words) in the following format:
 *
 * First letter is in upper case.
 * Each word in text are separated by a single space.
 * Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths.
 * If two words have the same length, arrange them in their original order.
 *
 *
 * Input: text = "Leetcode is cool"
 * Output: "Is cool leetcode"
 * Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and "cool" of length 4.
 * Output is ordered by length and the new first word starts with capital letter
 *
 *
 */

/**
 *
 * usng custom comparator
 *
 * tc = nlogn
 * sc = n
 *
 */

public class lc_1_rearrange_words_by_length {

    public static void main(String[] args) {
//        String text = "Leetcode is cool";

        String text = "Keep calm and code on";

        String ans = new Solution_1().function(text);
        System.out.println(ans);
    }


}

class Helper1 {
    String word;
    int occurance_id;
    int length;

    Helper1(String s, int o, int l) {
        this.word = s;
        this.occurance_id = o;
        this.length = l;
    }
}

class Solution_1 {

    String function(String text) {

        text = text.toLowerCase();
//        text = ('a' + (text.charAt(0) - 'A')) + text.substring(1);
        String[] tokens = text.split("\\s+");

        List<Helper1> list = new LinkedList<>();

        for (int i = 0; i < tokens.length; i++) {
            list.add(new Helper1(tokens[i], i, tokens[i].length()));
        }

        Collections.sort(list, new Comparator<Helper1>() {
            @Override
            public int compare(Helper1 o1, Helper1 o2) {
                return o1.length == o2.length ? o1.occurance_id - o2.occurance_id : o1.length - o2.length;
            }
        });

        String answer = "";

        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                answer += list.get(i).word;
            } else {
                answer += list.get(i).word + " ";
            }
        }

        answer = answer.substring(0,1).toUpperCase() + answer.substring(1);

        return answer;


    }

}
