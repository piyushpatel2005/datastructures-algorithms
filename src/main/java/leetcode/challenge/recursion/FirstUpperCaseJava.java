package leetcode.challenge.recursion;

/**
 * Given a string, find its first uppercase letter.
 *
 * firstUpperCase("hellO worlD") // O
 */
public class FirstUpperCaseJava {
    public static void main(String[] args) {
        String input = "helLo woRLd";
        System.out.printf("The first upper case letter of word %s is %c\n", input, firstUpperCase(input));
    }

    public static char firstUpperCase(String input) {
        if (input.isEmpty())
            return ' ';
        if (Character.isUpperCase(input.charAt(0)))
            return input.charAt(0);
        return firstUpperCase(input.substring(1));
    }
}
