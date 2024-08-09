package de.supercode.dAY29_1_8_2024.lambdaAusdruck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaAusdruck {
    public static void main(String[] args) {
        List<Character> list = Arrays.asList('a', 'b', 'c');
        StringBuilder concatenatedString = new StringBuilder();
//        Aufgabe: Zeichenketten-Konkatenation:
//Gegeben ist eine Liste von Zeichenketten. Konkateniere alle Zeichenketten in der Liste zu einer einzigen Zeichenkette und gebe das Ergebnis aus.

//        list.forEach(a -> concatenatedString.append(a));
        list.forEach(concatenatedString::append);
        System.out.println(concatenatedString.toString());


//        可以在Lambda表达式内部完成拼接后立即输出（注意，这种方式不常见，因为通常不建议在forEach中进行副作用操作，如输出）
       /* list.forEach(str -> {
            concatenatedString.append(str);
            System.out.println(concatenatedString.toString());
        });*/

//        Aufgabe: Quadratzahlen:
//Gegeben ist eine Liste von ganzen Zahlen. Berechnen Sie das Quadrat jeder Zahl und geben Sie die Quadratzahlen aus
       /* List<Integer> listInt = Arrays.asList(1, 2, 3);
        listInt.forEach(num -> Math.pow(num, 2));
        System.out.println(listInt);*/
        List<Integer> listInt = Arrays.asList(1, 2, 3);
        List<Integer> squaredList = listInt.stream().map(num -> num * num).collect((Collectors.toList()));
        squaredList.forEach(System.out::println);

        List<Integer> squaredList2 = new ArrayList<>();
        listInt.forEach(num -> {
            squaredList2.add(num * num);
        });
        squaredList2.forEach(System.out::println);

//        Länge der Zeichenketten:
//Gegeben ist eine Liste von Zeichenketten. Gebe die Länge jeder Zeichenkette in der Liste aus.
        List<String> listString = Arrays.asList("aa", "bbb", "cccc");
        List<Integer> sizeList = new ArrayList<>();
//        listString.forEach(string -> sizeList.add(string.length()));
//        sizeList.forEach(System.out::println);

        sizeList = listString.stream().map(String::length).collect(Collectors.toList());
        sizeList.forEach(System.out::print);

    }
}
