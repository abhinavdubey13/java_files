package personal.CP;

import java.util.*;

public class test1 {

    public static void main(String[] args) {

        soln1 s = new soln1();

        String str = "sentence4 a3 is2 This1";

        String ans = s.function(str);

        System.out.println(ans);


    }
}


class Helper {
    String s;
    int idx;

    Helper(String s, int i) {
        this.s = s;
        this.idx = i;
    }
}

class soln1 {

    String function(String str) {

        String[] tokens = str.split("\\s+");

        List<Helper> arr = new LinkedList<>();

        for (String x : tokens) {
            int n = x.length();
            int idx = x.charAt(n - 1) - '0';
            String y = x.substring(0, n - 1);
            arr.add(new Helper(y, idx));
        }


        Collections.sort(arr, new Comparator<Helper>() {
            @Override
            public int compare(Helper o1, Helper o2) {
                return o1.idx - o2.idx;
            }
        });

        String ans = "";

        for (int i = 0; i < arr.size(); i++) {
            if (i == arr.size() - 1) {
                ans += arr.get(i).s;
            } else {
                ans += arr.get(i).s + " ";
            }
        }

        return ans;


    }

}
