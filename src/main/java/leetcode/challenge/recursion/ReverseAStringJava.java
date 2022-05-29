package leetcode.challenge.recursion;

/*
Given an input string, return its output string.
For example,
>> reverse("hello")
olleh
 */
public class ReverseAStringJava {
    public static void main(String[] args) {
        String str = "hello";
        System.out.printf("The reverse of %s is %s\n", str, reverse(str));
    }

    public static String reverse(String input) {
        if (input.length() == 1) return input;
        return reverse(input.substring(1)) + input.charAt(0);
    }
}
