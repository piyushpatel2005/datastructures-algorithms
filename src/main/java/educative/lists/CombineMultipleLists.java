package educative.lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Suppose we are given `n` arrays that are all sorted in ascending order. Let's imagine scenario of movies.
 * We have n arrays that are sorted for movie popularity. We want to combine these into single list.
 * The movie may appear in multiple lists. So, we will need to be careful about not duplicating same movie in the final list.
 *
 * Idea:
 * Consider a case for two lists. Then we will create one sorted list.
 * The same list will be compared with other lists to create the final list out of n lists.
 *
 * 1. Take one list as the final result list.
 * 2. Traverse list of lists, starting with second list and combine it with the final result list.
 *      When combining, make sure to maintain previous pointer in order to arrange them in correct order.
 * 3. If value for list1 is less than the value for list2, connect the previous node to list1 and increment list1
 *    else connect previous of list2 and increment list2.
 * 4. Do this until one list reaches `null` value.
 * 5. Merge the non-null list elements to final list and return.
 */


public class CombineMultipleLists {
    /**
     * Merge two lists in ascending order
     * @param list1
     * @param list2
     * @return  resultant lists with elements from both lists. (There may be duplicates)
     */
    public static LinkedListNode mergeLists(LinkedListNode list1, LinkedListNode list2) {
        LinkedListNode temp = new LinkedListNode(-1);
        LinkedListNode prev = temp;
        while (list1 != null && list2 != null) {
            if (list1.data <= list2.data) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        // If list1 is exhausted, append everything left in list2 at the end
        if (list1 == null) {
            prev.next = list2;
        } else {
            prev.next = list1;
        }
        return temp.next;
    }

    /**
     * Merge two sorted lists in ascending order ensuring to remove duplicates.
     * @param list1
     * @param list2
     * @return Unique elements in sorted order.
     */
    public static LinkedListNode mergeListsWithUnique(LinkedListNode list1, LinkedListNode list2) {
        LinkedListNode temp = new LinkedListNode(-1);
        LinkedListNode prev = temp;
        while (list1 != null && list2 != null) {
            if (list1.data < list2.data && prev.data != list1.data) {
                prev.next = list1;
                list1 = list1.next;
            } else if (list1.data > list2.data && prev.data != list2.data) {
                prev.next = list2;
                list2 = list2.next;
            } else {
                prev.next = list1;
                list1 = list1.next;
                list2 = list2.next;
            }
            prev = prev.next;
        }

        if (list1 == null) {
            prev.next = list2;
        } else {
            prev.next = list1;
        }
        return temp.next;
    }

    /**
     * Merge n linkedlists in ascending order
     * @param lists  LinkedLists with elements sorted in ascending order.
     * @return resultant list with elements in ascending order or -1 if input is empty list.
     */
    public static LinkedListNode mergeNLists(List<LinkedListNode> lists) {
        if (lists.size() > 0) {
            // set result list as list (0)
            LinkedListNode result = lists.get(0);

            for (int i = 1; i < lists.size(); i++) {
                result = mergeListsWithUnique(result, lists.get(i));
            }
            return result;
        }
        return new LinkedListNode(-1);
    }

    public static LinkedListNode mergeNListsWithoutDuplicates(List<LinkedListNode> lists) {
        if (lists.size() > 0) {
            LinkedListNode result = lists.get(0);
            for (int i = 1; i < lists.size(); i++) {
                result = mergeListsWithUnique(result, lists.get(i));
            }
            return result;
        }
        return new LinkedListNode(-1);
    }

    public static void main(String[] args) {
        LinkedListNode a = LinkedList.createLinkedList(new int[] {11, 23, 25});
        LinkedListNode b = LinkedList.createLinkedList(new int[] {11, 21, 23});
        LinkedListNode c = LinkedList.createLinkedList(new int[]{11, 11, 41});
        List<LinkedListNode> lists = new ArrayList<>();
        lists.add(a);
        lists.add(b);
        lists.add(c);

        // Because both lists have same head Lists(0), it will show same value.
        LinkedList.display(mergeNLists(lists));
        LinkedList.display(mergeNListsWithoutDuplicates(lists));
    }
}
