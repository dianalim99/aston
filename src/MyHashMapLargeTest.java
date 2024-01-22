import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapLargeTest {
    private MyHashMap<Integer, String> map;

    @Test
    public void largeData() {
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
    @Test
    public void largeDatainitialCapacity() {
        map = new MyHashMap<>(3);
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