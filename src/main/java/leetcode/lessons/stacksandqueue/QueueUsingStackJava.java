package leetcode.lessons.stacksandqueue;

import java.util.Stack;

/**
 * Implement queue using stack.
 *
 * Both have different order to data retrieval.
 * So, we can use second stack to reverse the order of elements.
 * We have to be careful with this because when we call enqueue, it has to add elements to stack1, but should not add to stack 2 unless it's empty.
 * Once, stack2 becomes empty, we can add all elements from stack1 and then dequeue
 *
 */
public class QueueUsingStackJava {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public QueueUsingStackJava() {
        stack1 = new Stack<Integer>(); // enqueue here
        stack2 = new Stack<Integer>(); // dequeue from here
    }

    public int size() {
        return stack1.size() + stack2.size();
    }

    public void enqueue(int value) {
        stack1.push(value);
    }

    // Move elements from stack1 to stack2 when stack2 is empty.
    private void shiftStacks() {
        if (stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                int poppedItem = stack1.pop();
                stack2.push(poppedItem);
            }
        }
    }

    public int dequeue() {
        shiftStacks();
        return stack2.pop();
    }

    public int peek() {
        shiftStacks();
        return stack2.peek();
    }

    public static void main(String[] args) {
        QueueUsingStackJava queue = new QueueUsingStackJava();
        queue.enqueue(1);
        queue.enqueue(2);
        System.out.printf("The first element is %d\n", queue.peek());
        System.out.printf("The first element is %d\n", queue.dequeue());
        queue.enqueue(3);
        System.out.printf("The first element is %d\n", queue.dequeue());
        System.out.printf("The size of queue is %d\n", queue.size());

    }
}
