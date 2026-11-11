package lab5;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagCounter {
    private static final Pattern TAG = Pattern.compile("<\\s*([a-zA-Z][a-zA-Z0-9-]*)");

    public static Map<String, Integer> count(String html) {
        Map<String, Integer> freq = new HashMap<>();
        Matcher m = TAG.matcher(html);
        while (m.find()) {
            freq.merge(m.group(1).toLowerCase(), 1, Integer::sum);
        }
        return freq;
    }

    public static void printByName(Map<String, Integer> freq) {
        new TreeMap<>(freq).forEach((tag, n) -> System.out.println(tag + " - " + n));
    }

    public static void printByFrequency(Map<String, Integer> freq) {
        freq.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .thenComparing(Map.Entry.comparingByKey()))
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));
    }
}
