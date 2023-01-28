package leetcode.challenge.beginners;

import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode Problem 383: Ransom Note
 * <p>
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * <p>
 * Each letter in magazine can only be used once in ransomNote.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * <p>
 * Example 2:
 * <p>
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * <p>
 * Example 3:
 * <p>
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote and magazine consist of lowercase English letters.
 */
public class RandomNote {

    /**
     * Approach 1: For each item in magazine, find if it has the ransomNote chracter.
     * If it's there, remove it else if not found return false.
     * Iterate through all characters of ransomNote to find if they exist in magazine.
     * <p>
     * Time Complexity: O(m * n) where m= length of magazine and n = length of ransomNote
     * Space complexity: O(m), we are building new magazine string as strings are immutable
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        for (char c : ransomNote.toCharArray()) {
            int index = magazine.indexOf(c);
            if (index == -1) {
                return false;
            }
            magazine = magazine.substring(0, index) + magazine.substring(index + 1);
        }
        return true;
    }

    /**
     * Approach 2: Create HashMap of character and their count in each of those words.
     * Then loop over hashmap of ransomNote to see if those are available in magazine.
     * HashMap gives result in O(1), So, effectively we are reducing the Time complexity.
     * Time Complexity: O(m)
     * Space Complexity: O(k) / O(1), k is unique number of characters in both. cannot be more than 26
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        Map<Character, Integer> ransomNoteCounts = makeCountsMap(ransomNote);
        Map<Character, Integer> magazineCounts = makeCountsMap(magazine);

        for (char c : ransomNoteCounts.keySet()) {
            int countInMagazine = magazineCounts.getOrDefault(c, 0);
            int countInRansomNote = ransomNoteCounts.get(c);
            if (countInRansomNote > countInMagazine) {
                return false;
            }
        }
        return true;
    }

    /**
     * Approach 3: One HashMap
     * In this case create hashmap of magazine characters.
     * For each character in ransomNote, see if it exists in magazineCount and if the count is greater than 0.
     * Reduce count by 1. If it doesn't exists, then return false.
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct3(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        Map<Character, Integer> magazineCounts = makeCountsMap(magazine);

        for (char c : ransomNote.toCharArray()) {
            int countInMagazine = magazineCounts.getOrDefault(c, 0);
            if (countInMagazine == 0) {
                return false;
            }
            magazineCounts.put(c, countInMagazine - 1);
        }
        return true;
    }

    private Map<Character, Integer> makeCountsMap(String str) {
        Map<Character, Integer> countsMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            int currentCount = countsMap.getOrDefault(c, 0);
            countsMap.put(c, currentCount + 1);
        }
        return countsMap;
    }
}
