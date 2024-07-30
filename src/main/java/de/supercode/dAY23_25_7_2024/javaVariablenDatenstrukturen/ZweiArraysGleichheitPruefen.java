package de.supercode.dAY23_25_7_2024.javaVariablenDatenstrukturen;

import java.util.HashMap;
import java.util.Map;

public class ZweiArraysGleichheitPruefen {

    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {5, 4, 3, 2, 1};
        int[] array3 = {1, 2, 2, 3, 4};

        System.out.println(haveSameElements(array1, array2)); //  true
        System.out.println(haveSameElements(array1, array3)); //  false
    }

    public static boolean haveSameElements(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        Map<Integer, Integer> frequencyMap1 = buildFrequencyMap(array1);
        Map<Integer, Integer> frequencyMap2 = buildFrequencyMap(array2);
        return frequencyMap1.equals(frequencyMap2);
    }

    private static Map<Integer, Integer> buildFrequencyMap(int[] array) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : array) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        return frequencyMap;
    }


//     这段代码是用于计算一个数组中每个元素出现次数的常见方式。下面是对这段代码的详细解释：
//
//java
//Code kopieren
//for (int num : array) {
//    frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
//}
//解释
//for (int num : array):
//
//这是增强型 for 循环（也叫做 "for-each" 循环），用于遍历数组 array 中的每个元素。
//在每次循环中，变量 num 代表当前数组元素的值。
//frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1):
//
//frequencyMap 是一个 HashMap，用于存储每个元素及其出现的次数。num 是当前遍历到的数组元素。
//frequencyMap.getOrDefault(num, 0)：这个方法用于从 frequencyMap 中获取键 num 对应的值（即 num 的当前计数）。
//如果 frequencyMap 中存在键 num，则返回其对应的值。
//如果 frequencyMap 中不存在键 num，则返回默认值 0。
//frequencyMap.getOrDefault(num, 0) + 1：计算 num 的新计数值。它是当前计数值加 1。
//frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1)：将更新后的计数值存入 frequencyMap 中，更新键 num 的值。
//总结
//这段代码的作用是：
//
//遍历数组中的每个元素。
//使用 frequencyMap 记录每个元素的出现频率。对于每个元素，若它已经存在于 frequencyMap 中，则将其计数加 1；若不存在，则将其计数设为 1。
//举个例子：
//
//假设数组 array 是 {3, 5, 3, 2}。
//
//初始状态：frequencyMap 是空的 {}。
//遍历到第一个元素 3，frequencyMap 中没有 3，因此 frequencyMap.getOrDefault(3, 0) 返回 0。更新后的计数是 0 + 1，所以 frequencyMap.put(3, 1)。
//现在 frequencyMap 是 {3=1}。
//遍历到第二个元素 5，frequencyMap 中没有 5，因此 frequencyMap.getOrDefault(5, 0) 返回 0。更新后的计数是 0 + 1，所以 frequencyMap.put(5, 1)。
//现在 frequencyMap 是 {3=1, 5=1}。
//遍历到第三个元素 3，frequencyMap 中已有 3，因此 frequencyMap.getOrDefault(3, 0) 返回 1。更新后的计数是 1 + 1，所以 frequencyMap.put(3, 2)。
//现在 frequencyMap 是 {3=2, 5=1}。
//遍历到最后一个元素 2，frequencyMap 中没有 2，因此 frequencyMap.getOrDefault(2, 0) 返回 0。更新后的计数是 0 + 1，所以 frequencyMap.put(2, 1)。
//最终 frequencyMap 是 {3=2, 5=1, 2=1}。
//这样，frequencyMap 就成功地记录了数组中每个元素的出现次数。
}
