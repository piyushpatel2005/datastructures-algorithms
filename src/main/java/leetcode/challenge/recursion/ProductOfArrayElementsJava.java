package leetcode.challenge.recursion;

/**
 * Given an array of integer elements <code>arr</code> and its size <code>sizeOfArr</code>, return the product of all of its elements.
 */
public class ProductOfArrayElementsJava {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5};
        int size = 4;
        System.out.println("The sum of elements of " + arr + " with size " + 4 + " is " + productOfArrayElements(arr,size));
    }

    /**
     *
     * @param arr array of integer elements
     * @param sizeOfArr size of array
     * @return product of all elements
     */
    public static int productOfArrayElements(int[] arr, int sizeOfArr) {
        if (sizeOfArr <= 0) return 1;
        return arr[sizeOfArr - 1] * productOfArrayElements(arr, sizeOfArr - 1);
    }
}
