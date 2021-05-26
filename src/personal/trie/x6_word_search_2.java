package personal.trie;

import java.util.*;

/**
 * leetcode : 212
 *
 *
 * Given an m x n board of characters and a list of strings words,
 * return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 *
 * The same letter cell may not be used more than once in a word.
 *
 *
 * Input: board =
 * [
 *  ["o","a","a","n"],
 *  ["e","t","a","e"],
 *  ["i","h","k","r"],
 *  ["i","f","l","v"]
 * ],
 *
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 *
 */

/**
 * 1. insert all words in trie
 *
 * 2. from each cell on the board , begin dfs using trie
 * if trie node is null => we cannot continue
 *
 *
 *
 */

class TrieNode_6 {
    TrieNode_6[] next;
    String word_here;

    TrieNode_6() {
        next = new TrieNode_6[26];
        word_here = null;
    }

}


class x6_word_search_2 {
    public static void main(String[] args) {


    }
}


class Solution_6 {

    TrieNode_6 ROOT;
    int ROW;
    int COL;

    public List<String> function(char[][] board, String[] words) {
        ROOT = new TrieNode_6();
        ROW = board.length;
        COL = board[0].length;

        //add all words to trie
        for (String w : words) {
            add(w);
        }

        List<String> ans = new LinkedList<>();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                dfs(i, j, board, ans, ROOT);
            }
        }

        return ans;

    }


    void dfs(int x, int y, char[][] board, List<String> ans, TrieNode_6 node) {
        if (!is_safe(x, y)) {
            return;
        }

        char curr_ij = board[x][y];
        int idx = curr_ij - 'a';

        if (curr_ij == '#' || node.next[idx] == null) {
            return;
        }

        node = node.next[idx];
        if (node.word_here != null) {
            ans.add(node.word_here);
            node.word_here = null;
        }

        //mark as used
        board[x][y] = '#';

        dfs(x + 1, y, board, ans, node);
        dfs(x - 1, y, board, ans, node);
        dfs(x, y + 1, board, ans, node);
        dfs(x, y - 1, board, ans, node);

        //restore on back tracking
        board[x][y] = curr_ij;
    }


    boolean is_safe(int x, int y) {
        return x >= 0 && x < ROW && y >= 0 && y < COL;
    }


    void add(String s) {
        if (s == null || s.length() == 0) return;
        TrieNode_6 ptr = ROOT;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (ptr.next[idx] == null) {
                ptr.next[idx] = new TrieNode_6();
            }
            ptr = ptr.next[idx];
        }
        ptr.word_here = s;
    }


}