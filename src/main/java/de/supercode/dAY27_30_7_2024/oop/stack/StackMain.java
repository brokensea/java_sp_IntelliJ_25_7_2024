package de.supercode.dAY27_30_7_2024.oop.stack;

import java.util.Stack;

public class StackMain {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(10);
        stack.push(15);
        // Überprüfen, ob der Stack leer ist
        System.out.println("Ist der Stack leer? " + stack.isEmpty());
        // Anzeigen des obersten Elements des Stacks, ohne es zu entfernen
        System.out.println("Oberstes Element des Stacks: " + stack.peek());
        // Entfernen und Rückgabe des obersten Elements des Stacks
        int poppedElement = stack.pop();
        System.out.println("Entferntes Element: " + poppedElement);
        // Anzeigen der Größe des Stacks
        System.out.println("Größe des Stacks: " + stack.size());
        // Anzeigen des obersten Elements des Stacks, ohne es zu entfernen
        System.out.println("Oberstes Element des Stacks: " + stack.peek());
        // Löschen des Stacks
        stack.clear();
        System.out.println("Ist der Stack leer nach dem Löschen? " + stack.isEmpty());

    }
}
