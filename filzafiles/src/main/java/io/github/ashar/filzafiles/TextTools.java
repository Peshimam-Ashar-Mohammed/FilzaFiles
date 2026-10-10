
package io.github.ashar.filzafiles;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class TextTools {

    private TextTools() {
    }

    public static long wordCount(String text) {
        String trimmed = text.trim();

        if (trimmed.isEmpty()) {
            return 0;
        }

        return trimmed.split("\\s+").length;
    }

    public static long characterCount(String text) {
        return text.codePointCount(0, text.length());
    }

    public static long lineCount(String text) {
        if (text.isEmpty()) {
            return 0;
        }

        return text.lines().count();
    }

    public static List<String> splitParagraphs(String text) {
        return Arrays.stream(text.split("(?:\\R\\s*){2,}"))
                .map(String::trim)
                .filter(paragraph -> !paragraph.isEmpty())
                .toList();
    }

    public static long paragraphCount(String text) {
        return splitParagraphs(text).size();
    }

    public static long wordCountFile(String path)
            throws IOException {
        return wordCount(FilzaFiles.read(path));
    }

    public static long characterCountFile(String path)
            throws IOException {
        return characterCount(FilzaFiles.read(path));
    }

    public static long lineCountFile(String path)
            throws IOException {
        return lineCount(FilzaFiles.read(path));
    }

    public static long paragraphCountFile(String path)
            throws IOException {
        return paragraphCount(FilzaFiles.read(path));
    }

    public static String replaceAll(
            String text, String target, String replacement) {
        return text.replace(target, replacement);
    }

    public static void replaceInFile(
            String path, String target, String replacement)
            throws IOException {
        String content = FilzaFiles.read(path);
        FilzaFiles.write(
                path,
                replaceAll(content, target, replacement)
        );
    }
}
