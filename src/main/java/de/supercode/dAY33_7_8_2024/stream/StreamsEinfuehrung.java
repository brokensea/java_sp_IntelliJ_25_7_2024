package de.supercode.dAY33_7_8_2024.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsEinfuehrung {
    public static void main(String[] args) {
//-. **Liste von Zahlen filtern**
//- Gegeben: Eine Liste von ganzen Zahlen.
//- Aufgabe: Filter alle geraden Zahlen aus der Liste.
        List<Integer> list1 = erzeugenList(10);
//        System.out.println(list);
        list1 = list1.stream()
                .filter(zahl -> (zahl % 2 == 0))
                .toList();
        System.out.println(list1);


//-. **Liste von Zeichenketten in Großbuchstaben umwandeln**
//- Gegeben: Eine Liste von Zeichenketten.
//- Aufgabe: Wandele alle Zeichenketten in der Liste in Großbuchstaben um.
        List<String> list2 = List.of("aaa", "bbb", "ccc");
//        list2 = list2.stream().map(zeichen -> zeichen.toUpperCase()).toList();
        list2 = list2.stream().map(String::toUpperCase).toList();
        System.out.println(list2);

//-. **Summe einer Liste von Zahlen berechnen**
//- Gegeben: Eine Liste von ganzen Zahlen.
//- Aufgabe: Berechne die Summe aller Zahlen in der Liste.
        List<Integer> list3 = erzeugenList(10);
        int sum = list3.stream()
                .mapToInt(zahl -> zahl).sum();
        System.out.println(sum);

//          int sum = list3.stream()
//                .mapToInt(Integer::intValue)  // 将每个 Integer 对象映射为 int 值
//                .sum();  // 计算所有 int 值的总和

//-. **Duplikate aus einer Liste entfernen**
//- Gegeben: Eine Liste von ganzen Zahlen mit Duplikaten.
//- Aufgabe: Entferne alle Duplikate aus der Liste.
        List<Integer> list4 = erzeugenList(5);
        List<Integer> list5 = erzeugenList(5);
        list4.addAll(list5);
//        System.out.println(list4);
        list4 = list4.stream()
                .collect(Collectors.toCollection(HashSet::new))
                .stream()
                .toList();
       /* list4 = new HashSet<>(list4)
                .stream()
                .toList();*/
        System.out.println(list4);

//-. **Durchschnitt einer Liste von Zahlen berechnen**
//- Gegeben: Eine Liste von ganzen Zahlen.
//- Aufgabe: Berechne den Durchschnittswert der Zahlen in der Liste.
        List<Integer> list6 = erzeugenList(10);
        double durchschnittswert = list6.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
        System.out.println(durchschnittswert);


//-. **Zeichenketten nach Länge sortieren**
//- Gegeben: Eine Liste von Zeichenketten.
//- Aufgabe: Sortiere die Zeichenketten nach ihrer Länge.
        List<String> list7 = List.of("aaa", "bbbb", "ccccc");
        list7 = list7.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .toList();
        System.out.println(list7);
    }
    /*List<String> sortedList = list.stream()
        .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length())) // 降序排序
        .toList();*/

    /* public static List<Integer> erzeugenList(int anzahl) {
         List<Integer> list = new ArrayList<>();
         for (int i = 0; i < anzahl; i++) {
             list.add(i);
         }
         return list;
     }*/
    public static List<Integer> erzeugenList(int size) {
        return IntStream.range(1, size + 1) // 生成从 1 到 size 的整数流
                .boxed()// 转换为 Integer 对象
                .collect(Collectors.toCollection(ArrayList::new));// 收集到可变的 ArrayList

    }

}
