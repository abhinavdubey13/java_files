package personal.CP.rough;

import personal.aaa_shared.Reader;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */

/**
 *
 *
 *
 */

public class cc3_charges {

    public static void main(String[] args) {
        Reader r = new Reader();
        int t = r.nextInt();
        Solution_1 s = new Solution_1();

        while (t-- > 0) {
            int D = r.nextInt();
            int d = r.nextInt();
            int P = r.nextInt();
            int Q = r.nextInt();


            System.out.println(s.fun(D, d, P, Q));
        }
    }
}


class Solution_1 {

    long fun(int D, int d, int p, int q) {
        int rem = D % d;
        int quo = D / d;

        long times = (rem == 0) ? quo : (quo + 1);
        long t = times - 1;
        long x = (rem == 0) ? d : rem;

        long a = (t * d * p) + (d * q * ((t * (t - 1)) / 2));
        long b = (x * p) + (t * q * x);

        return (a + b);
    }


}


