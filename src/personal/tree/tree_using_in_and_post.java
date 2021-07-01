package personal.tree;


/**
 * given in-order and post -order of a binary tree
 * construct the tree
 */

/**
 * similar to create using in and pre
 *
 * but here we goto right child first and begin POST_IDX from last : n-1
 * =================================
 * let total nodes in tree = n
 *
 * TC = O(n)
 * SC = O(n)
 *
 *
 */

class char_node_2 {
    char data;
    char_node_2 left, right;

    char_node_2(char dta) {
        this.data = dta;
        this.left = this.right = null;
    }
}

class tree_using_in_and_post {

    public static void main(String[] args) {
        char[] in_order = {'D', 'B', 'E', 'A', 'F', 'C'};
        char[] post_order = {'D', 'E', 'B', 'F', 'C', 'A'};

        char_node_2 Root = new Solution_2().construct_tree(in_order, post_order);
        Helper_2.print_in_order(Root);
    }

}


class Solution_2 {

    int POST_IDX;

    char_node_2 construct_tree(char[] in, char[] pre) {
        this.POST_IDX = in.length - 1;
        return this.construct_tree_util(in, pre, 0, in.length - 1);

    }

    char_node_2 construct_tree_util(char[] in, char[] post, int in_start, int in_end) {

        if (in_start > in_end) {
            return null;
        }

        if (in_start == in_end) {
            return new char_node_2(post[POST_IDX--]);
        }


        char data = post[POST_IDX--];
        char_node_2 root = new char_node_2(data);
        int index_in_inorder = find_in_inorder(in, data);


        root.right = construct_tree_util(in, post, index_in_inorder + 1, in_end);
        root.left = construct_tree_util(in, post, in_start, index_in_inorder - 1);
        return root;
    }


    int find_in_inorder(char[] arr, int x) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result = (arr[i] == x) ? i : result;
        }
        return result;
    }
}


class Helper_2 {

    static void print_in_order(char_node_2 curNode) {
        if (curNode != null) {
            print_in_order(curNode.left);
            System.out.print(curNode.data + " ");
            print_in_order(curNode.right);
        }
    }


}