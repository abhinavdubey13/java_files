package personal.code_chef.DP;

import personal.aaa_shared.Reader;

import java.util.Scanner;

/**
 * code-chef id : XYSTR
 */


public class cc_1_XYSTR {

    public static void main(String[] args) {
        Reader reader = new Reader();
        //Scanner reader = new Scanner(System.in);

        int t = reader.nextInt();
//        reader.next();
        Solution_1 soln = new Solution_1();

        while (t-- > 0) {
            String input = reader.nextLine();
            int ans = soln.function(input);
            System.out.println(ans);
        }
    }


}


class Solution_1 {

    int function(String s) {

        if (s == null || s.length() < 2) {
            return 0;
        }

        int n = s.length();

        if (n == 2) {
            return s.charAt(0) == s.charAt(1) ? 0 : 1;
        }

        int[] dp = new int[n];
        dp[1] = s.charAt(0) == s.charAt(1) ? 0 : 1;

        for (int i = 2; i < n; i++) {
            char curr = s.charAt(i);
            char prev = s.charAt(i - 1);

            if (curr == prev) {
                dp[i] = dp[i - 1];
            } else {
                int pair = 1 + dp[i - 2];
                int no_pair = dp[i - 1];
                dp[i] = Math.max(pair, no_pair);
            }
        }
        return dp[n - 1];
    }


}