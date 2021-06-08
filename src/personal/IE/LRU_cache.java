package personal.IE;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;


public class LRU_cache {

    public static void main(String[] args) {
        LRU cache = new LRU(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4)); //4
        System.out.println(cache.get(3)); //3
        System.out.println(cache.get(2)); //2
        System.out.println(cache.get(1)); //-1
        cache.put(5, 5);
        System.out.println(cache.get(1));//-1
        System.out.println(cache.get(2));//2
        System.out.println(cache.get(3));//3
        System.out.println(cache.get(4));//-1
        System.out.println(cache.get(5));//5
    }
}


class DLinkedNode {
    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;

    DLinkedNode() {
    }

    DLinkedNode(int k, int v) {
        this.key = k;
        this.value = v;
    }

}

class LRU {

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode FRONT, REAR;


    private void addAtLast(DLinkedNode n) {

        //set pointers for new node
        n.prev = REAR.prev;
        n.next = REAR;

        //2nd last node's next will point to n
        REAR.prev.next = n;

        //last node REAR is dummy node : point it to n
        REAR.prev = n;
    }

    private void removeGivenNode(DLinkedNode node) {
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }


    private DLinkedNode removeHead() {
        DLinkedNode res = FRONT.next;
        removeGivenNode(res);
        return res;
    }

    private void moveToRear(DLinkedNode node) {
        removeGivenNode(node);
        addAtLast(node);
    }


    public LRU(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        //DUMMY NODES
        FRONT = new DLinkedNode();
        REAR = new DLinkedNode();

        FRONT.next = REAR;
        REAR.prev = FRONT;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;

        // move the accessed node to the head;
        moveToRear(node);

        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode already = cache.get(key);


        if (already != null) {
            already.value = value;
            moveToRear(cache.get(key));
            return;
        }

        DLinkedNode newNode1 = new DLinkedNode(key, value);
        size++;

        if (size > capacity) {
            size--;
            DLinkedNode first = removeHead();
            cache.remove(first.key);
        }

        addAtLast(newNode1);
    }
}