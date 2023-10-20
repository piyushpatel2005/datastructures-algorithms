package leetcode.lessons.lists;

public class SinglyLinkedList {

    private Node head;
//    private Node tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
//        this.tail = null;
        this.size = 0;
    }

    public SinglyLinkedList(int value) {
        Node node = new Node(value);
        this.head = node;
        this.size = this.size++;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        Node currentNode = this.head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.value;
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        if (this.head == null) {
            this.head = newNode;
//            this.tail = newNode;
            this.size++;
            return;
        }
        Node currentNode = this.head;
//        this.tail.next = newNode;
//        this.tail = newNode;
        // without tail use below.
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
        this.size++;
    }

    public void insert(int index, int value) {
        if (index > size) return;
        if (index < 0) index = 0;
        this.size++;
        Node currentNode = this.head;
        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.next;
            System.out.println("after for loop" + Integer.toString(i) + " : " + currentNode);
        }

        Node newNode = new Node(value);
        if (currentNode.next == null) {
            newNode.next = null;
        } else {
            newNode.next = currentNode.next;
        }
        currentNode.next = newNode;
        System.out.println("end of loop");
    }

    public void prepend(int value) {
        this.insert(0, value);
    }

    public void append(int value) {
        this.insert(this.size, value);
    }

    public void delete(int index) {
        if (index < 0 || index >= this.size) return;
        this.size--;
        if (index == 0) {
            this.head = this.head.next;
            return;
        }
        Node currentNode = this.head;
        for (int i = 1; i < index; i++) {
            currentNode = currentNode.next;
        }
        currentNode.next = currentNode.next.next;
    }

    public int size() {
        return this.size;
    }

    public int find(int data) {
        Node currentNode = this.head;
        for (int i = 0; i < this.size; i++) {
            if (currentNode.value == data)
                return i;
            currentNode = currentNode.next;
        }
        return -1;
    }

    // TODO: Implement other methods

    public void print() {
        Node currentNode = head;
        if (this.size == 0) {
            System.out.println("Empty List");
        }
        while (currentNode != null) {
            System.out.printf("%d => ", currentNode.value);
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SinglyLinkedList lst = new SinglyLinkedList();
        lst.insert(1);
        lst.insert(2);
        System.out.printf("Size of list is %d\n", lst.size());
        lst.print();

        lst.insert(3);
        lst.print();

        lst.insert(3, 4);
        lst.print();

        lst.append(5);
        lst.print();
        System.out.printf("The item at index %d is %d\n", 3, lst.get(3));
        System.out.printf("The item %d is found at index position %d\n", 5, lst.find(5));
        System.out.printf("The item %d is found at index position %d\n", 1, lst.find(1));
        System.out.printf("The item %d is found at index position %d\n", 7, lst.find(7));

        lst.delete(3);
        lst.delete(0);
        lst.print();

        lst.delete(2);
        lst.print();
    }
}
