package datastructures.array;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] numbers = new int[5];
        System.out.println(System.identityHashCode(numbers));
        System.out.println(System.identityHashCode(numbers[1]));
    }
}
