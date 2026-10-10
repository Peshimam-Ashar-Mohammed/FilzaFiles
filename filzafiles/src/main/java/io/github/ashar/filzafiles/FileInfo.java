
package io.github.ashar.filzafiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Objects;

public final class FileInfo {

    private final Path path;
    private final long size;
    private final FileTime lastModified;

    private FileInfo(Path path, long size, FileTime lastModified) {
        this.path = path;
        this.size = size;
        this.lastModified = lastModified;
    }

    public static FileInfo inspect(String filePath) throws IOException {
        Path path = Path.of(filePath);

        if (!Files.isRegularFile(path)) {
            throw new IOException("Not a regular file: " + path);
        }

        return new FileInfo(
                path,
                Files.size(path),
                Files.getLastModifiedTime(path)
        );
    }

    public String getName() {
        return path.getFileName().toString();
    }

    public String getExtension() {
        String name = getName();
        int dot = name.lastIndexOf('.');

        if (dot <= 0 || dot == name.length() - 1) {
            return "";
        }

        return name.substring(dot + 1);
    }

    public long getSize() {
        return size;
    }

    public FileTime getLastModified() {
        return lastModified;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "name='" + getName() + '\'' +
                ", extension='" + getExtension() + '\'' +
                ", size=" + size +
                ", lastModified=" + lastModified +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof FileInfo other)) return false;

        return size == other.size
                && path.equals(other.path)
                && lastModified.equals(other.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, size, lastModified);
    }
}
