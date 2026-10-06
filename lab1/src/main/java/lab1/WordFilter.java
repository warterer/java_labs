package lab1;

import java.util.ArrayList;
import java.util.List;

public class WordFilter {

    private static final String VOWELS = "aeiouAEIOU";

    public static String[] findBalancedLatinWords(String text) {
        List<String> result = new ArrayList<>();
        if (text == null || text.isBlank()) {
            return new String[0];
        }
        for (String word : text.trim().split("\\s+")) {
            if (isLatinOnly(word) && hasEqual(word)) {
                result.add(word);
            }
        }
        return result.toArray(new String[0]);
    }

    private static boolean isLatinOnly(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            boolean isLatin = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
            if (!isLatin) {
                return false;
            }
        }
        return !word.isEmpty();
    }

    private static boolean hasEqual(String word) {
        int vowels = 0;
        int consonants = 0;
        for (int i = 0; i < word.length(); i++) {
            if (VOWELS.indexOf(word.charAt(i)) >= 0) {
                vowels++;
            } else {
                consonants++;
            }
        }
        return vowels == consonants;
    }
}
