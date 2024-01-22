import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class MyHashMapTest {
    private MyHashMap<Integer, String> mapA;
    private HashMap<Integer, String> mapE;

    @Before
    public void setUp1() {
        mapA =new MyHashMap<>();
        mapA.put(24, "Diana");
        mapA.put(25, "Maks");
        mapA.put(23, "Dasha");
        mapA.put(21, "Lesha");
    }
    @Before
    public void setUp2() {
        mapE =new HashMap<>();
        mapE.put(24, "Diana");
        mapE.put(25, "Maks");
        mapE.put(23, "Dasha");
        mapE.put(21, "Lesha");
    }
    @Test
    public void get1() {
        String e = mapE.get(24);
        String a = mapA.get(24);
        assertEquals(e, a);
    }

    @Test
    public void get2() {
        String a = mapA.get(27);
        assertNull(a);
    }

    @Test
    public void remove() {
        mapA.remove(21);
        assertEquals(3, mapA.getSize());
        assertFalse(mapA.containsKey(21));
        assertNull(mapA.get(21));
        mapA.remove(26);
        assertEquals(3, mapA.getSize());
    }
    @Test
    public void removeCompareSize() {
        mapE.remove(25);
        mapA.remove(25);
        int e = mapA.getSize();
        int a = mapE.size();
        assertEquals(e, a);
    }

    @Test
    public void removeCompareValue() {
        mapE.remove(25);
        mapA.remove(25);
        String e = mapA.get(25);
        String a = mapE.get(25);
        assertEquals(e, a);
    }

    @Test
    public void containsKey() {
        boolean e = mapE.containsKey(25);
        boolean a = mapA.containsKey(25);
        assertEquals(e, a);
    }
    @Test
    public void containsAnyKey() {
        assertTrue(mapA.containsKey(23));
        assertTrue(mapA.containsKey(24));
        assertTrue(mapA.containsKey(25));
        assertFalse(mapA.containsKey(26));
    }

    @Test
    public void containsValue() {
        boolean e = mapE.containsValue("Diana");
        boolean a = mapA.containsValue("Diana");
        assertEquals(e, a);
    }

    @Test
    public void isEmpty() {
        boolean a = mapE.isEmpty();
        assertNotNull(a);
    }

    @Test
    public void getSize() {
        int e = mapA.getSize();
        int a = mapE.size();
        assertEquals(e, a);
    }
}