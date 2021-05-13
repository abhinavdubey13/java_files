package personal.CP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * codechef id :
 *
 *
 *
 *
 */

/**
 *
 *
 */


class a1_template_reader {
    BufferedReader br;
    StringTokenizer st;

    public a1_template_reader() {
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


public class a1_template {

    public static void main(String[] args) {

        a1_template_reader sc = new a1_template_reader();
        int t = sc.nextInt();
        a1_template_soln solution = new a1_template_soln();

        while (t-- > 0) {
            //taking row and col inp
            int n = sc.nextInt();
            int ans = solution.function(n);
            System.out.println(ans);
        }


    }
}


class a1_template_soln {

    int function(int n) {

        return n;
    }

}
