package leetcode.challenge.recursion;

public class DecimalToBinaryJava {
    public static void main(String[] args) {
        int decimal = 13;
        System.out.printf("The binary of %d is %s OR %d", decimal, decimalToBinary("", decimal), decimalToBinary(decimal));
    }

    /*
    | Number | Quotient | Remainder |
    +--------+----------+-----------+
    | 13 / 2 | 6        |  1        |
    | 6 / 2  | 3        |  0        |
    | 3 / 2  | 1        |  1        |
    | 1 / 2  | 0        |  1        |
     */
    public static String decimalToBinary(String binary, int number) {
        int quotient = number / 2;
        int remainder = number % 2;
        if (quotient == 0) return remainder + binary;
        return decimalToBinary(Integer.toString(remainder), quotient) + binary;
    }

    public static int decimalToBinary(int number) {
        if (number == 0) return 0;
        return (number % 2) + (10 * decimalToBinary(number / 2));
    }
}
