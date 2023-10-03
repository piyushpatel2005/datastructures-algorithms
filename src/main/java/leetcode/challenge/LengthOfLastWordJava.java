package leetcode.challenge;


/*
Leetcode Problem 58: Length of Last Word
Given a string s consisting of some words separated by some number of spaces, return the length of the last word in the string.

A word is a maximal substring consisting of non-space characters only.



Example 1:

Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.
Example 2:

Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.
Example 3:

Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.


Constraints:

1 <= s.length <= 104
s consists of only English letters and spaces ' '.
There will be at least one word in s.
 */
public class LengthOfLastWordJava {
    public static int lengthOfLastWord(String s) {
        int count = 0;
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        while (i >= 0 && s.charAt(i) != ' ') {
            i--;
            count++;
        }
        System.out.printf("Length of last word in string '%s' is %d\n", s, count);
        return count;
    }

    public static void main(String[] args) {
        lengthOfLastWord("   fly me   to   the moon  ");
        lengthOfLastWord("luffy is still joyboy");
        lengthOfLastWord("    ");
    }
}
