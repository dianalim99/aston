package test.my_second_hashmap;

import main.my_hashmap.MyHashMap;
import main.my_second_hashmap.MySecondHashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class MySecondHashMapTest {

    @Test
    public void testPutAndGet() {
        MySecondHashMap<String, Integer> map = new MySecondHashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(Integer.valueOf(1), map.get("one"));
        assertEquals(Integer.valueOf(2), map.get("two"));
        assertEquals(Integer.valueOf(3), map.get("three"));
    }

    @Test
    public void testGet() {
        MySecondHashMap<String, Integer> map = new MySecondHashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        assertEquals(Integer.valueOf(1), map.get("one"));
    }

    @Test
    public void testGetNull() {
        MySecondHashMap<String, Integer> map = new MySecondHashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        Integer a = map.get("ten");
        assertNull(a);
    }
    @Test
    public void testPutAndGetDuplicateKey() {
        MySecondHashMap<String, Integer> map = new MySecondHashMap<>();

        map.put("one", 1);
        map.put("one", 2);

        assertEquals(Integer.valueOf(2), map.get("one"));
    }

        @Test
    public void testSize() {
        MySecondHashMap<String, Integer> map = new MySecondHashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(3, map.size());
    }

    @Test
    public void testIsEmpty() {
        MySecondHashMap<String, Integer> map = new MySecondHashMap<>();

        assertTrue(map.isEmpty());

        map.put("one", 1);

        assertFalse(map.isEmpty());
    }
    @Test
    public void testContainsKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertTrue(map.containsKey("one"));
        assertTrue(map.containsKey("two"));
        assertTrue(map.containsKey("three"));
        assertFalse(map.containsKey("four"));
    }

    @Test
    public void testContainsValue() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertTrue(map.containsValue(1));
        assertTrue(map.containsValue(2));
        assertTrue(map.containsValue(3));
        assertFalse(map.containsValue(4));
    }

    @Test
    public void largeData() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map = new MyHashMap<>();
        int dataSize = 1000000;

        for (int i = 0; i < dataSize; i++) {
            map.put(i, "Value " + i);
        }

        assertEquals(dataSize, map.getSize());
        for (int i = 0; i < dataSize; i++) {
            assertTrue(map.containsKey(i));
            assertEquals("Value " + i, map.get(i));
        }

        for (int i = 0; i < dataSize; i += 2) {
            map.remove(i);
        }

        assertEquals(dataSize / 2, map.getSize());
        for (int i = 0; i < dataSize; i++) {
            if (i % 2 == 0) {
                assertFalse(map.containsKey(i));
                assertNull(map.get(i));
            } else {
                assertTrue(map.containsKey(i));
                assertEquals("Value " + i, map.get(i));
            }
        }
    }
}