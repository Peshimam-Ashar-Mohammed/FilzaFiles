
package io.github.ashar.filzafiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public final class FilzaFiles {

    private FilzaFiles() {
    }

    public static boolean exists(String path) {
        return Files.exists(Path.of(path));
    }

    public static void createFile(String path) throws IOException {
        Path file = Path.of(path);

        if (file.getParent() != null) {
            Files.createDirectories(file.getParent());
        }

        Files.createFile(file);
    }

    public static void createDirectory(String path) throws IOException {
        Files.createDirectories(Path.of(path));
    }

    public static String read(String path) throws IOException {
        return Files.readString(
                Path.of(path),
                StandardCharsets.UTF_8
        );
    }

    public static void write(String path, String content)
            throws IOException {
        Path file = Path.of(path);

        if (file.getParent() != null) {
            Files.createDirectories(file.getParent());
        }

        Files.writeString(file, content, StandardCharsets.UTF_8);
    }

    public static void append(String path, String content)
            throws IOException {
        Files.writeString(
                Path.of(path),
                content,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }


    public static void copy(String source, String destination)
            throws IOException {
        Path sourcePath = Path.of(source);
        Path destinationPath = Path.of(destination);

        if (destinationPath.getParent() != null) {
            Files.createDirectories(destinationPath.getParent());
        }

        Files.copy(
                sourcePath,
                destinationPath,
                StandardCopyOption.REPLACE_EXISTING
        );
    }

    public static void move(String source, String destination)
            throws IOException {
        Path sourcePath = Path.of(source);
        Path destinationPath = Path.of(destination);

        if (destinationPath.getParent() != null) {
            Files.createDirectories(destinationPath.getParent());
        }

        Files.move(
                sourcePath,
                destinationPath,
                StandardCopyOption.REPLACE_EXISTING
        );
    }


    public static void delete(String path) throws IOException {
        Files.delete(Path.of(path));
    }


    
    public static Path backup(String source) throws IOException {
        Path original = Path.of(source);

        if (!Files.isRegularFile(original)) {
            throw new IOException("Not a regular file: " + original);
        }

        Path backup = original.resolveSibling(
                original.getFileName().toString() + ".bak"
        );

        return Files.copy(original, backup);
    }


}
    