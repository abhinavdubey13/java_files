package personal.models;


public class TrieNode {
    public TrieNode[] children;
    public int end_of_word;

    public TrieNode() {
        this.children = new TrieNode[26];
    }
}