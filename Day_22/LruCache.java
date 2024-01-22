// Problem Statement:
// Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

// Implement the LRUCache class:

// LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
// int get(int key) Return the value of the key if the key exists, otherwise return -1.
// void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
// The functions get and put must each run in O(1) average time complexity.

// Example 1:

// Input
// ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
// [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
// Output
// [null, null, null, 1, null, -1, null, -1, 3, 4]

// Explanation
// LRUCache lRUCache = new LRUCache(2);
// lRUCache.put(1, 1); // cache is {1=1}
// lRUCache.put(2, 2); // cache is {1=1, 2=2}
// lRUCache.get(1);    // return 1
// lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
// lRUCache.get(2);    // returns -1 (not found)
// lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
// lRUCache.get(1);    // return -1 (not found)
// lRUCache.get(3);    // return 3
// lRUCache.get(4);    // return 4
 
// Constraints:

// 1 <= capacity <= 3000
// 0 <= key <= 104
// 0 <= value <= 105
// At most 2 * 105 calls will be made to get and put.

// Solution:

/**
 * LRUCache class represents a Least Recently Used (LRU) cache.
 * It uses a LinkedHashMap to maintain the order of elements based on access.
 */
class LRUCache {
    // Map to store key-value pairs.
    Map<Integer, Integer> m;
    // Capacity of the cache.
    int c;

    /**
     * Initializes an LRUCache with the specified capacity.
     *
     * @param capacity The maximum number of elements the cache can hold.
     */
    public LRUCache(int capacity) {
        m = new LinkedHashMap<>();
        c = capacity;
    }

    /**
     * Retrieves the value associated with the given key in the cache.
     * If the key is found, it is moved to the most recently used position.
     *
     * @param key The key to look up in the cache.
     * @return    The value associated with the key, or -1 if not found.
     */
    public int get(int key) {
        if (m.containsKey(key)) {
            int value = m.get(key);
            m.remove(key);
            m.put(key, value);
            return value;
        } else {
            return -1;
        }
    }

    /**
     * Inserts a key-value pair into the cache. If the key already exists,
     * it updates the value and moves the key to the most recently used position.
     * If the cache is at full capacity, it evicts the least recently used item.
     *
     * @param key   The key to insert or update in the cache.
     * @param value The value associated with the key.
     */
    public void put(int key, int value) {
        if (m.containsKey(key)) {
            m.remove(key);
            m.put(key, value);
            return;
        }

        if (c > 0) {
            c--;
            m.put(key, value);
        } else {
            // Cache is at full capacity, evict the least recently used item.
            List<Integer> keys = new ArrayList<>(m.keySet());
            m.remove(keys.get(0));
            m.put(key, value);
        }
    }
}
