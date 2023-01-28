package leetcode.lessons.tree;

/**
 * Implement Binary Tree using Array.
 * In array, we will leave index position 0,
 * For left child, we will have cell found using 2x
 * For right child, we will use 2x+1 ,x is parent index position
 */
public class BinaryTreeUsingArray {
    String[] arr;
    int lastUsedIndex;

    public BinaryTreeUsingArray(int size) {
        arr = new String[size + 1]; // adding 1 to accommodate for 0 index which is not being used.
        this.lastUsedIndex = 0;
    }

    public boolean isFull() {
        return arr.length - 1 == lastUsedIndex;
    }

    public void insert(String data) {
        if (!isFull()) {
            arr[lastUsedIndex + 1] = data;
            lastUsedIndex++;
            System.out.println("The value " + data + " is inserted at index " + lastUsedIndex + ".");
        } else {
            System.out.println("The Binary Tree is full.");
        }
    }

    public static void main(String[] args) {
        BinaryTreeUsingArray tree = new BinaryTreeUsingArray(5);
        tree.insert("n1");
        tree.insert("n2");
        tree.insert("n3");
        tree.insert("n4");
        tree.insert("n5");
        tree.insert("n6");
    }
}
