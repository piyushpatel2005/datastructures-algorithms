package educative.lists;


import java.util.ArrayList;
import java.util.Random;

class LinkedListNode {
    public int key;
    public int data;
    public LinkedListNode next;
    public LinkedListNode arbitraryPointer;

    public LinkedListNode(int value) {
        this.data = value;
        this.next = null;
    }

    public LinkedListNode(int key, int data) {
        this.key = key;
        this.data =data;
        this.next = null;
    }

    public LinkedListNode(int data, LinkedListNode next) {
        this.data = data;
        this.next = next;
    }

    public LinkedListNode(int data, LinkedListNode next, LinkedListNode arbitraryPointer) {
        this.data = data;
        this.next = next;
        this.arbitraryPointer = arbitraryPointer;
    }
}


public class LinkedList {
    /**
     * Create LinkedList from ArrayList
     * @param lst
     * @return
     */
    public static LinkedListNode createLinkedList(ArrayList<Integer> lst) {
        LinkedListNode head = null;
        LinkedListNode tail = null;
        for (Integer x: lst) {
            LinkedListNode newNode = new LinkedListNode(x);
            if (head == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
        }
        return head;
    }

    public static LinkedListNode createLinkedList(int[] arr) {
        LinkedListNode head = null;
        LinkedListNode tail = null;

        for (int i = 0; i < arr.length; i++) {
            LinkedListNode newNode = new LinkedListNode(arr[i]);
            if (head == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
        }
        return head;
    }

    public static LinkedListNode insertAtHead(LinkedListNode head, int value) {
        LinkedListNode newNode = new LinkedListNode(value);
        newNode.next = head;
        return newNode;
    }

    public static LinkedListNode createRandomList(int length) {
        LinkedListNode head = null;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            head = insertAtHead(head, random.nextInt(100));
        }
        return head;
    }

    public static LinkedListNode insertAtTail(LinkedListNode head, int value) {
        LinkedListNode newNode = new LinkedListNode(value);
        if (head == null) {
            return newNode;
        }
        LinkedListNode curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        return head;
    }

    public static LinkedListNode insertAtTail(LinkedListNode head, LinkedListNode node) {
        if (head == null) {
            return node;
        }
        LinkedListNode curr = head;
        while (curr != null) {
            curr = curr.next;
        }
        curr.next = node;
        return head;
    }

    public static ArrayList<Integer> toList(LinkedListNode head) {
        ArrayList<Integer> lst = new ArrayList<>();
        LinkedListNode current = head;
        while (current != null) {
            lst.add(current.data);
            current = current.next;
        }
        return lst;
    }

    public static void display(LinkedListNode head) {
        LinkedListNode curr = head;
        while (curr != null) {
            System.out.printf("%d", curr.data);
            curr = curr.next;
            if (curr != null) {
                System.out.printf(", ");
            }
        }
        System.out.println();
    }

    public static LinkedListNode mergeAlternating(LinkedListNode list1, LinkedListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        LinkedListNode head = list1;
        while (list1.next != null && list2 != null) {
            LinkedListNode temp = list2;
            list2 = list2.next;
            temp.next = list1.next;
            list1.next = temp;
            list1 = temp.next;
        }

        if (list1.next == null) {
            list1.next = list2;
        }
        return head;
    }

    public static boolean isEqual(LinkedListNode list1, LinkedListNode list2) {
        if (list1 == list2)
            return true;
        while (list1 != null && list2 != null) {
            if (list1.data != list2.data)
                return false;
            list1 = list1.next;
            list2 = list2.next;
        }
        return (list1 == list2);
    }


}
