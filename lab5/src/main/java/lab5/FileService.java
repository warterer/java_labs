package lab5;

import java.io.*;
import java.net.URI;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class FileService {
    public static String findLineWithMostWords(String path) throws IOException {
        String best = null;
        int max = -1;
        try (BufferedReader r = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = r.readLine()) != null) {
                int count = countWords(line);
                if (count > max) {
                    max = count;
                    best = line;
                }
            }
        }
        return best;
    }

    private static int countWords(String line) {
        String t = line.trim();
        return t.isEmpty() ? 0 : t.split("\\s+").length;
    }

    public static void encrypt(String inPath, String outPath, char key) throws IOException {
        try (Reader in = new FileReader(inPath);
             Writer out = new CaesarWriter(new FileWriter(outPath), key)) {
            in.transferTo(out);
        }
    }

    public static void decrypt(String inPath, String outPath, char key) throws IOException {
        try (Reader in = new CaesarReader(new FileReader(inPath), key);
             Writer out = new FileWriter(outPath)) {
            in.transferTo(out);
        }
    }

    public static String downloadPage(String url) throws IOException {
        URLConnection conn = URI.create(url).toURL().openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        try (InputStream in = conn.getInputStream()) {
            return new String(in.readAllBytes());
        }
    }
}
