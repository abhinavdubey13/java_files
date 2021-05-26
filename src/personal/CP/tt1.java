package personal.CP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 */

/**
 *
 * cache the solution using map
 *
 */




public class tt1 {

    public static void main(String[] args) {

        Reader sc = new Reader();
        int t = sc.nextInt();
        tt1_soln solution = new tt1_soln();
        while (t-- > 0) {
            //taking row and col inp
            int days = sc.nextInt();
            int per_day_dist = sc.nextInt();
            int p10 = sc.nextInt();
            int p21 = sc.nextInt();
            int p42 = sc.nextInt();

            int ans = solution.function(days, per_day_dist, p10, p21, p42);
            System.out.println(ans);
        }


    }
}


class tt1_soln {


    int function(int D, int d, int p1, int p2, int p3) {

        int total = D * d;

        if (total < 10) {
            return 0;
        }else if(total >= 10 && total < 21){
            return p1;
        }else if(total >= 21 && total < 42){
            return p2;
        }
        return p3;

    }


}
