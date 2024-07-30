package dAY27_30_7_2024.oop.stack;

import de.supercode.dAY27_30_7_2024.oop.stack.MyOwnStack;
import de.supercode.dAY27_30_7_2024.oop.stack.MyOwnStackArray;

public class MyOwnStackTest {
    public static void main(String[] args) {
        //MyOwnStack<String> stringStack = new MyOwnStack<>();
        MyOwnStackArray<String> stringStack = new MyOwnStackArray<>();
        stringStack.push("A");
        stringStack.push("B");

        System.out.println("Peek: " + stringStack.peek()); // output: A
        System.out.println("Size: " + stringStack.size()); // output: 2

        System.out.println("Pop: " + stringStack.pop()); // output: A
        System.out.println("Size: " + stringStack.size()); // output: 1

        System.out.println("IsEmpty: " + stringStack.isEmpty()); // output: false
        stringStack.clear();
        System.out.println("IsEmpty after clear: " + stringStack.isEmpty()); // output: true

        MyOwnStack<Integer> intStack = new MyOwnStack<>();
        intStack.push(1);
        intStack.push(2);

        System.out.println("Peek: " + intStack.peek()); // output: 2
        System.out.println("Size: " + intStack.size()); // output: 2

        System.out.println("Pop: " + intStack.pop()); // output: 2
        System.out.println("Size: " + intStack.size()); // output: 1
    }
}
