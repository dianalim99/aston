package main.my_second_hashmap;

/**
 * Реализация простго HashMap
 * @param <K> тип ключей
 * @param <V> тип значений
 */
public class MySecondHashMap<K, V> {
    /**
     * Размер по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 16;
    /**
     * Коэффициент заполнения
     */
    private static final double LOAD_FACTOR = 0.75;
    /**
     * Массив корзин HashMap
     */
    private Node<K, V>[] buckets;
    /**
     * Количество элементов в HashMap
     */

    private int size;
    /**
     * Создает новый объект main.my_second_hashmap.MySecondHashMap с размером по умолчанию.
     */

    public MySecondHashMap() {
        this(DEFAULT_CAPACITY);
    }
    /**
     * Создает новый объект main.my_second_hashmap.MySecondHashMap с заданным начальным размером.
     * @param capacity начальный размер хэш-карты
     */
    public MySecondHashMap(int capacity) {
        buckets = new Node[capacity];
        size = 0;
    }
    /**
     * Возвращает количество элементов в HashMap
     * @return количество элементов в HashMap
     */
    public int size() {
        return size;
    }
    /**
     * Проверяет, пуст ли HashMap
     * @return true, если HashMap пуст, иначе false
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Добавляет элемент с указанным ключом и значением в HashMap
     * @param key   ключ элемента
     * @param value значение элемента
     */
    public void put(K key, V value) {
        int bucketIndex = getIndex(key);
        Node<K, V> node = new Node<>(key, value);

        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = node;
            size++;
        } else {
            Node<K, V> current = buckets[bucketIndex];
            Node<K, V> previous = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                previous = current;
                current = current.next;
            }

            previous.next = node;
            size++;
        }

        if ((double) size / buckets.length > LOAD_FACTOR) {
            resizeBuckets(buckets.length * 2);
        }
    }
    /**
     * Возвращает значение, сопоставленное с указанным ключом, или null, если в этой карте отсутствует значение для ключа.
     * @param key ключ, для которого должно быть возвращено сопоставленное значение
     * @return значение, сопоставленное с указанным ключом, или null, если для ключа нет значения
     */
    public V get(K key) {
        int bucketIndex = getIndex(key);

        Node<K, V> current = buckets[bucketIndex];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }
    /**
     * Проверяет, содержится ли указанный ключ в HashMap
     * @param key значение для поиска
     * @return true, если значение содержится в HashMap, иначе false
     */
    public boolean containsKey(K key) {
        int bucketIndex = getIndex(key);
        Node<K, V> current = buckets[bucketIndex];

        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }
    /**
     * Проверяет, содержится ли указанное значение в HashMap
     * @param value значение для поиска
     * @return true, если значение содержится в HashMap, иначе false
     */
    public boolean containsValue(V value) {
        for (int i = 0; i < buckets.length; i++) {
            Node<K, V> current = buckets[i];
            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }
    /**
     * Удаляет значение для указанного ключа из этой карты, если оно существует.
     * @param key ключ, для которого нужно удалить значение
     */
    public void remove(K key) {
        int bucketIndex = getIndex(key);

        if (buckets[bucketIndex] == null) {
            return;
        }

        Node<K, V> current = buckets[bucketIndex];
        Node<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    buckets[bucketIndex] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
    }
    /**
     * Возвращает индекс корзины (bucket) в массиве buckets по заданному ключу.
     * @param key ключ, для которого нужно получить индекс
     * @return индекс корзины для хранения элемента
     */
    private int getIndex(K key) {
        return hash(key) % buckets.length;
    }
    /**
     * Вычисляет хэш-код ключа с использованием стандартного алгоритма hashCode().
     * @param key ключ, для которого вычисляется хэш-код
     * @return хэш-код ключа
     */
    static final int hash(Object key) {
        int h;
        if (key == null){
            return  0;
        }
        else {
            return (h = key.hashCode()) ^ (h >>> 16);
        }
    }
    /**
     * Изменяет размер хэш-карты (buckets) путем создания нового массива с большей
     * емкостью и перехэширования всех элементов.
     * @param newCapacity новая емкость хэш-карты
     */
    private void resizeBuckets(int newCapacity) {
        Node<K, V>[] newBuckets = new Node[newCapacity];

        for (Node<K, V> node : buckets) {
            Node<K, V> current = node;
            while (current != null) {
                Node<K, V> next = current.next;
                int bucketIndex = getIndex(current.key);
                current.next = newBuckets[bucketIndex];
                newBuckets[bucketIndex] = current;
                current = next;
            }
        }

        buckets = newBuckets;
    }
    /**
     * Внутренний класс, представляющий элемент HashMap
     * @param <K> тип ключа
     * @param <V> тип значения
     */
    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}