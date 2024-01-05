package datastructures.lists;

import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        verifyArrayList();
        System.out.println("==================================================");
        verifySinglyLinkedList();
    }

    private static void verifyArrayList() {
        List<Integer> list = new ArrayList<>();
        System.out.println(list + " has size of " + list.size());
        list.add(11);
        list.add(12);
        list.add(13);
        System.out.println(list + " has size of " + list.size());

        list.remove(1);
        System.out.println("After removing from idnex 1: " + list + " has size of " + list.size());
        System.out.println("List contains 11? " + list.contains(11));
        System.out.println("List.indexOf(13): " + list.indexOf(13));
        System.out.println("List.indexOf(100): " + list.indexOf(100));
        System.out.println("Adding 11, 13");
        list.add(11);
        list.add(13);
        System.out.println(list.toString());
        System.out.println("List.lastIndexOf(11): " + list.lastIndexOf(11));
    }

    private static void verifySinglyLinkedList() {
        List<Integer> list = new ArrayList<>();
        System.out.println(list + " has size of " + list.size());
        list.add(11);
        list.add(12);
        list.add(13);
        System.out.println(list + " has size of " + list.size());

        list.remove(1);
        System.out.println("After removing from idnex 1: " + list + " has size of " + list.size());
        System.out.println("List contains 11? " + list.contains(11));
        System.out.println("List.indexOf(13): " + list.indexOf(13));
        System.out.println("List.indexOf(100): " + list.indexOf(100));
        System.out.println("Adding 11, 13");
        list.add(11);
        list.add(13);
        System.out.println(list.toString());
        System.out.println("List.lastIndexOf(11): " + list.lastIndexOf(11));
    }
}
