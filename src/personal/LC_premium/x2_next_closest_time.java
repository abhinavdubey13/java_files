package personal.LC_premium;

import java.util.*;

/**
 * leetcode id : 681
 *
 *
 * Given a time represented in the format "HH:MM",
 * form the next closest time by reusing the current digits.
 * There is no limit on how many times a digit can be reused.
 *
 * You may assume the given input string is always valid.
 * For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 *
 *
 * example :
 * 19:34 -> 19:39
 * 23:59 -> 22:22
 *
 */


/**
 *
 * 1. brute force : add 1 minute to curr time and check if all digits are present in input
 *
 * 2. optimal
 *
 * This approach here is trying to find next digit for each postion in "HH:MM" from right to left. If the next digit is greater than current digit, return directly and keep other digits unchanged.
 * Here is the steps: (e.g. "17:38")
 *
 * Retrieve all four digits from given string and sort them in asscending order, "17:38" -> digits[] {'1', '3', '7', '8'}
 *
 * Apply findNext() from the right most digit to left most digit, try to find next greater digit from digits[] (if exist) which is suitable for current position, otherwise return the minimum digit (digits[0]):
 *
 * "HH:M_": there is no upperLimit for this position (0-9). Just pick the next different digit in the sequence. In the example above, '8' is already the greatest one, so we change it into the smallest one (digits[0] i.e. '1') and move to next step: "17:38" -> "17:31"
 *
 * "HH:_M": the upperLimit is '5' (00~59). The next different digit for '3' is '7', which is greater than '5', so we should omit it and try next. Similarly, '8' is beyond the limit, so we should choose next, i.e. '1': "17:38" -> "17:11"
 *
 * "H_:MM": the upperLimit depends on result[0]. If result[0] == '2', then it should be no more than '3'; else no upperLimit (0-9). Here we have result[0] = '1', so we could choose any digit we want. The next digit for '7' is '8', so we change it and return the result directly. "17:38" -> "18:11"
 *
 * "_H:MM": the upperLimit is '2' (00~23). e.g. "03:33" -> "00:00"
 *
 *
 */

public class x2_next_closest_time {

    public static void main(String[] args) {

//        String input = "04:59";
//        String input = "19:34";
//        String input = "23:59";
        String input = "06:49";


//        String str = new Solution_2_brute_force().function(input);

        String str = new Solution_2_optimal().function(input);


        System.out.println(str);

    }
}


class Solution_2_brute_force {

    Set<Character> hset;

    String function(String input) {

        hset = new HashSet<>();
        for (char c : input.toCharArray()) {
            hset.add(c);
        }

        String x = input;
        while (true) {

            String y = add_1_minute(x);
            if (check(y)) {
                return y;
            }
            x = y;

        }
    }

    String add_1_minute(String x) {
        String[] tokens = x.split(":");
        int hh = Integer.parseInt(tokens[0]);
        int mm = Integer.parseInt(tokens[1]);
        String ans = "";
        if (mm == 59) {
            if (hh == 23)
                ans = "00:00";
            else if (hh <= 8) {
                hh++;
                ans = "0" + hh + ":00";
            } else {
                ans = ((hh + 1) + ":00");
            }
        } else {
            mm++;
            if (hh < 10) {
                ans = "0" + hh + ":" + ((mm < 10) ? "0" + mm : mm);
            } else {
                ans = hh + ":" + ((mm < 10) ? "0" + mm : mm);
            }
        }

        //System.out.println(x + " --> " + ans);
        return ans;
    }


    boolean check(String x) {
        for (char i : x.toCharArray()) {
            if (!hset.contains(i))
                return false;
        }
        return true;
    }

}


class Solution_2_optimal {

    TreeSet<Character> treeSet;

    String function(String input) {
        treeSet = new TreeSet<>();
        char[] result = input.toCharArray();
        for (char c : result) {
            treeSet.add(c);
        }

        //if time = xx:xx (11:11 , 22:22 ...)
        if (treeSet.size() == 1) return input;

        //last digit
        result[4] = find_next(result[4], '9');
        if (result[4] > input.charAt(4)) return String.valueOf(result);

        //2nd last digit
        result[3] = find_next(result[3], '5');
        if (result[3] > input.charAt(3)) return String.valueOf(result);


        //2nd digit
        result[1] = find_next(result[1], result[0] == '2' ? '3' : '9');
        if (result[3] > input.charAt(1)) return String.valueOf(result);

        //1st digit
        result[0] = find_next(result[0], '2');
        return String.valueOf(result);

    }

    char find_next(char curr, char max_allowed) {
        Character nxt = treeSet.higher(curr);
        if (nxt == null || nxt > max_allowed) {
            nxt = treeSet.first(); //smallest char in input
        }
        return nxt;
    }

}