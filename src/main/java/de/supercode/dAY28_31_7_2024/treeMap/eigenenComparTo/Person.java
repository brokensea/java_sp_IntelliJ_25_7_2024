package de.supercode.dAY28_31_7_2024.treeMap.eigenenComparTo;

import org.jetbrains.annotations.NotNull;

public class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(@NotNull Person person) {
        // Sortieren nach Alter
        return Integer.compare(this.age, person.age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
