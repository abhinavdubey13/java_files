package personal.String;

/**
 *
 * interview-bit : Count And Say
 *
 * The count-and-say sequence is the sequence of integers beginning as follows:
 *
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as one 1 or 11.
 * 11 is read off as two 1s or 21.
 *
 * 21 is read off as one 2, then one 1 or 1211.
 *
 * Given an integer n, generate the nth sequence.
 *
 * Note: The sequence of integers will be represented as a string.
 *
 * Example:
 *
 * if n = 2,
 * the sequence is 11.
 *
 *
 */

public class ib_count_and_say {

    public static void main(String[] args) {

//        String ans = new Solution().countAndSay(3);

        String ans = new Solution().countAndSay(5);

        System.out.println(ans);
    }
}


class Solution {

    String countAndSay(int A) {

        if (A == 1) {
            return "1";
        }

        if (A == 2) {
            return "11";
        }


        String prev = "1";
        String curr = "";

        for (int i = 2; i <= A; i++) {
            curr = get_next(prev);
            prev = curr;
        }
        return prev;
    }

    String get_next(String str) {

        if (str.equals("1")) {
            return "11";
        }

        int i = 0;
        int j = 1;
        int n = str.length();
        String return_str = "";

        int freq = 0;
        char c = str.charAt(0);

        while (i < n && j < n) {

            while (j < n && str.charAt(i) == str.charAt(j)) {
                j++;
            }

            if (i < n && j < n) {
                freq = j - i;
                c = str.charAt(i);
                return_str += "" + freq + c;


                i = j;
                j = j + 1;
            }


        }

        freq = j - i;
        c = str.charAt(i);
        return_str += "" + freq + c;

        return return_str;

    }


}