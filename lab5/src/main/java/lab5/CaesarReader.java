package lab5;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class CaesarReader extends FilterReader {
    private final char key;

    public CaesarReader(Reader in, char key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return c == -1 ? -1 : (char) (c - key);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int n = super.read(cbuf, off, len);
        for (int i = off; i < off + n; i++) {
            cbuf[i] = (char) (cbuf[i] - key);
        }
        return n;
    }
}
