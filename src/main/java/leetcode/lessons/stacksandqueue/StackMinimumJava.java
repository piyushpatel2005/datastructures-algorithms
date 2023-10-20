package leetcode.lessons.stacksandqueue;

import java.util.Stack;

/**
 * How would you design a stack which in addition to push and pop, has a function min which returns the minimum element?
 * push, pop and min should all operate in O(1)
 */

class Node {
    int value;
    Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}

public class StackMinimumJava {
    private Node top;
    private Node min;

    public StackMinimumJava() {
        this.top = null;
        this.min = null;
    }

    public int min() {
        return min.value;
    }

    public void push(int value) {
        if (this.min == null) {
            this.min = new Node(value, min);
        } else if (min.value < value) {
            this.min = new Node(min.value, min);
        } else {
            this.min = new Node(value, min);
        }

        this.top = new Node(value, top);
    }

    public int pop() {
        this.min = min.next;
        int result = top.value;
        this.top = top.next;
        return result;
    }

    public static void main(String[] args) {
        StackMinimumJava stack = new StackMinimumJava();
        stack.push(4);
        System.out.printf("The minimum in stack is %d\n", stack.min());
        stack.push(3);
        System.out.printf("The minimum in stack is %d\n", stack.min());
        stack.push(5);
        System.out.printf("The minimum in stack is %d\n", stack.min());
        stack.pop();
        stack.pop();
        System.out.printf("The minimum in stack is %d\n", stack.min());
    }
}
