package de.supercode.dAY26_29_7_2024.algorithmenDatenstrukturen;

public class DeineLinkedList {
    private Knoten head;
    private Knoten tail;
    private int size;

    public DeineLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public void addSize() {
        this.size++;
    }

    public void add(int data) {
        Knoten newKnoten = new Knoten(null, data, null);
        if (tail == null) {
            head = newKnoten;
            tail = newKnoten;
        } else {
            tail.setHinterKonten(newKnoten);
            newKnoten.setVorKonten(newKnoten);
            tail = newKnoten;
        }
        addSize();
    }

    public void add(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Knoten newKonten = new Knoten(null, data, null);
        if (index == 0) {
            // insert von head
            if (head == null) {
                head = newKonten;
                tail = newKonten;
            } else {
                newKonten.setHinterKonten(head);
                head.setVorKonten(newKonten);
                head = newKonten;
            }
        } else if (index == size) {
            // insert von tail
            tail.setHinterKonten(newKonten);
            newKonten.setVorKonten(tail);
            tail = newKonten;
        } else {
            // insert von zwischen
            Knoten current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getHinterKonten();
            }
            Knoten next = current.getHinterKonten();
            current.setHinterKonten(newKonten);
            newKonten.setVorKonten(current);
            newKonten.setHinterKonten(next);
            next.setVorKonten(newKonten);
        }
        addSize();
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.getHinterKonten();
                head.setVorKonten(null);
            }
        } else if (index == size - 1) {
            tail = tail.getVorKonten();
            tail.setHinterKonten(null);
        } else {
            // remove in middle.
            Knoten current = head;
            for (int i = 0; i < index; i++) {
                current = current.getHinterKonten();
            }
            Knoten previous = current.getVorKonten();
            Knoten next = current.getHinterKonten();
            previous.setHinterKonten(next);
            next.setVorKonten(previous);
        }
        size--;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Knoten current = head;
        for (int i = 0; i < index; i++) {
            current = current.getHinterKonten();
        }
        return current.getDate();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printList() {
        Knoten current = head;
        while (current != null) {
            System.out.print(current.getDate() + " ");
            current = current.getHinterKonten();
        }
        System.out.println();
    }

    public void reverse() {
        Knoten current = head;
        Knoten prev = null;
        Knoten next = null;
        while (current != null) {
            next = current.getHinterKonten();// Den nächsten Knoten speichern
            current.setHinterKonten(prev);// Den Zeiger des aktuellen Knotens umkehren
            current.setVorKonten(next);//Setze den vorherigen Knotenzeiger
            prev = current;//Prev zum aktuellen Knoten verschieben
            current = next;//Strom zum nächsten Knoten verschieben
        }
        Knoten temp = head;
        head = tail;
        tail = temp;
    }

    public int getKthFromEnd(int k) {
        if (k <= 0 || k > size) {
            throw new IllegalArgumentException("k: " + k + " is out of bounds");
        }
        Knoten first = head;
        Knoten second = head;
        // Bewege den first Zeiger k Schritte nach vorne
        for (int i = 0; i < k; i++) {
            first = first.getHinterKonten();
        }

        // Bewege gleichzeitig die first und second Zeiger, bis first das Ende der Liste erreicht
        while (first != null) {
            first = first.getHinterKonten();
            second = second.getHinterKonten();
        }

        return second.getDate();
    }

    class Knoten {
        Knoten vorKonten;
        Integer date;
        Knoten hinterKonten;

        public Knoten(Knoten vorKonten, Integer date, Knoten hinterKonten) {
            this.vorKonten = vorKonten;
            this.date = date;
            this.hinterKonten = hinterKonten;
        }

        //        getter setter
        public Knoten getVorKonten() {
            return vorKonten;
        }

        public void setVorKonten(Knoten vorKonten) {
            this.vorKonten = vorKonten;
        }

        public Integer getDate() {
            return date;
        }

        public void setDate(Integer date) {
            this.date = date;
        }

        public Knoten getHinterKonten() {
            return hinterKonten;
        }

        public void setHinterKonten(Knoten hinterKonten) {
            this.hinterKonten = hinterKonten;
        }
    }
}
/*
Bonusaufgabe 1: reverse()
实现一个方法，来反转链表中的节点顺序。

步骤:
初始化三个指针: prev（前一个节点）, current（当前节点）, 和 next（下一个节点）。
遍历链表: 逐个节点地反转它们的指针方向。
更新头尾指针: 最后，将原来的头节点变成尾节点，将原来的尾节点变成头节点。*/

/*
Bonusaufgabe 2: getKthFromEnd(int k)
实现一个方法，返回链表中倒数第 k 个元素。

步骤:
检查 k 是否有效: k 必须在 1 和 size 之间。
使用两个指针: first 和 second。
移动 first 指针: 从头开始，移动 first 指针 k 次。
同时移动两个指针: 直到 first 到达链表末尾，此时 second 指针所指的就是倒数第 k 个节点。*/
