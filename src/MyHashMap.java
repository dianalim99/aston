import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private ArrayList<LinkedList<Node<K, V>>> buckets;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int initialCapacity) {
        buckets = new ArrayList<>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets.get(bucketIndex);
        for (Node<K, V> node : bucket) {
            if (node.getKey().equals(key)) {
                node.setValue(value);
                return;
            }
        }
        bucket.add(new Node<>(key, value));
        size++;
        if (getSize() >= buckets.size() * DEFAULT_LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        int bucketIndex = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets.get(bucketIndex);
        for (Node<K, V> node : bucket) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        return null;
    }

    public void remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        int bucketIndex = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets.get(bucketIndex);
        for (Node<K, V> node : bucket) {
            if (node.getKey().equals(key)) {
                bucket.remove(node);
                size--;
                return;
            }
        }
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        int bucketIndex = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets.get(bucketIndex);
        for (Node<K, V> node : bucket) {
            if (node.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }


    public boolean containsValue(V value) {
        for (int i = 0; i < buckets.size(); i++) {
            LinkedList<Node<K, V>> bucket = buckets.get(i);
            for (Node<K, V> node : bucket) {
                if (value == null ? node.getValue() == null : value.equals(node.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    private int getBucketIndex(K key) {
        return hash(key) % buckets.size();
    }

    static final int hash(Object key) {
        int h;
        if (key == null){
            return  0;
        }
        else {
            return (h = key.hashCode()) ^ (h >>> 16);
        }
    }
    private void resize() {
        ArrayList<LinkedList<Node<K, V>>> newBuckets = new ArrayList<>(buckets.size() * 2);
        for (int i = 0; i < buckets.size() * 2; i++) {
            newBuckets.add(new LinkedList<>());
        }
        for (LinkedList<Node<K, V>> bucket : buckets) {
            for (Node<K, V> node : bucket) {
                int newBucketIndex = hash(node.getKey()) % newBuckets.size();
                newBuckets.get(newBucketIndex).add(node);
            }
        }
        buckets = newBuckets;
    }

    private static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}