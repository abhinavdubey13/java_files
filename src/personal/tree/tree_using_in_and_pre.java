package personal.tree;


/**
 * given in-order and pre-order of a binary tree
 * construct the tree
 */

/**
 * Pick an element from Preorder. Increment a Preorder Index Variable (preIndex in below code) to pick next element in next recursive call
 *
 * Create a new tree node tNode with the data as picked element.
 *
 * Find the picked element’s index in Inorder. Let the index be inIndex.
 *
 * Call buildTree for elements before inIndex and make the built tree as left subtree of tNode.
 *
 * Call buildTree for elements after inIndex and make the built tree as right subtree of tNode.
 *
 * return tNode.
 *
 * =================================
 * let total nodes in tree = n
 *
 * TC = O(n)
 * SC = O(n)
 */

class char_node {
    char data;
    char_node left, right;

    char_node(char dta) {
        this.data = dta;
        this.left = this.right = null;
    }
}

class tree_using_in_and_pre {

    public static void main(String[] args) {
        char[] in_order = {'D', 'B', 'E', 'A', 'F', 'C'};
        char[] pre_order = {'A', 'B', 'D', 'E', 'C', 'F'};

        char_node Root = new Solution_1().construct_tree(in_order, pre_order);
        Helper.print_in_order(Root);
    }

}


class Solution_1 {

    int PRE_IDX = 0;

    char_node construct_tree(char[] in, char[] pre) {
        this.PRE_IDX = 0;
        return this.construct_tree_util(in, pre, 0, in.length - 1);

    }


    char_node construct_tree_util(char[] in, char[] pre, int in_start, int in_end) {

        if (in_start > in_end) {
            return null;
        }

        if (in_start == in_end) {
            return new char_node(pre[PRE_IDX++]);
        }


        char data = pre[PRE_IDX++];
        char_node root = new char_node(data);
        int index_in_inorder = find_in_inorder(in, data);


        root.left = construct_tree_util(in, pre, in_start, index_in_inorder - 1);
        root.right = construct_tree_util(in, pre, index_in_inorder + 1, in_end);
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


class Helper {

    static void print_in_order(char_node curNode) {
        if (curNode != null) {
            print_in_order(curNode.left);
            System.out.print(curNode.data + " ");
            print_in_order(curNode.right);
        }
    }


}