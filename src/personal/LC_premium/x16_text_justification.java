package personal.LC_premium;
import java.util.*;


/**
 * leetcode id : 68
 *
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 */



/**
 *
 * get i,j from [] denoting string i..j will be in 1 string
 *
 * then spacing logic
 *
 */

public class x16_text_justification {

    public static void main(String[] args) {

//        String[] arr = {"This", "is", "an", "example", "of", "text", "justification."};
//               int w = 16;

//        String[] arr = {"What", "must", "be", "acknowledgment", "shall", "be"};
//        int w = 16;


        String[] arr = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        int w = 20;

//        String[] arr = {"ask", "not", "what", "your", "country", "can", "do", "for", "you", "ask", "what", "you", "can", "do", "for", "your", "country"};
//        int w = 16;


        List<String> ans = new Solution_16().function(arr, w);
        for (String s : ans)
            System.out.println(s);

    }
}


class Solution_16 {

    List<String> function(String[] arr, int w) {

        List<String> ans = new LinkedList<>();

        int i;

        for (i = 0; i < arr.length; ) {
            int j = find_end_idx(i, w, arr);
            if (j != arr.length - 1) {
                String s = get_string(i, j, w, arr);
                ans.add(s);
            }

            //for last set of words , the logic is slightly different
            else {
                break;
            }
            i = j + 1;
        }

        //last string
        StringBuffer app = new StringBuffer("");
        for (int k = i; k < arr.length; k++) {
            if (k == i) {
                app.append(arr[k]);
            } else {
                app.append(" ");
                app.append(arr[k]);
            }

        }


        //trailing spaces in last sentence
        for (int k = app.length() + 1; k <= w; k++) {
            app.append(' ');
        }


        ans.add(app.toString());
        return ans;
    }

    String get_string(int i, int j, int w, String[] arr) {
        StringBuffer return_str = new StringBuffer("");

        //for 1 letter in a sentence
        if (j == i) {
            String x = arr[i];
            for (int k = x.length() + 1; k <= w; k++) {
                x = x.concat(" ");
            }
            return x;
        }

        int word_len = 0;
        for (int k = i; k <= j; k++) {
            word_len += arr[k].length();
        }


        //logic for even distribution of spaces
        int[] space_arr = new int[j - i];
        int jagah_left = j - i;
        int spaces_left = w - word_len;

        for (int k = 0; k < space_arr.length; k++) {
            int x = (int) Math.ceil((double) spaces_left / jagah_left);
            jagah_left -= 1;
            spaces_left -= x;
            space_arr[k] = x;
        }

        int ptr = 0;


        for (int k = i; k <= j; k++) {
            if (k == i) {
                return_str.append(arr[k]);
            } else {
                for (int l = 0; l < space_arr[ptr]; l++) {
                    return_str.append(" ");
                }
                return_str.append(arr[k]);
                ptr++;
            }

        }

        for (int k = return_str.length() + 1; k <= w; k++) {
            return_str.append(" ");
        }

        return return_str.toString();
    }

    int find_end_idx(int start, int width, String[] arr) {

        int overall = 0;
        int i = start;
        int n = arr.length;

        for (; i < n; i++) {

            int len = (i == start) ? arr[i].length() : 1 + arr[i].length();

            if (overall + len < width) {
                overall += len;
            } else if (overall + len == width) {
                return i;
            } else {
                return i - 1;
            }
        }

        return (i == n) ? n - 1 : i;

    }

}
