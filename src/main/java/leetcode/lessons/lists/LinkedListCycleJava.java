package leetcode.lessons.lists;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem 141: Linked List Cycle
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, `pos` is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 */
public class LinkedListCycleJava {
    // Using hashset which takes O(n) space
    public boolean hascycle(Node head) {
        Set<Node> elems = new HashSet<>();
        while (head != null) {
            if (elems.contains(head)) {
                return true;
            }
            elems.add(head);
            head = head.next;
        }
        return false;

    }

    // Flyod's Tortoise and Hare algorithm
    // Space complexity: O(1)
    // Time complexity: O(n)
    public boolean hasCycle2(Node head) {
        if (head == null) return false;
        // start with same head, fast pointer will move two positions at a time and slow one position at a time.
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }
}
