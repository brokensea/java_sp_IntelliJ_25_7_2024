package dAY26_29_7_2024Test;

import de.supercode.dAY26_29_7_2024.algorithmenDatenstrukturen.DeineLinkedList;

public class DeineLinkedListTest {
    public static void main(String[] args) {
        DeineLinkedList list = new DeineLinkedList();
        // Test add and printList methods
        System.out.println("Adding elements to the list:");
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.printList(); // Expected output: 1 2 3 4

        // Test add(int index, int data) method
        System.out.println("Adding element 5 at index 2:");
        list.add(2, 5);
        list.printList(); // Expected output: 1 2 5 3 4

        // Test remove method
        System.out.println("Removing element at index 3:");
        list.remove(3);
        list.printList(); // Expected output: 1 2 5 4

        // Additional Tests

        // Test remove(int index) method on edge cases
        System.out.println("Removing first element:");
        list.remove(0);
        list.printList(); // Expected output: 2 5 4

        System.out.println("Removing last element:");
        list.remove(list.getSize() - 1);
        list.printList(); // Expected output: 2 5

        // Test get(int index) method
        System.out.println("Getting element at index 1:");
        int element = list.get(1);
        System.out.println("Element at index 1: " + element); // Expected output: 5


        // Test isEmpty() method
        System.out.println("Is the list empty?");
        boolean isEmpty = list.isEmpty();
        System.out.println("IsEmpty: " + isEmpty); // Expected output: false

        // Remove all elements to test isEmpty
        list.remove(0);
        list.remove(0);
        System.out.println("After removing all elements, is the list empty?");
        isEmpty = list.isEmpty();
        System.out.println("IsEmpty: " + isEmpty); // Expected output: true

        // Test getKthFromEnd method
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println("List before getKthFromEnd test:");
        list.printList(); // Expected output: 1 2 3 4 5

        System.out.println("Getting 1st element from the end:");
        int kthElement = list.getKthFromEnd(1);
        System.out.println("Element: " + kthElement); // Expected output: 5

        System.out.println("Getting 3rd element from the end:");
        kthElement = list.getKthFromEnd(3);
        System.out.println("Element: " + kthElement); // Expected output: 3

        // Test reversePrintList method
        System.out.println("Printing list in reverse:");
        list.reverse();
        list.printList();
        // Expected output: 5 4 3 2 1
    }
}
