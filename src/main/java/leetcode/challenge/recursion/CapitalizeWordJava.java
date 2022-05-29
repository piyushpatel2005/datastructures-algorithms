package leetcode.challenge.recursion;

/**
 * Implement a function that capitalizes each word in string.
 *
 * capitalize("i am indian") // I Am Indian
 */
public class CapitalizeWordJava {
    public static void main(String[] args) {
        String input = "i am indian";
        System.out.printf("The input string '%s' capitalized becomes '%s'\n",input, capitalize(input));
    }

    public static String capitalize(String input) {
        int size = input.length();
        if (input.isEmpty())
            return input;
        char lastChar = input.charAt(size - 1);
        if (size == 1)
            return Character.toString(Character.toUpperCase(lastChar));
        if (input.substring(size - 2, size - 1).equals(" "))
            lastChar = Character.toUpperCase(lastChar);
        return capitalize(input.substring(0, size - 1)) + Character.toString(lastChar);

    }
}
