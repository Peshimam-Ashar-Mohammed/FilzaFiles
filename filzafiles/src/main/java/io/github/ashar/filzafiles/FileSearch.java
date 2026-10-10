
package io.github.ashar.filzafiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public final class FileSearch {

    private FileSearch() {
    }

    public static List<Path> findByName(
            String directory, String fileName) throws IOException {

        Path root = Path.of(directory);

        try (Stream<Path> paths = Files.walk(root)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName()
                            .toString().equalsIgnoreCase(fileName))
                    .toList();
        }
    }

    public static List<Path> findByExtension(
            String directory, String extension) throws IOException {

        Path root = Path.of(directory);

        String normalizedExtension = extension.startsWith(".")
                ? extension.substring(1)
                : extension;

        normalizedExtension =
                normalizedExtension.toLowerCase(Locale.ROOT);

        final String ext = normalizedExtension;

        try (Stream<Path> paths = Files.walk(root)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> {
                        String name = path.getFileName()
                                .toString()
                                .toLowerCase(Locale.ROOT);

                        return name.endsWith("." + ext);
                    })
                    .toList();
        }
    }
}
