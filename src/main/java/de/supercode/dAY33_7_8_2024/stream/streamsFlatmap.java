package de.supercode.dAY33_7_8_2024.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static de.supercode.dAY33_7_8_2024.stream.StreamsEinfuehrung.erzeugenList;

public class streamsFlatmap {
    public static void main(String[] args) {
//-. **Liste von Listen zu einer einzigen Liste “flach machen”**
//- Gegeben: Eine Liste von Listen von ganzen Zahlen.
//- Aufgabe: Verwende `flatMap`, um eine flache Liste zu erstellen, die alle Zahlen enthält.
        List<Integer> list1 = erzeugenList(5);
        List<Integer> list2 = erzeugenList(3);
        List<List<Integer>> list3 = new ArrayList<>();
        list3.add(list1);
        list3.add(list2);
        System.out.println(list3);
        List<Integer> flattenedList = list3.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println(flattenedList);


//-. **Zeichenketten in eine Liste von Wörtern aufteilen**
//- Gegeben: Eine Liste von Sätzen (Zeichenketten).
//- Aufgabe: Verwende `flatMap`, um eine Liste von einzelnen Wörtern zu erstellen.
        List<String> listString1 = Arrays.asList("111", "222", "333");
        List<String> listString2 = Arrays.asList("444", "555", "666");
        List<List<String>> listString3 = new ArrayList<>();
        listString3.add(listString1);
        listString3.add(listString2);
        System.out.println(listString3);
        List<String> flattenedListString = listString3.stream()
                .flatMap(List::stream).toList();
        System.out.println(flattenedListString);
/*
        List<String> flattenedListString = listString3.stream()
                .flatMap(list -> list.stream()) // 使用 Lambda 表达式
                .collect(Collectors.toList());
        System.out.println(flattenedListString);*/

//-. **Filtern und flach machen einer Liste von Listen**
//- Gegeben: Eine Liste von Listen von ganzen Zahlen.
//- Aufgabe: Filter alle Zahlen, die größer als 5 sind.
        List<Integer> listInt1 = erzeugenList(5);
        List<Integer> listInt2 = erzeugenList(8);
        List<List<Integer>> listInt3 = new ArrayList<>();
        listInt3.add(listInt1);
        listInt3.add(listInt2);
        System.out.println(listInt3);
        List<Integer> groesserAls5List = listInt3.stream()
                .flatMap(List::stream)
                .filter(zahl -> zahl > 5)
                .toList();
        System.out.println(groesserAls5List);

    }

}
