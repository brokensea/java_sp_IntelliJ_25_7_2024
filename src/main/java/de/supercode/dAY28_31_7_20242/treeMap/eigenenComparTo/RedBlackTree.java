package de.supercode.dAY28_31_7_20242.treeMap.eigenenComparTo;

public class RedBlackTree {
    private Node root;

    private static class Node {
        int value;
        Node leftNode, rightNode, parentNode;
        boolean isRed;

        Node(int value) {
            this.value = value;
            this.isRed = true;
            this.leftNode = this.rightNode = null;
            this.parentNode = null;
        }
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            root.isRed = false;// 根节点是黑色
        } else {
            insertRec(root, newNode);
            fixInsert(newNode);
        }
    }

    private void insertRec(Node current, Node newNode) {
        if (newNode.value < current.value) {
            if (current.leftNode == null) {
                current.leftNode = newNode;
                newNode.parentNode = current;
            } else {
                insertRec(current.leftNode, newNode);
            }
        } else {
            if (current.rightNode == null) {
                current.rightNode = newNode;
                newNode.parentNode = current;
            } else {
                insertRec(current.rightNode, newNode);
            }
        }
    }

    private void fixInsert(Node node) {
        while (node != root && node.parentNode.isRed) {
            if (node.parentNode == node.parentNode.parentNode.leftNode) {
                Node uncle = node.parentNode.parentNode.rightNode;
                if (uncle != null && uncle.isRed) {
                    node.parentNode.isRed = false;
                    uncle.isRed = false;
                    node.parentNode.parentNode.isRed = true;
                    node = node.parentNode.parentNode;
                } else {
                    if (node == node.parentNode.rightNode) {
                        node = node.parentNode;
                        leftRotate(node);
                    }
                    node.parentNode.isRed = false;
                    node.parentNode.parentNode.isRed = true;
                    rightRotate(node.parentNode.parentNode);
                }
            } else {
                Node uncle = node.parentNode.parentNode.leftNode;
                if (uncle != null && uncle.isRed) {
                    node.parentNode.isRed = false;
                    uncle.isRed = false;
                    node.parentNode.parentNode.isRed = true;
                    node = node.parentNode.parentNode;
                } else {
                    if (node == node.parentNode.leftNode) {
                        node = node.parentNode;
                        rightRotate(node);
                    }
                    node.parentNode.isRed = false;
                    node.parentNode.parentNode.isRed = true;
                    leftRotate(node.parentNode.parentNode);
                }
            }
        }
        root.isRed = false;// 根节点始终是黑色
    }

    private void leftRotate(Node node) {
        Node rightChild = node.rightNode;
        node.rightNode = rightChild.leftNode;
        if (rightChild.leftNode != null) {
            rightChild.leftNode.parentNode = node;
        }
        rightChild.parentNode = node.parentNode;
        if (node.parentNode == null) {
            root = rightChild;
        } else if (node == node.parentNode.leftNode) {
            node.parentNode.leftNode = rightChild;
        } else {
            node.parentNode.rightNode = rightChild;
        }
        rightChild.leftNode = node;
        node.parentNode = rightChild;
    }

    private void rightRotate(Node node) {
        Node leftChild = node.leftNode;
        node.leftNode = leftChild.rightNode;
        if (leftChild.rightNode != null) {
            leftChild.rightNode.parentNode = node;
        }
        leftChild.parentNode = node.parentNode;
        if (node.parentNode == null) {
            root = leftChild;
        } else if (node == node.parentNode.rightNode) {
            node.parentNode.rightNode = leftChild;
        } else {
            node.parentNode.leftNode = leftChild;
        }
        leftChild.rightNode = node;
        node.parentNode = leftChild;
    }

    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.leftNode);
            System.out.print(node.value + (node.isRed ? "R " : "B "));
            inorderTraversal(node.rightNode);
        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(25);

        System.out.println("Inorder Traversal of the Red-Black Tree:");
        tree.inorderTraversal(); // 应该显示红黑树的中序遍历结果
    }
}
