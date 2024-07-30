package de.supercode.dAY23_25_7_2024.javaVariablenDatenstrukturen;

import java.util.HashMap;
import java.util.Map;

public class UebungWiederholung {

    public static void main(String[] args) {

    }


    public static boolean areArraysEqual(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        // 创建两个HashMap来记录元素及其出现的频率
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for (int interger : array1) {
            map1.put(interger, map1.getOrDefault(interger, 0) + 1);
        }

        for (int interger : array2) {
            map2.put(interger, map1.getOrDefault(interger, 0) + 1);
        }
        return map1.equals(map2);

    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (o == this) {
//            return true; // 如果两个对象是同一个对象，直接返回 true
//        }
//        if (!(o instanceof Map<?, ?> other)) {
//            return false; // 如果对象类型不同，返回 false
//        }
//        if (other.size() != size()) {
//            return false; // 如果大小不同，返回 false
//        }
//
//        try {
//            for (Entry<K, V> e : entrySet()) {
//                K key = e.getKey();
//                V value = e.getValue();
//                if (value == null) {
//                    if (!(other.get(key) == null && other.containsKey(key))) {
//                        return false; // 如果值为 null，检查其他 Map 中是否有相同的键并且值也为 null
//                    }
//                } else {
//                    if (!value.equals(other.get(key))) {
//                        return false; // 如果值不为 null，检查其他 Map 中是否有相同的键并且值也相同
//                    }
//                }
//            }
//        } catch (ClassCastException | NullPointerException unused) {
//            return false; // 如果出现类型转换异常或空指针异常，返回 false
//        }
//
//        return true; // 所有键值对都相同，返回 true
//    }


}
