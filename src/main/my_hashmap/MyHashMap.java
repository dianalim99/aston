package main.my_hashmap;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Этот класс представляет собой реализацию HashMap, которая позволяет хранить пары ключ-значение
 * и обеспечивает быстрый доступ к значениям.
 */
public class MyHashMap<K, V> {
    /**
     * Значение емкости по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 16;
    /**
     * Значение предельного значения по умолчанию
     */
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    /**
     * Динамический массив для хранения корзин (связанных списков) пар ключ-значение.
     */
    private ArrayList<LinkedList<Node<K, V>>> buckets;
    /**
     * Размер HashMap (количество пар ключ-значение).
     */
    private int size;

    /**
     * Создает новый объект HashMap с начальной емкостью по умолчанию (16).
     * Он инициализирует внутренний массив (bucket) размером 16 и все его элементы устанавливаются в null.
     * Также инициализируется переменная size (размер) значением 0.
     */
    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Создает пустой HashMap с указанной начальной емкостью.
     * @param initialCapacity начальная емкость HashMap
     */
    public MyHashMap(int initialCapacity) {
        buckets = new ArrayList<>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }
    /**
     * Выводит на экран элементы HashMap в виде "ключ=значение"
     */
    public void print(){
        for (LinkedList<Node<K, V>> bucket : buckets) {
            for (Node<K, V> node : bucket) {
                System.out.print(node.toString());
            }
        }
    }
    /**
     * Связывает указанное значение с указанным ключом в этом HashMap.
     * Если карта ранее содержала значение для ключа, старое значение заменяется.
     * @param key   ключ, с которым связывается указанное значение
     * @param value значение, которое будет связано с указанным ключом
     */
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
    /**
     * Возвращает значение, сопоставленное с указанным ключом, или null, если в этой карте отсутствует значение для ключа.
     * @param key ключ, для которого должно быть возвращено сопоставленное значение
     * @return значение, сопоставленное с указанным ключом, или null, если для ключа нет значения
     */
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets.get(bucketIndex);
        for (Node<K, V> node : bucket) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        return null;
    }
    /**
     * Удаляет значение для указанного ключа из этой карты, если оно существует.
     * @param key ключ, для которого нужно удалить значение
     */
    public void remove(K key) {
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
    /**
     * Проверяет наличие значения, связанного с указанным ключом, HashMap
     * @param key ключ, для которого нужно проверить наличие значения
     * @return true, если значение существует, иначе false
     */
    public boolean containsKey(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets.get(bucketIndex);
        for (Node<K, V> node : bucket) {
            if (node.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверяет наличие указанного значения в HashMap
     * @param value значение, для которого нужно проверить наличие
     * @return true, если значение существует, иначе false
     */
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
    /**
     * Проверяет, является ли HashMap пустым (не содержит пар ключ-значение).
     * @return true, если HashMap пустой, в противном случае false
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Возвращает количество пар ключ-значение в HashMap.
     * @return количество пар ключ-значение в HashMap
     */
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
    /**
     * Внутренний класс, представляющий узел HashMap.
     */
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

        public final String toString() { return key + "=" + value; }
    }
}