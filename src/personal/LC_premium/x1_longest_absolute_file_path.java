package personal.LC_premium;

import java.util.*;

/**
 *
 * leetcode : 388
 *
 * If we were to write this representation in code, it will look like this:
 *
 * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext".
 *
 * Note that the '\n' and '\t' are the new-line and tab characters.
 *
 * so directory will be
 *
 * dir
 * ⟶ subdir1
 * ⟶ ⟶ file1.ext
 * ⟶ ⟶ subsubdir1
 * ⟶ subdir2
 * ⟶ ⟶ subsubdir2
 * ⟶ ⟶ ⟶ file2.ext
 *
 *
 *
 *
 * Every file and directory has a unique absolute path in the file system,
 * which is the order of directories that must be opened to reach the file/directory itself,
 * all concatenated by '/'s.
 * Using the above example, the absolute path to file2.ext is "dir/subdir2/subsubdir2/file2.ext".
 * Each directory name consists of letters, digits, and/or spaces.
 * Each file name is of the form name.extension, where name and extension consist of letters, digits, and/or spaces.
 *
 * Given a string input representing the file system in the explained format,
 * return the length of the longest absolute path to a file in the abstracted file system.
 * If there is no file in the system, return 0.
 *
 *
 *
 */


/**
 *
 *
 * used stack here to maintain the nesting
 *
 *
 */

public class x1_longest_absolute_file_path {

    public static void main(String[] args) {
        String input = "x\n\ty\n\t\tf1.txt\n\tf2.ext";
        int ans = new Solution_1().function(input);
        System.out.println(ans);

    }
}


class Solution_1 {

    int function(String input) {
        String[] tokens = input.split("\n");
        Stack<Integer> stk = new Stack<>();
        int cur_path_len_from_root = 0;
        int ans = 0;

        for (String s : tokens) {
            int depth = get_level_from_root(s);

            //if we go in previous folder
            // if current directory/file depth is lower that the top directory/file on the stack, pop from stack
            while (stk.size() > depth) {
                cur_path_len_from_root -= stk.pop();
            }


            // +1 here because a "/" needs to be counted following each diretory
            int len_contribution_of_s = s.replaceAll("\t", "").length() + 1;
            cur_path_len_from_root += len_contribution_of_s;

            //if file
            if (s.contains(".")) {
                ans = Math.max(ans, cur_path_len_from_root - 1);
            }

            stk.push(len_contribution_of_s);
        }
        return ans;
    }

    int get_level_from_root(String s) {
        String without_tabs = s.replaceAll("\t", "");
        int diff = s.length() - without_tabs.length();
        return diff;
    }
}