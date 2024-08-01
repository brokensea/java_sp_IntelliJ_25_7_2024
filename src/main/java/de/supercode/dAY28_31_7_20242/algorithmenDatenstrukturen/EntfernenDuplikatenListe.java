package de.supercode.dAY28_31_7_20242.algorithmenDatenstrukturen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntfernenDuplikatenListe {
    public static void main(String[] args) {
//        List<String> stringList = new ArrayList<>();
//        stringList.addAll(List.of("1", "1", "2", "2", "3", "3"));
        List<String> stringList = new ArrayList<>(List.of("1", "1", "2", "2", "3", "3"));
        EntfernenDuplikatenListe.entfernenDuplikatenListe(stringList).forEach((string -> System.out.println(string)));
        //EntfernenDuplikatenListe.entfernenDuplikatenListe(stringList).forEach((System.out::println));

    }

    public static <T> Set<T> entfernenDuplikatenListe(List<T> list) {
//        Set<T> set = new HashSet<>();
//        set.addAll(list);
        Set<T> set = new HashSet<>(list);
        return set;
    }
}
