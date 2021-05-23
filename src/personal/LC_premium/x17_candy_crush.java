package personal.LC_premium;

import java.util.*;

/**
 * leetcode id : 723
 *
 *
 *
 * This question is about implementing a basic elimination algorithm for Candy Crush.
 *
 * Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:
 *
 * 1. If 3 or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
 * 2. After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
 * 3. After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
 * 4. If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
 *
 *
 * You need to perform the above rules until the board becomes stable, then return the current board.
 *
 *
 */


/**
 *
 * for each iteration
 *
 * 1. find all cell to crush (if none found , we have stable state , return)
 * 2. crush all cells
 * 3. perform gravity logic
 *
 */

public class x17_candy_crush {

    public static void main(String[] args) {


    }
}


class Solution_17 {

    List<int[]> cells_to_crush;
    int ROW;
    int COL;

    int[][] function(int[][] arr) {

        cells_to_crush = new LinkedList<>();
        ROW = arr.length;
        COL = arr[0].length;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (arr[i][j] != 0) {
                    add_to_crush_cell(i, j, arr[i][j], arr);
                }
            }
        }

        if (cells_to_crush.size() == 0) {
            return arr;
        }

        crush_all_cells(arr);
        reset_board(arr);

        return function(arr);
    }


    void add_to_crush_cell(int i, int j, int val, int[][] arr) {
        //vertical checking
        if (i + 2 < ROW && arr[i + 1][j] == val && arr[i + 2][j] == val) {
            cells_to_crush.add(new int[]{i, j});
            cells_to_crush.add(new int[]{i + 1, j});
            cells_to_crush.add(new int[]{i + 2, j});

        }

        //horizontal checking
        if (j + 2 < COL && arr[i][j + 1] == val && arr[i][j + 2] == val) {
            cells_to_crush.add(new int[]{i, j});
            cells_to_crush.add(new int[]{i, j + 1});
            cells_to_crush.add(new int[]{i, j + 2});
        }
    }

    void crush_all_cells(int[][] arr) {
        for (int[] a : cells_to_crush) {
            arr[a[0]][a[1]] = 0;
        }
    }


    void reset_board(int[][] arr) {
        //for each column
        for (int col = 0; col < COL; col++) {

            //top idx will be used as iterator
            //bottom idx point to last non-zero value

            int top_idx = ROW - 1;
            int bottom_idx = ROW;

            while (top_idx >= 0) {
                //put non-zero values in the bottom
                if (arr[top_idx][col] != 0) {
                    bottom_idx--;
                    arr[bottom_idx][col] = arr[top_idx][col];
                }
                top_idx--;
            }


            //bottom idx point to last non-zero value
            bottom_idx--;

            while (bottom_idx >= 0) {
                arr[bottom_idx][col] = 0;
                bottom_idx--;
            }

        }
    }

}
