package personal.CP;

import java.util.*;

/**
 * '#','*','#','#','#','#','#','#','#','#','#','#','*','*','*','#','#','*','#','#','#','#','*','#','*','#','#','#','#','#','#','*','#','#','*','#','#','#','.','#','*','#','*','*','*','#','#','#','#','*'
 */

public class test3 {

    public static void main(String[] args) {

        soln3 s = new soln3();

//        char[][] arr = {{'#', '.', '*', '.'}, {'#', '#', '*', '.'}};
//        char[][] arr = {{'#', '#', '*', '.', '*', '.'}, {'#', '#', '#', '*', '.', '.'}, {'#', '#', '#', '.', '#', '.'}};

        char[][] arr = {{'#', '*', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '*', '*', '*', '#', '#', '*', '#', '#', '#', '#', '*', '#', '*', '#', '#', '#', '#', '#', '#', '*', '#', '#', '*', '#', '#', '#', '.', '#', '*', '#', '*', '*', '*', '#', '#', '#', '#', '*'}};


        char[][] ans = s.function(arr);

        String[] x = {"*", "*", "#", "*", "*", "*", "#", "*", "#", "#", ".", ".", "#", "#", "#", ".", ".", "#", ".", "#", ".", ".", "#", "#", "#", "#", "#", ".", ".", "#", "#", ".", ".", "#", "#", "#", "#", ".", "#", "#", ".", ".", "#", "#", "#", ".", "#", "#", "#", "#"};


        int k = 0;
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + " " + x[k++]);

            }
            System.out.println();
        }


    }
}


class soln3 {

    char STONE = '#';
    char EMPTY = '.';
    char NOT_MOVE = '*';

    char[][] function(char[][] arr) {

        int input_row = arr.length;
        int input_col = arr[0].length;

        int output_row = input_col;
        int output_col = input_row;

        char[][] ans = new char[output_row][output_col];

        int col = output_col - 1;
        for (char[] row : arr) {
            for (int i = 0; i < input_col; i++) {
                ans[i][col] = row[i];
            }
            col--;
        }


        for (int c = 0; c < output_col; c++) {
            List<Integer> not_move_idx = new LinkedList<>();

            for (int r = 0; r < output_row; r++) {
                if (ans[r][c] == NOT_MOVE) {
                    not_move_idx.add(r);
                }
            }

            if (not_move_idx.size() == 0) {
                helper(c, 0, output_row, ans);
            } else {
                int start = 0;
                for (Integer end : not_move_idx) {
                    helper(c, start, end, ans);
                    start = end + 1;

                }
            }
        }


        return ans;
    }


    void helper(int col_id, int start, int end, char[][] ans) {

        if (start == end || start + 1 == end) {
            return;
        }

        int count_stone = 0;
        int count_empty = 0;
        for (int i = start; i < end; i++) {
            if (ans[i][col_id] == STONE) count_stone++;
            else if (ans[i][col_id] == EMPTY) count_empty++;
        }

        int r = end - 1;
        while (count_stone > 0) {
            ans[r][col_id] = STONE;
            count_stone--;
            r--;
        }

        while (count_empty > 0) {
            ans[r][col_id] = EMPTY;
            count_empty--;
            r--;
        }
    }

}
