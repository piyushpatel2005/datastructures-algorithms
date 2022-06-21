package leetcode.lessons.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Bucket Sort
 * Create bucket and distribute elements of array into buckets.
 * Sort buckets individually and merge buckets after sorting.
 *
 * Number of buckets is decided based on round(sqrt(number of elements))
 * Appropriate bucket for elements = ceil ( value * no. of buckets / maxValue)
 *
 * This is good when we have input uniformly distributed over a range.
 * but should be avoided when we have space constraint.
 */
public class BucketSortJava {
    public void bucketSort(int[] arr) {
        int numberOfBuckets = (int) Math.ceil(Math.sqrt(arr.length));
        int maxValue = Integer.MIN_VALUE;
        // find max value
        for (int value: arr) {
            if (value > maxValue)
                maxValue = value;
        }
        // create buckets
        ArrayList<Integer>[] buckets = new ArrayList[numberOfBuckets];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        for (int value: arr) {
            int bucketNumber = (int) Math.ceil(((float) value * numberOfBuckets) / maxValue);
            buckets[bucketNumber - 1].add(value);
        }
        System.out.println("\n\nPrinting bcukets after inserting");
        printBucket(buckets);

        for (ArrayList<Integer> bucket: buckets) {
            Collections.sort(bucket);
        }

        System.out.println("\n\nPrinting buckets after sorting");
        printBucket(buckets);

        int index = 0;
        for (ArrayList<Integer> bucket: buckets) {
            for (int value: bucket) {
                arr[index++] = value;
            }
        }
    }

    private void printBucket(ArrayList<Integer>[] buckets) {
        for (int i = 0; i < buckets.length; i++) {
            System.out.println("\nBucket# " + i + ": ");
            for(int j = 0; j < buckets[i].size(); j++) {
                System.out.print(buckets[i].get(j) + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BucketSortJava sorter = new BucketSortJava();
        int[] arr = {3,6, 4, 2, 7, 8, 4, 1};
        System.out.println(Arrays.toString(arr));
        sorter.bucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
