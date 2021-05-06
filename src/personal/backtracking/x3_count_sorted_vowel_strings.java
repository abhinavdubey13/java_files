package personal.backtracking;

/**
 * Leetcode id : 1641
 *
 *
 * Given an integer n, return the number of strings of length n that consist only of vowels
 * (a, e, i, o, u) and are lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i,
 * s[i] is the same as or comes before s[i+1] in the alphabet
 *
 *
 * ==========
 * example :
 * ==========
 *
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet
 *
 *
 */


/**
 * ===========
 * approach :
 * ===========
 *
 * back-tracking
 *
 * the solution is
 *
 * for(i=0->4)
 *      for(j=i->4)
 *          for(k=j->4)
 *                 .... n nested for loops
 *
 */

public class x3_count_sorted_vowel_strings {

    public static void main(String[] args) {

        int n = 33;
        int answer = new Solution3().function(n);
        System.out.println(answer);

    }
}

class Solution3 {

    int COUNT;
    int[] arr;

    int function(int n) {
        COUNT = 0;
        arr = new int[n];
//        Arrays.fill(arr, -1);
        dfs(0, 0, n);
        return COUNT;
    }

    void dfs(int curr, int idx, int n) {
        if (idx == n) {
            COUNT++;
        //print();
        } else {
            for (int i = curr; i <= 4; i++) {
                arr[idx] = i;
                dfs(i, idx + 1, n);
                arr[idx] = -1;
            }
        }
    }

    void print() {
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
    }

}
