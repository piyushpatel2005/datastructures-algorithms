package leetcode.challenge.beginners;

/**
 * Leetcode Challenge 876: Middle of the Linked List
 *
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3.
 *
 * Example 2:
 *
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 * Constraints:
 *
 *     The number of nodes in the list is in the range [1, 100].
 *
 *
 * We could first find the length of the list by traversing once and store each pointer in array.
 * At the end of the loop, return that array middle position.
 *
 * Better approach with O(1) time complexity is to use fast and slow pointers.
 * When fast pointer reach end of list, slow pointer with half speed will be in middle of the list.
 */

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class MiddleOfLinkedList {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode[] arr = new ListNode[100];
        int i = 0;
        while (head != null) {
            arr[i++] = head;
            head = head.next;
        }
        return arr[i / 2];
    }

    // Two pointer approach

    /**
     * Time complexity: O(n)
     * Space compexity: O(1)
     * @param head
     * @return
     */
    public ListNode middleNode2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
