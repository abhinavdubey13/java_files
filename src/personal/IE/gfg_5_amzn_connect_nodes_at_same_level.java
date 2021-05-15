package personal.IE;

/**
 *
 * Given a binary tree, connect the nodes that are at same level.
 *
 * You'll be given an addition nextRight pointer for the same.
 *
 * Initially, all the nextRight pointers point to garbage values.
 *
 * Your function should set these pointers to point next right for each
 *
 *
 *
 *
 */

/**
 *
 *
 * using recursion
 *
 * first finish connecting for RST , then proceed with LST
 *
 *
 *
 *
 */


class Node {
    int data;
    Node left;
    Node right;
    Node nextRight;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
        nextRight = null;
    }
}

public class gfg_5_amzn_connect_nodes_at_same_level {
    public static void main(String[] args) {

        Node root = new Node(10);

        root.left = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(1);

        root.right = new Node(5);
        root.right.right = new Node(2);

        new Solution_5().connect(root);

        System.out.println("done");
    }
}


class Solution_5 {

    public void connect(Node root) {
        if (root == null) {
            return;
        }

        connect_util(root, root.right, false);
        connect_util(root, root.left, true);
    }

    void connect_util(Node parent, Node child, boolean is_left_child) {
        if (parent == null || child == null) {
            return;
        }

        //if child is left-child , then we can check if parent has right child also
        //else we need parent.nextRight chain to find next right
        child.nextRight = (is_left_child && parent.right != null) ? parent.right : get_nr(parent);



        //recursive calls
        connect_util(child, child.right, false);
        connect_util(child, child.left, true);
    }



    //get next right
    Node get_nr(Node curr) {
        if (curr == null) {
            return null;
        } else if (curr.nextRight != null) {
            if (curr.nextRight.left != null) return curr.nextRight.left;
            else if (curr.nextRight.right != null) return curr.nextRight.right;
            else return get_nr(curr.nextRight);
        }

        return null;
    }

}
