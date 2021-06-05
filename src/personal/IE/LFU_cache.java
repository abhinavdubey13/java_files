package personal.IE;

import java.util.*;


public class LFU_cache {

    public static void main(String[] args) {
        Cache cache = new LFU(2);


        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); //1
        cache.put(3, 3);
        System.out.println(cache.get(2));//-1
        System.out.println(cache.get(3));//3
        cache.put(4, 4);
        System.out.println(cache.get(1));//-1
        System.out.println(cache.get(3));//3
        System.out.println(cache.get(4));//4
    }
}

interface Cache {
    public int get(int key);

    public void put(int key, int val);
}

class LFU implements Cache {


    private Map<Integer, Integer> key_value_map; //key and its value
    private Map<Integer, Integer> key_freq_map; //key and its frequency of usage
    private Map<Integer, LinkedHashSet<Integer>> freq_element_map_list; //frequency of usage and set of keys at that freq
    private int MIN_FREQ_OF_USAGE;

    private int CAPACITY;

    LFU(int capacity) {
        this.CAPACITY = capacity;
        key_value_map = new HashMap<>();
        key_freq_map = new HashMap<>();
        freq_element_map_list = new HashMap<>();
        freq_element_map_list.put(1, new LinkedHashSet<>());
    }


    public int get(int key) {

        if (!key_value_map.containsKey(key)) {
            return -1;
        }

        //1. calculate old and new freq of key
        // and update in freq-map
        int old_freq = key_freq_map.get(key);
        int new_freq = old_freq + 1;
        key_freq_map.put(key,new_freq);

        //2. remove key from old-freq list
        freq_element_map_list.get(old_freq).remove(key);

        //3. update MIN_FREQ
        if (old_freq == MIN_FREQ_OF_USAGE && freq_element_map_list.get(old_freq).size() == 0) {
            MIN_FREQ_OF_USAGE++;
        }


        //4. add key to new freq. list
        if (!freq_element_map_list.containsKey(new_freq)) {
            freq_element_map_list.put(new_freq, new LinkedHashSet<>());
        }
        freq_element_map_list.get(new_freq).add(key);

        //5. return the val
        return key_value_map.get(key);
    }

    public void put(int key, int val) {

        if (this.CAPACITY <= 0) {
            return;
        }

        //if value already exist in key : update with new value , and call get logic
        if (key_value_map.containsKey(key)) {
            key_value_map.put(key, val);
            this.get(key);
            return;
        }


        //if cache is full , evict 1 key
        if (key_freq_map.size() >= this.CAPACITY) {
            int key_to_evict = freq_element_map_list.get(this.MIN_FREQ_OF_USAGE).iterator().next();
            freq_element_map_list.get(this.MIN_FREQ_OF_USAGE).remove(key_to_evict);

            //remove from maps
            key_value_map.remove(key_to_evict);
            key_freq_map.remove(key_to_evict);
        }


        //put in key-val map
        key_value_map.put(key, val);

        //new key has initial freq of 0
        key_freq_map.put(key, 1);

        //when new key comes in cache , its freq of usage =1
        //so min_freq_usage will become 1
        this.MIN_FREQ_OF_USAGE = 1;

        //add key to 1 freq-key-map-list
        freq_element_map_list.get(1).add(key);

    }

}
