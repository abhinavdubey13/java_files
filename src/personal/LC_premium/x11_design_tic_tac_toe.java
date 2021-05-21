package personal.LC_premium;

/**
 * leetcode id : 348
 *
 *
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 *
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 *
 *
 */


/**
 *
 *
 * You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.
 *
 * TC = o(1) for move()
 * SC = O(n)
 *
 */

public class x11_design_tic_tac_toe {

    public static void main(String[] args) {


    }
}


class TicTacToe {

    int[] ROW;
    int[] COL;
    int DIAGONAL;
    int ANTI_DIAGONAL;


    public TicTacToe(int n) {
        ROW = new int[n];
        COL = new int[n];
        DIAGONAL = ANTI_DIAGONAL = 0;
    }


    public int move(int r, int c, int player) {
        int to_add = (player == 1) ? 1 : -1;
        int n = ROW.length;

        ROW[r] += to_add;
        COL[c] += to_add;

        //diagonal
        if (r == c) {
            DIAGONAL += to_add;
        }

        //anti-diagonal
        if (c == n - r - 1) {
            ANTI_DIAGONAL += to_add;
        }


        //check if player won
        if (Math.abs(ROW[r]) == n || Math.abs(COL[c]) == n || Math.abs(DIAGONAL) == n || Math.abs(ANTI_DIAGONAL) == n) {
            return player;
        }

        //if no one won
        return 0;


    }

}
