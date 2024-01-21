import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class MyHashMapTest {
    private MyHashMap<Integer, String> m1 =new MyHashMap<>();
    private HashMap<Integer, String> m2 =new HashMap<>();

    @Before
    public void setUp1() {
        m1.put(24, "Diana");
        m1.put(25, "Maks");
        m1.put(23, "Dasha");
        m1.put(21, "Lesha");
    }
    @Before
    public void setUp2() {
        m2.put(24, "Diana");
        m2.put(25, "Maks");
        m2.put(23, "Dasha");
        m2.put(21, "Lesha");
    }
    @Test
    public void get1() {
        String e = m2.get(24);
        String a = m1.get(24);
        Assert.assertEquals(e, a);
    }

    @Test
    public void get2() {
        String a = m1.get(27);
        Assert.assertNull(a);
    }

    @Test
    public void removeCompareSize() {
        m2.remove(25);
        m1.remove(25);
        int e = m1.getSize();
        int a = m2.size();
        Assert.assertEquals(e, a);
    }

    @Test
    public void removeCompareValue() {
        m2.remove(25);
        m1.remove(25);
        String e = m1.get(25);
        String a = m2.get(25);
        Assert.assertEquals(e, a);
    }

    @Test
    public void containsKey() {
        boolean e = m2.containsKey(25);
        boolean a = m1.containsKey(25);
        Assert.assertEquals(e, a);
    }

    @Test
    public void containsValue() {
        boolean e = m2.containsValue("Diana");
        boolean a = m1.containsValue("Diana");
        Assert.assertEquals(e, a);
    }

    @Test
    public void isEmpty() {
        boolean a = m2.isEmpty();
        Assert.assertNotNull(a);
    }

    @Test
    public void getSize() {
        int e = m1.getSize();
        int a = m2.size();
        Assert.assertEquals(e, a);
    }
}