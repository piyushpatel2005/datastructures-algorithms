package leetcode.lessons.lists;

public class SinglyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public SinglyLinkedList(int value) {
        Node node = new Node(value);
        this.head = node;
        this.size = this.size++;
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            this.size++;
            return;
        }
        Node currentNode = this.head;
        this.tail.next = newNode;
        this.tail = newNode;
        // without tail use below.
//        while (currentNode.next != null) {
//            currentNode = currentNode.next;
//        }
//        currentNode.next = newNode;
        this.size++;
    }

    public int size() {
        return this.size;
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
    }

    public static void main(String[] args) {
        SinglyLinkedList lst = new SinglyLinkedList();
        lst.insert(1);
        lst.insert(2);
        System.out.printf("Size of list is %d\n", lst.size());
        lst.print();
    }
}
