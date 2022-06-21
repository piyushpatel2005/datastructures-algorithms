package leetcode.lessons.stacksandqueue;

/**
 * Implement k stacks using an array.
 *
 * In this case, we can divide the array into k equal parts and use those to store stack elements.
 */
public class ThreeInOneStackJava {
    private int numberOfStacks = 3;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public ThreeInOneStackJava(int stackSize) {
        this.stackCapacity = stackSize; // how many stacks we want to create from an array.
        values = new int[stackSize * numberOfStacks]; // stores the actual values inside each stack by dividing this array into 3 parts
        sizes = new int[numberOfStacks]; // keeps track of size of each stack we create
    }

    public boolean isFull(int stackNumber) {
        if (sizes[stackNumber] == stackCapacity) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty(int stackNumber) {
        if (sizes[stackNumber] == 0) {
            return true;
        }
        return false;
    }

    private int indexOfTop(int stackNumber) {
        int offset = stackNumber * stackCapacity;
        int size = sizes[stackNumber];
        return offset + size - 1;
    }

    public void push(int stackNumber, int item) {
        if (isFull(stackNumber)) {
            System.out.printf("The %d stack is full\n", stackNumber);
        } else {
            sizes[stackNumber]++;
            values[indexOfTop(stackNumber)] = item;
        }
    }

    public int pop(int stackNumber) {
        if (isEmpty(stackNumber)) {
            System.out.printf("The %d stack is empty\n", stackNumber);
            return -1;
        } else {
            int topIndex = indexOfTop(stackNumber); // get index of last added element at the top
            int value = values[topIndex];           // get the last added element
            values[topIndex] = 0;                   // change values to 0 for this index
            sizes[stackNumber]--;                   // reduce size to consider this empty space next time
            return value;
        }
    }

    public int peek(int stackNumber) {
        if (isEmpty(stackNumber)) {
            System.out.printf("The %d stack is empty\n", stackNumber);
            return -1;
        } else {
            return values[indexOfTop(stackNumber)];
        }
    }

    public static void main(String[] args) {
        ThreeInOneStackJava stack = new ThreeInOneStackJava(3);
        boolean firstStackEmpty = stack.isEmpty(0);
        System.out.println("First stack is empty? " + firstStackEmpty);
        boolean firstStackFull = stack.isFull(0);
        System.out.println("First stack is full? " + firstStackFull);
        stack.push(0, 1);
        stack.push(0, 2);
        stack.push(0, 3);
        stack.push(0, 4);

        stack.push(1, 4);
        stack.push(1, 5);
        stack.push(1, 6);

        stack.push(2, 7);
        stack.push(2, 8);
        stack.push(2, 9);

        System.out.println("The top of head for 2nd stack is " + stack.peek(2));

    }
}
