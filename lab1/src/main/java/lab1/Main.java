package lab1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] testCases = {
                "book code java test unit algorithm queue",
                "hello world test1234 java привіт desk",
                "area song hero echo nice noon кава"
        };

        for (int i = 0; i < testCases.length; i++) {
            String line = testCases[i];
            String[] words = WordFilter.findBalancedLatinWords(line);

            System.out.println("Test " + (i + 1) + ": " + line);
            System.out.println("Result: " + Arrays.toString(words));
            System.out.println("----------------------------------------");
        }
    }
}
