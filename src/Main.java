import my_hashmap.MyHashMap;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer, String> m1 =new MyHashMap<>();
        m1.put(24, "Diana");
        m1.put(25, "Maks");
        m1.put(23, "Dasha");
        m1.put(21, "Lesha");
        m1.print();

        HashMap<Integer, String> m2 =new HashMap<>();
        m2.put(24, "Diana");
        m2.put(25, "Maks");
        m2.put(23, "Dasha");
        m2.put(21, "Lesha");
        System.out.println(m2);
    }
}