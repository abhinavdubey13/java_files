package personal.tree;


/**
 *
 * given pre and post order traversals , construct uniq full tree
 *
 * https://www.geeksforgeeks.org/full-and-complete-binary-tree-from-given-preorder-and-postorder-traversals/
 *
 *
 */

/**
 *
 *
 * https://www.techiedelight.com/construct-full-binary-tree-from-preorder-postorder-sequence/
 *
 *
 *
 *
 */

class char_node_3 {
    int data;
    char_node_3 left, right;

    char_node_3(int dta) {
        this.data = dta;
        this.left = this.right = null;
    }
}

class tree_using_pre_and_post {

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 8, 9, 5, 3, 6, 7};
        int[] post = {8, 9, 4, 5, 2, 6, 7, 3, 1};

        char_node_3 root = new Solution_3().construct_tree(pre, post);
        Helper_3.print_in_order(root);
    }

}


class Solution_3 {


    int PRE_IDX;

    char_node_3 construct_tree(int[] pre, int[] post) {
        PRE_IDX = 0;
        return construct_tree_util(pre, post, 0, post.length - 1);

    }


    char_node_3 construct_tree_util(int[] pre, int[] post, int post_start, int post_end) {

        if (post_start > post_end) {
            return null;
        }

        int data = pre[PRE_IDX++];
        char_node_3 root = new char_node_3(data);


        if (PRE_IDX == pre.length) {
            return root;
        }


        //find the next node of the pre-order , inside the post[]
        int idx_in_post = find_in_array(post, pre[PRE_IDX]);

        if (post_start <= idx_in_post && idx_in_post + 1 < post_end) {
            root.left = construct_tree_util(pre, post, post_start, idx_in_post);
            root.right = construct_tree_util(pre, post, idx_in_post + 1, post_end);
        }


        return root;


    }


    int find_in_array(int[] arr, int x) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result = (arr[i] == x) ? i : result;
        }
        return result;
    }
}


class Helper_3 {

    static void print_in_order(char_node_3 curNode) {
        if (curNode != null) {
            print_in_order(curNode.left);
            System.out.print(curNode.data + " ");
            print_in_order(curNode.right);
        }
    }


}