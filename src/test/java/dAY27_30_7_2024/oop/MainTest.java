package dAY27_30_7_2024.oop;

import de.supercode.dAY27_30_7_2024.oop.Box;
import de.supercode.dAY27_30_7_2024.oop.GenerischeMethodeErstellen;

public class MainTest {
    public static void main(String[] args) {
        //  Box
        Box<String> stringBox = new Box<>("Hello");
        System.out.println("Initial content of stringBox: " + stringBox);
        stringBox.setIteam("World");
        System.out.println("Updated content of stringBox: " + stringBox);

        //  swap
        Integer[] numbers = {1, 2, 3, 4, 5};
        System.out.println("Before swap: ");
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // swap first and last element
        GenerischeMethodeErstellen.swap(numbers, 0, numbers.length - 1);

        System.out.println("After swap: ");
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
