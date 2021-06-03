package personal.IE;

import java.util.*;

public class parenthesis {

    public static void main(String[] args) {
        int n = 3;

        Set<String> set = new Solution_123().gen(n);

        for (String s : set) {
            System.out.println(s);
        }
    }
}


class Solution_123 {


    Set<String> ans;

    Set<String> gen(int n) {
        ans = new HashSet<>();
        dfs(0, 0, n, "");
        return ans;
    }

    void dfs(int opn, int cls, int n, String curr) {

        if (opn > n || cls > n) {
            return;
        }

        if (opn == n && cls == n) {
            ans.add(curr);
            return;
        }


        String s1 = curr + "(";
        dfs(opn + 1, cls, n, s1);

        if (opn > cls) {
            String s2 = curr + ")";
            dfs(opn, cls + 1, n, s2);
        }


    }
}