package leetcode.lessons.lists;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem 142: Linked List Cycle II
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 *
 * Do not modify the linked list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * Example 2:
 *
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 * Example 3:
 *
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 *
 *
 *
 * Constraints:
 *
 *     The number of the nodes in the list is in the range [0, 104].
 *     -105 <= Node.val <= 105
 *     pos is -1 or a valid index in the linked-list.
 *
 *
 *
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */
public class LinkedListCycle2Java {
    // Space complexity: O(n)
    public Node detectCycle(Node head) {
        Set<Node> elems = new HashSet<>();
        Node currentNode = head;
        while (currentNode != null) {
            if (elems.contains(currentNode)) {
                return currentNode;
            }
            elems.add(currentNode);
            currentNode = currentNode.next;
        }
        return null;

    }

    private Node getFirstIntersect(Node head) {
        Node fast = head;
        Node slow = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return slow;
            }
        }
        return null;
    }

    // Space complexity: O(1)
    public Node detectCycle2(Node head) {
        if (head == null) {
            return null;
        }
        Node firstIntersect = getFirstIntersect(head);
        if (firstIntersect == null) {
            return null;
        }

        // Tp fomd the entry to the cycle, we have two pointers traverse at the same speed
        // one from the front of the list, the other from the point of intersection.
        Node ptr1 = head;
        Node ptr2 = firstIntersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }


}
