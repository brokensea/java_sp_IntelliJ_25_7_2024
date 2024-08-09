package de.supercode.dAY28_31_7_2024.treeMap.eigenenComparTo;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapCustomObjects {
    public static void main(String[] args) {
        // Erstellen einer TreeMap mit natürlicher Ordnung der Person-Objekte (nach Alter)
        TreeMap<Person, String> treeMap = new TreeMap<>();
        treeMap.put(new Person("Alice", 30), "Alice's Info");
        treeMap.put(new Person("Bob", 25), "Bob's Info");
        treeMap.put(new Person("Charlie", 35), "Charlie's Info");

        // Anzeigen der Elemente der TreeMap in natürlicher Ordnung
        System.out.println("TreeMap nach Alter sortiert: " + treeMap);
        // Erstellen einer TreeMap mit benutzerdefiniertem Comparator (nach Name)
        TreeMap<Person, String> treeMapByName = new TreeMap<>(Comparator.comparing(p -> p.name));
        // TreeMap<Person, String> peopleByAgeDesc = new TreeMap<>(Comparator.comparingInt(Person::getAge).reversed());
        treeMapByName.put(new Person("Alice", 30), "Alice's Info");
        treeMapByName.put(new Person("Bob", 25), "Bob's Info");
        treeMapByName.put(new Person("Charlie", 35), "Charlie's Info");
        // Anzeigen der Elemente der TreeMap nach Namen sortiert
        System.out.println("TreeMap nach Namen sortiert: " + treeMapByName);
    }
}
/*
在 Java 中，TreeMap 是一种基于红黑树的实现，它会按照键的自然顺序或通过提供的比较器（Comparator）对键进行排序。你提到的代码使用了 Comparator.comparing 方法来创建一个自定义的比较器，用于排序 TreeMap 的键。下面是详细解释：

代码分析
java
Code kopieren
TreeMap<Person, String> treeMapByName = new TreeMap<>(Comparator.comparing(p -> p.name));
详细解释
TreeMap<Person, String>：

这是一个 TreeMap 对象，其中的键是 Person 对象，值是 String 类型。TreeMap 会按照键的自然顺序或通过提供的比较器对键进行排序。
Comparator.comparing(p -> p.name)：

Comparator.comparing 是 Java 8 引入的一个静态方法，用于创建一个 Comparator 对象。这个 Comparator 对象用于指定键的排序规则。
Comparator.comparing 方法接受一个 Function 作为参数，该 Function 将 Person 对象映射到用于排序的键（在本例中是 name 属性）。
p -> p.name 是一个 Lambda 表达式，它从 Person 对象中提取 name 属性。Comparator.comparing(p -> p.name) 就是创建一个比较器，这个比较器会根据 Person 对象的 name 属性来对 Person 对象进行排序。
过程说明
创建比较器：

Comparator.comparing(p -> p.name) 创建了一个比较器，这个比较器会按照 Person 对象的 name 属性进行排序。
这个比较器会比较两个 Person 对象的 name 属性，以决定它们的相对顺序。
初始化 TreeMap：

使用这个比较器初始化 TreeMap 对象 treeMapByName。这意味着 treeMapByName 中的 Person 对象会按照它们的 name 属性的字典顺序进行排序。
例如，如果你将两个 Person 对象 Alice 和 Bob 添加到 treeMapByName 中，treeMapByName 会将 Alice 放在 Bob 之前，因为 Alice 的 name 字典序在 Bob 之前。
*/

/*

红黑树（Red-Black Tree）是一种自平衡的二叉搜索树，它在数据结构中广泛用于实现动态数据集合。它的特点是能在最坏情况下保持平衡，确保基本操作（如插入、删除、查找）的时间复杂度为
𝑂(log 𝑛)，其中𝑛
n 是树中节点的数量。理解红黑树的关键在于它的性质和操作规则。

红黑树的基本性质
红黑树具有以下性质，这些性质帮助保持树的平衡：

节点颜色：

每个节点要么是红色，要么是黑色。
根节点：

根节点必须是黑色。
红色节点的子节点：

如果一个节点是红色，则它的两个子节点必须是黑色（即，不能有两个连续的红色节点）。
每个节点到其所有叶子节点的路径上黑色节点的数量相同：

从任意节点到其每个叶子节点的路径上，必须包含相同数量的黑色节点。这一性质确保了从根到叶子的路径长度大致相同，从而保证了树的平衡性。
叶子节点：

叶子节点（NIL节点，表示树的外部空节点）是黑色的。虽然在实现中这些叶子节点通常被省略，但它们在理论上是存在的。
红黑树的操作
插入
插入节点：

像普通的二叉搜索树一样，首先在树中插入新节点。
重新着色和旋转：

插入新节点后，可能会破坏红黑树的性质，需要通过重新着色和旋转来恢复平衡。
重新着色：调整节点的颜色，以满足红黑树的性质。
旋转：通过左旋和右旋操作来调整树的结构，使其保持平衡。旋转分为：
左旋：将某个节点作为左子树的根，提升其右子节点。
右旋：将某个节点作为右子树的根，提升其左子节点。
删除
删除节点：

删除节点时，首先移除目标节点，可能需要用其直接后继节点或前驱节点替代。
重新着色和旋转：

删除操作后，可能会破坏红黑树的性质，同样需要通过重新着色和旋转来恢复平衡。
示例代码
以下是一个简单的红黑树插入操作的示例代码，注意这只是一个简化的示例，实际的实现要复杂得多：

java
Code kopieren
public class RedBlackTree {
    private Node root;

    private static class Node {
        int value;
        Node left, right, parent;
        boolean isRed;

        Node(int value) {
            this.value = value;
            this.isRed = true; // 新插入的节点是红色
        }
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            root.isRed = false; // 根节点是黑色
        } else {
            insertRec(root, newNode);
            fixInsert(newNode);
        }
    }

    private void insertRec(Node current, Node newNode) {
        // 插入逻辑（类似于普通的二叉搜索树）
    }

    private void fixInsert(Node node) {
        // 重新着色和旋转逻辑
    }

    // 左旋和右旋方法
    private void leftRotate(Node node) { */
/* 实现左旋 *//*
 }
    private void rightRotate(Node node) { */
/* 实现右旋 *//*
 }
}
红黑树的优势
平衡性：红黑树保持平衡的方式使其在最坏情况下也能保证
O(log n)
O(log n) 的操作时间复杂度。
自适应：通过插入和删除操作的旋转和重新着色，红黑树自动调整其结构，以适应不断变化的动态数据集。
总结
红黑树是一种自平衡的二叉搜索树，通过遵循特定的颜色和结构性质，确保了所有基本操作的对数时间复杂度。这些性质和操作规则帮助红黑树保持平衡，即使在面对频繁的插入和删除操作时。*/
