package leetcode.lessons.tree;

import java.util.Queue;
import java.util.LinkedList;


class BinaryNode {
    String value;
    BinaryNode left;
    BinaryNode right;
    int height;
}

/**
 * Implement Binary Tree using Linked List
 *
 * Example Tree:
 *
 *              root
 *             /   \
 *            a     b
 *           / \   /  \
 *          c   d e    f
 *         / \
 *        g   h
 *
 * Pre Order Traversal
 * -------------------
 * For above Tree the pre order traversal, traverses root => left => right subtrees.
 * So, sequence will be root => a => c => g => h => d => b => e => f
 *
 * In Order Traversal
 * ------------------
 * In order traversal traverses left subtree => root => right subtree
 * sequence will be g => c => h => a => d => root => e => b => f
 *
 * Post Order Traversal
 * --------------------
 * The traversing sequence will be left => right => root subtree
 * Sequence will be g => h => c => d => a => e => f => b => root
 *
 * Level Order Traversal
 * ---------------------
 * Traverse all elements at the same level from left to right then to go children of this level.
 * Sequence will be root => a => b => c => d => e => f => g => h
 *
 *
 *
 *
 *
 */
public class BinaryTreeUsingLinkedList {
    BinaryNode root;

    public BinaryTreeUsingLinkedList() {
        this.root = null;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    public void preOrder(BinaryNode node) {
        if (node == null) return;
        System.out.print(node.value + " => ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(BinaryNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.value + " => ");
        inOrder(node.right);

    }

    public void postOrder(BinaryNode node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " => ");
    }

    public void levelOrder(BinaryNode node) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode currentNode = queue.remove();
            System.out.print(currentNode.value + " => ");
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }

    public boolean search(String value) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode currentNode = queue.remove();
            if (currentNode.value == value) {
                System.out.println("The value " + value + " is found in tree.");
                return true;
            }
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        System.out.println("The value " + value + " is not found in the tree.");
        return false;
    }

    public void insert(String value) {
        BinaryNode newNode = new BinaryNode();
        newNode.value = value;

        if (root == null) {
            root = newNode;
            System.out.println("Inserted value " + value + " at the root.");
            return;
        }

        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode currentNode = queue.remove();
            if (currentNode.left == null) {
                currentNode.left = newNode;
                break;
            } else if (currentNode.right == null) {
                currentNode.right = newNode;
                break;
            } else {
                queue.add(currentNode.left);
                queue.add(currentNode.right);
            }
        }
    }

    private BinaryNode getDeepestNode() {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode currentNode = null; // finally this one will be what we return
        while (!queue.isEmpty()) {
            currentNode = queue.remove();
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        return currentNode;
    }

    private void deleteDeepestNode() {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode previousNode, currentNode = null;
        while (!queue.isEmpty()) {
            previousNode = currentNode;
            currentNode = queue.remove();
            if (currentNode.left == null) {
                // This means previous node's right child was the deepest node.
                previousNode.right = null;
                return;
            }
            if (currentNode.right == null) {
                // This means currentNode's left is the deepest node.
                currentNode.left = null;
                return;
            }
            queue.add(currentNode.left);
            queue.add(currentNode.right);
        }
    }

    // Find the node
    // Find the deepest node
    // set deepest node's value to current node
    // Delete deepest node.
    public boolean delete(String value) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode current = queue.remove();
            if (current.value == value) {
                current.value = getDeepestNode().value;
                deleteDeepestNode();
                return true;
            } else {
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
        }
        return false;
    }

    public void clear() {
        this.root = null;
    }

    public static void main(String[] args) {
        BinaryTreeUsingLinkedList binaryTree = new BinaryTreeUsingLinkedList();

//        BinaryNode rootNode = new BinaryNode();
//        rootNode.value = "root";
//        BinaryNode aNode = new BinaryNode();
//        aNode.value = "a";
//        BinaryNode bNode = new BinaryNode();
//        bNode.value = "b";
//        BinaryNode cNode = new BinaryNode();
//        cNode.value = "c";
//        BinaryNode dNode = new BinaryNode();
//        dNode.value = "d";
//        BinaryNode eNode = new BinaryNode();
//        eNode.value = "e";
//        BinaryNode fNode = new BinaryNode();
//        fNode.value = "f";
//        BinaryNode gNode = new BinaryNode();
//        gNode.value = "g";
//        BinaryNode hNode = new BinaryNode();
//        hNode.value = "h";
//
//        rootNode.left = aNode;
//        rootNode.right = bNode;
//
//        bNode.left = eNode;
//        bNode.right = fNode;
//
//        aNode.left = cNode;
//        aNode.right = dNode;
//
//        cNode.left = gNode;
//        cNode.right = hNode;
//
//        binaryTree.root = rootNode;

        binaryTree.insert("root");
        binaryTree.insert("a");
        binaryTree.insert("b");
        binaryTree.insert("c");
        binaryTree.insert("d");
        binaryTree.insert("e");
        binaryTree.insert("f");
        binaryTree.insert("g");
        binaryTree.insert("h");


        System.out.println("Pre order traversal");
        // root => a => c => g => h => d => b => e => f
        binaryTree.preOrder(binaryTree.root);
        System.out.println();

        System.out.println("In order Traversal");
        // g => c => h => a => d => root => e => b => f
        binaryTree.inOrder(binaryTree.root);
        System.out.println();

        System.out.println("Post Order Traversal");
        // g => h => c => d => a => e => f => b => root
        binaryTree.postOrder(binaryTree.root);
        System.out.println();

        System.out.println("Leve Order Traversal");
        // root => a => b => c => d => e => f => g => h
        binaryTree.levelOrder(binaryTree.root);
        System.out.println();

        binaryTree.search("a");
        binaryTree.search("z");

        binaryTree.delete("e");
        binaryTree.levelOrder(binaryTree.root);
    }
}
