package leetcode.challenge.beginners;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode Challenge 412: Fizz Buzz
 *
 * Given an integer n, return a string array answer (1-indexed) where:
 *
 *     answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
 *     answer[i] == "Fizz" if i is divisible by 3.
 *     answer[i] == "Buzz" if i is divisible by 5.
 *     answer[i] == i (as a string) if none of the above conditions are true.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["1","2","Fizz"]
 *
 * Example 2:
 *
 * Input: n = 5
 * Output: ["1","2","Fizz","4","Buzz"]
 *
 * Example 3:
 *
 * Input: n = 15
 * Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
 *
 */
public class FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> fizzBuzz = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                fizzBuzz.add("FizzBuzz");
            } else if (i % 3 == 0) {
                fizzBuzz.add("Fizz");
            } else if (i % 5 == 0) {
                fizzBuzz.add("Buzz");
            } else {
                fizzBuzz.add(Integer.toString(i));
            }
        }
        return fizzBuzz;
    }

    // This approach helps us add more conditions like if number divisible by 7, add Jaaz.
    // We can easily achieve that by adding one more if condition in below code.
    public List<String> fizzBuzz2(int n) {
        List<String> fizzBuzz = new ArrayList<>(n);
        String output = "";
        for (int i = 1; i <= n; i++) {
            boolean divisibleBy3 = i % 3 == 0;
            boolean divisibleBy5 = i % 5 == 0;
//            boolean divisibleBy7 = i % 7 == 0;
            if (divisibleBy3)
                output += "Fizz";
            if (divisibleBy5)
                output += "Buzz";
//            if (divisibleBy7)
//                output += "Jaaz";
            if (output.isEmpty())
                output += String.valueOf(i);
            fizzBuzz.add(output);
        }
        return fizzBuzz;
    }
}
