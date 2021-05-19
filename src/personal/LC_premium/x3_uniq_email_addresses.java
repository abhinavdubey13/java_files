package personal.LC_premium;

import java.util.*;

/**
 * leetcode id : 929
 *
 * Every valid email consists of a local name and a domain name, separated by the '@' sign. Besides lowercase letters, the email may contain one or more '.' or '+'.
 *
 * For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
 * If you add periods '.' between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name. Note that this rule does not apply to domain names.
 *
 * For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
 * If you add a plus '+' in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered. Note that this rule does not apply to domain names.
 *
 * For example, "m.y+name@email.com" will be forwarded to "my@email.com".
 * It is possible to use both of these rules at the same time.
 *
 * Given an array of strings emails where we send one email to each email[i], return the number of different addresses that actually receive mails.
 */


/**
 *
 * hash set
 *
 */

public class x3_uniq_email_addresses {

    public static void main(String[] args) {

//        String[] emails = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        String[] emails = {"test.email+alex@leetcode.com", "test.email.leet+alex@code.com"};

        int ans = new Solution_3().function(emails);

        System.out.println(ans);

    }
}


class Solution_3 {

    int function(String[] emails) {

        Set<String> hset = new HashSet<>();

        for (String s : emails) {
            String[] tokens = s.split("@");

            if (tokens[0].contains("+")) {
                tokens[0] = s.substring(0, s.indexOf('+'));
            }

            tokens[0] = tokens[0].replaceAll("\\.", "");
            String finall_addr = tokens[0] + "@" + tokens[1];
            hset.add(finall_addr);
        }

        return hset.size();
    }

}
