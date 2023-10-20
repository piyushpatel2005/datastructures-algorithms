package leetcode.challenge.recursion;

/**
 * Write a recursive function called <code>isPalindrome</code> which returns true if the string
 * is palindrome else false.
 *
 * The string is called palindrome if the input string is equal to reverse of the string.
 *
 * <code>
 *     isPalindrome("awesome") // false
 *     isPalindrome("nayan") // true
 *     isPalindrome("tacocat") // true
 * </code>
 */
public class PalindromeStringRecursiveJava {
    public static void main(String[] args) {
        String input = "awesome";
        System.out.printf("The string %s is palindrome: %b\n\n", input, isPalindrome(input));

        input = "nayan";
        System.out.printf("The string %s is palindrome: %b\n\n", input, isPalindrome(input));

        input = "tacocat";
        System.out.printf("The string %s is palindrome: %b\n\n", input, isPalindrome(input));
    }

    public static boolean isPalindrome(String str) {
//        System.out.println("The function is called with input : " + str);
        if (str.length() < 2) return true;
        return isPalindrome(str.substring(1, str.length() - 1)) && (str.charAt(0) == str.charAt(str.length() - 1));
    }

    public static boolean isPalindrome2(String str) {
        if (str.length() < 2)
            return true;
        if (str.charAt(0) == str.charAt(str.length() - 1))
            return isPalindrome(str.substring(1, str.length() - 1));
        return false;
    }
}
