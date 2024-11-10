package store.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileLoader {
    public static BufferedReader loadFile(Path filePath) throws IOException {
        return Files.newBufferedReader(filePath);
    }
}
