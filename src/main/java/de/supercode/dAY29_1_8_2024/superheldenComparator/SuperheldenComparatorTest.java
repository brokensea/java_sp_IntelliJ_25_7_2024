package de.supercode.dAY29_1_8_2024.superheldenComparator;

import java.util.Arrays;
import java.util.List;

public class SuperheldenComparatorTest {
    public static void main(String[] args) {
        SuperheldenComparator hero1 = new SuperheldenComparator("Superman", 100, 80, Category.HERO);
        SuperheldenComparator hero2 = new SuperheldenComparator("Batman", 85, 70, Category.HERO);
        SuperheldenComparator hero3 = new SuperheldenComparator("Flash", 90, 100, Category.HERO);
        SuperheldenComparator villain1 = new SuperheldenComparator("Joker", 75, 65, Category.VILLAIN);
        SuperheldenComparator villain2 = new SuperheldenComparator("Lex Luthor", 95, 60, Category.VILLAIN);

        List<SuperheldenComparator> superheldenComparatorList = Arrays.asList(hero1, hero2, hero3, villain1, villain2);
        System.out.println("Sorting by Power Level:");
        superheldenComparatorList.sort(SuperheldenComparator.powerlevelComparator);
        superheldenComparatorList.forEach(System.out::println);

        System.out.println("Sorting by Power Level, Speed and Name:");
        superheldenComparatorList.sort(SuperheldenComparator.powerlevelComparator.thenComparing(SuperheldenComparator.speedComparator.thenComparing(SuperheldenComparator.nameComparator)));
        superheldenComparatorList.forEach(System.out::println);
    }
}
