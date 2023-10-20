package educative.arrays;

import java.util.*;

/**
 * Anagrams of words. (Group similar words together)
 *
 * If user mistyeps a word but contains same characters in that word, those are anagrams.
 * We want to group such words together
 * For example,
 * <code>
 *     {duel, dule, deul}, {speed, spede}
 * </code>
 *
 * Input:  [ duel, dule, speed, spede, deul, cars ]
 * Output: [ [duel, dule, deul ], [ speed, spede ], [cars] ]
 *
 * To solve this, the idea is to keep track of corresponding character number of occurrences in a string
 * delimited by # pound symbol for example.
 * So, 'abbc' becomes '#1#2#3#0#0#0....#0'. Each string will have total 26 characters to accommodate for 26 alphabets.
 *
 * Time Complexity: O(n * k)
 * Space Complexity: O(n * k)
 */
public class AnagramsJava {
    public static List<List<String>> groupTitles(String[] arr) {
        // If there is no input, return empty list
        if (arr.length == 0)
            return new ArrayList<List<String>>();

        // Create container to keep list of items
        Map<String, List<String>> res = new HashMap<>();

        // Create array to hold number of occurrences of each character.
        int[] count = new int[26];
        for (String s: arr) {
            Arrays.fill(count, 0); // Initialize count to 0 for this string
            for (char c: s.toCharArray()) {
                int index = c - 'a'; // set the index 0 to represent character 'a' and so on.
                count[index]++; // Increase count
            }

            // Create delimited string with count of each character in alphabet
            StringBuilder delimitedStr = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
//                delimitedStr.append('#');
                delimitedStr.append(count[i]);
            }

            String key = delimitedStr.toString(); // key will be delimited string
            // If HashMap doesn't contain this key, just initialize HashMap key with List of String
            if (!res.containsKey(key))
                res.put(key, new ArrayList<String>());
            // Add this string to the HashMap
            res.get(key).add(s);
        }
        return new ArrayList<List<String>>(res.values());
    }

    public static void main(String[] args) {
        String titles[] = {"duel", "dule", "speed", "spede", "cars", "deul"};
        List<List<String>> grouped = groupTitles(titles);
        String query = "deul";

        for (List<String> g: grouped) {
            if (g.contains(query))
                System.out.println(g);
        }

    }
}
