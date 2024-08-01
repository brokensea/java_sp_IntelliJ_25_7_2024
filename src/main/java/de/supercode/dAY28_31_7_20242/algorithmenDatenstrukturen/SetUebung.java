package de.supercode.dAY28_31_7_20242.algorithmenDatenstrukturen;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetUebung {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();

        set.add(5);
        set.add(10);
        set.add(15);
        set.add(5); // Duplikate werden nicht hinzugefügt
        // Überprüfen, ob das Set leer ist
        System.out.println("Ist das Set leer?" + set.isEmpty());
        // Anzeigen der Elemente des Sets
        System.out.println("Elemente im Set: " + set);
        // Entfernen eines Elements aus dem Set
        set.remove(10);
        System.out.println("Enthält das Set die Zahl 10 nach dem Entfernen? " + set.contains(10));
        // Anzeigen der Größe des Sets
        System.out.println("Größe des Sets: " + set.size());
        // Iterieren über die Elemente des Sets
        System.out.println("Iterieren über die Elemente des Sets:");
        for (Integer integer : set) {
            System.out.println(integer);
        }
        // Löschen des Sets
        set.clear();
        System.out.println("Ist das Set leer nach dem Löschen? " + set.isEmpty());

        // Arbeiten mit einem zweiten Set und der retainAll-Methode
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(1, 2, 4, 5, 6, 7, 8));
        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);

       /* Die Methode retainAll in Java gibt einen booleschen Wert zurück,
        der angibt, ob sich der aufrufende Satz geändert hat.
        Wenn der aufrufende Satz bereits nur die Elemente enthält,
                die auch im übergebenen Satz enthalten sind, wird kein Element entfernt und die Methode gibt false zurück.
            Wenn jedoch Elemente aus dem aufrufenden Satz entfernt werden, gibt die Methode true zurück.*/
        System.out.println(set2.retainAll(set1));
        // Behalte nur die gemeinsamen Elemente in set1
        set1.retainAll(set2);

        System.out.println("Set 2 (after retainAll): " + set2);
        System.out.println("Set 1 (after retainAll): " + set1);
    }
}

/*
Arrays.asList(1,2,3,4,5) und List.of{1,2,3,4,5}
Arrays.asList(1, 2, 3, 4, 5) 和 List.of(1, 2, 3, 4, 5) 是在 Java 中创建列表（List）对象的两种不同方式。它们在行为和实现上有一些显著的差异。让我们详细看看它们的区别和使用方法。

Arrays.asList(1, 2, 3, 4, 5)
Arrays.asList 是一个静态方法，用于将一个数组或一组元素转换为一个固定大小的列表。

特点
固定大小：返回的列表大小固定，不能增加或删除元素。
可修改元素：虽然不能改变列表的大小，但可以修改列表中的元素。
底层实现：返回的列表实际上是一个 Arrays$ArrayList，它是 AbstractList 的一个内部类，不是 java.util.ArrayList。
示例
java
Code kopieren
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
System.out.println(list); // 输出：[1, 2, 3, 4, 5]

list.set(0, 10); // 可以修改元素
System.out.println(list); // 输出：[10, 2, 3, 4, 5]

// list.add(6); // 会抛出 UnsupportedOperationException
// list.remove(0); // 会抛出 UnsupportedOperationException
List.of(1, 2, 3, 4, 5)
List.of 是 Java 9 引入的一个静态工厂方法，用于创建不可变的列表。

特点
不可变：返回的列表是不可变的，不能增加、删除或修改元素。
更简洁：提供了一种更简洁和更安全的方法来创建不可变列表。
底层实现：返回的列表是 ImmutableCollections.ListN 类型（Java 9 及更高版本），具体实现细节是内部类。
示例
java
Code kopieren
List<Integer> list = List.of(1, 2, 3, 4, 5);
System.out.println(list); // 输出：[1, 2, 3, 4, 5]

// list.set(0, 10); // 会抛出 UnsupportedOperationException
// list.add(6); // 会抛出 UnsupportedOperationException
// list.remove(0); // 会抛出 UnsupportedOperationException
对比总结
可变性：Arrays.asList 返回的列表是固定大小但可修改的，而 List.of 返回的列表是完全不可变的。
用法：Arrays.asList 更适合需要修改元素但不需要改变列表大小的场景，而 List.of 更适合需要不可变列表的场景。
性能：对于不可变列表的创建，List.of 通常更高效，因为它不需要进行额外的数组拷贝操作。
使用建议
如果需要一个可修改的列表，使用 new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5))。
如果需要一个不可变的列表，使用 List.of(1, 2, 3, 4, 5)。
示例代码
java
Code kopieren
public class Main {
    public static void main(String[] args) {
        // 使用 Arrays.asList
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Arrays.asList: " + list1);
        list1.set(0, 10); // 可修改元素
        System.out.println("Modified Arrays.asList: " + list1);
        // list1.add(6); // 抛出 UnsupportedOperationException

        // 使用 List.of
        List<Integer> list2 = List.of(1, 2, 3, 4, 5);
        System.out.println("List.of: " + list2);
        // list2.set(0, 10); // 抛出 UnsupportedOperationException
        // list2.add(6); // 抛出 UnsupportedOperationException
    }
}
通过上述解释和示例代码，可以更好地理解 Arrays.asList 和 List.of 的区别及其使用场景。*/
