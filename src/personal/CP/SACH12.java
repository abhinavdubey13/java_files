package personal.CP;

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 *
 * codechef id : SACH12
 *
 * Virat is a lazy guy his teacher given a task (homework) that he has given a number and he has to find
 * a number of set bit in its binary equivallent .
 *
 * now as you know that Virat is lazy guy he given the problem to solve you , let help him to solving his homework
 *
 *
 *
 *
 */

/**
 *
 * cache the solution using map
 *
 */


class SACH12_reader {
    BufferedReader br;
    StringTokenizer st;

    public SACH12_reader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}


public class SACH12 {

    public static void main(String[] args) {

        SACH12_reader sc = new SACH12_reader();
        int t = sc.nextInt();
        SACH12_soln solution = new SACH12_soln();
        while (t-- > 0) {
            //taking row and col inp
            int n = sc.nextInt();
            int ans = solution.function(n);
            System.out.println(ans);
        }


    }
}


class SACH12_soln {

    Map<Integer, Integer> dp;

    SACH12_soln() {
        dp = new HashMap<>();
    }

    int function(int x) {
        if (x < 2) {
            return x;
        }

        if (dp.containsKey(x)) {
            return dp.get(x);
        }

        int set_bits = 0;
        int copy_x = x;
        while (x > 0) {
            set_bits += x % 2;
            x = x >> 1;
        }

        dp.put(copy_x, set_bits);

        return set_bits;

    }

}
