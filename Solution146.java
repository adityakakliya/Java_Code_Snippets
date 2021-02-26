class LRUCache {

    Map<Integer, Integer> map;
    Deque<Integer> queue;
    int size;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        queue = new LinkedList<>();
        size = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            queue.remove(key);
            queue.addFirst(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key) && queue.size() == size) {
            map.remove(queue.removeLast());
        }
        queue.remove(key);
        queue.addFirst(key);
        map.put(key, value);
        //System.out.println("putting " + key + " " + value);
        //  for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        // System.out.print(entry.getKey() + "-" + entry.getValue());
        //    System.out.println();
        // }
        // System.out.println();
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */