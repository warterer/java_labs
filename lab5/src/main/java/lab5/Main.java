package lab5;

import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String inputFile = "input.txt";
    private static final String encryptedFile = "encrypted.txt";
    private static final String decryptedFile = "decrypted.txt";
    private static final char key = 'k';
    private static final String url = "https://github.com/";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== I/O Streams ===");
            System.out.println("1. Рядок з максимальною кількістю слів у " + inputFile);
            System.out.println("2. Зашифрувати " + inputFile + " в " + encryptedFile);
            System.out.println("3. Розшифрувати " + encryptedFile + " в " + decryptedFile);
            System.out.println("4. Частота тегів на " + url);
            System.out.println("0. Вихід");
            System.out.print("Ваш вибір: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> {
                        String line = FileService.findLineWithMostWords(inputFile);
                        System.out.println(line == null ? "Файл порожній." : "Рядок: " + line);
                    }
                    case "2" -> {
                        FileService.encrypt(inputFile, encryptedFile, key);
                        System.out.println(encryptedFile);
                    }
                    case "3" -> {
                        FileService.decrypt(encryptedFile, decryptedFile, key);
                        System.out.println(decryptedFile);
                    }
                    case "4" -> {
                        Map<String, Integer> freq = TagCounter.count(FileService.downloadPage(url));
                        System.out.println("\n-- За назвою --");
                        TagCounter.printByName(freq);
                        System.out.println("\n-- За частотою --");
                        TagCounter.printByFrequency(freq);
                    }
                    case "0" -> { return; }
                    default -> System.out.println("Невідома команда.");
                }
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }
}
