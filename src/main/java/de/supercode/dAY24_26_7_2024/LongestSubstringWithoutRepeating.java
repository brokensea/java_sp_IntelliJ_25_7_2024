package de.supercode.dAY24_26_7_2024;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeating {
    public static void main(String[] args) {
        System.out.println(getLongestSubstringWithoutRepeating("abcabcbb")); //  abc
        System.out.println(getLongestSubstringWithoutRepeating("bbbbb")); // : b
        System.out.println(getLongestSubstringWithoutRepeating("pwwkew")); // : wke

    }

    public static String getLongestSubstringWithoutRepeating(String checkString) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int maxLength = 0;
        int maxStart = 0;

        for (int end = 0; end < checkString.length(); end++) {
            char currentChar = checkString.charAt(end);
            // If the character is already in the map and the index is within the current window
            if (map.containsKey(currentChar) && map.get(currentChar) >= start) {
                // Move the start to the right of the previous occurrence
                start = map.get(currentChar) + 1;
            }
            // Update the character's latest position
            map.put(currentChar, end);
            // Update the maximum length and start position if a longer substring is found
            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                maxStart = start;
            }
        }
        // Return the longest substring without repeating characters
        return checkString.substring(maxStart, maxStart + maxLength);

    }

//    public static String getLongestSubstringWithoutRepeating(String checkString) {
//        Map<Character, Integer> map = new HashMap<>();
//        String noRepeationg = "";
//        int start = 0;
//        int end = 0;
//        int maxLength = 0;
//        char[] checkStringCharArray = checkString.toCharArray();
//        for (char c : checkStringCharArray) {
//            if(map.containsKey(c)&& map.get(c)>0){
//                start = map.getindex+1
//                        map.put(c,1);
//            }
//            maxLength = end - start +1;
//        }
//
//        return null;
//
//    }

}
