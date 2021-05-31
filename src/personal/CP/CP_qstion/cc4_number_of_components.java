package personal.CP.CP_qstion;

import personal.aaa_shared.Reader;

/**
 *
 */

/**
 *
 *
 */

public class cc4_number_of_components {

    public static void main(String[] args) {
        Reader r = new Reader();
        int t = r.nextInt();
        Solution_1 s = new Solution_1();

        while (t-- > 0) {
            int n = r.nextInt();
            int ans = s.fun(n);
            System.out.println(ans);
        }
    }
}


class Solution_1 {

    int fun(int n) {

        if (n == 1) {
            return 1;
        }


        int pairs = (n - 1) / 2;

        return pairs + 1;


    }


}