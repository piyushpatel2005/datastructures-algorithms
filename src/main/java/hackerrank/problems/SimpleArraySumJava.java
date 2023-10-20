package hackerrank.problems;

import java.util.Arrays;
import java.util.List;

public class SimpleArraySumJava {
    public static int simpleArraySum(List<Integer> ar) {
        int sum = ar.stream().reduce(0, (acc, value) -> acc + value);
        System.out.println(sum);
        return sum;
    }

    public static void main(String[] args) {
        Integer[] numbers = {1,2,3};
        simpleArraySum(Arrays.asList(numbers));
    }
}
