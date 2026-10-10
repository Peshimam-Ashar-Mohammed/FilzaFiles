
package io.github.ashar.filzafiles;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FilzaFilesTest {

    @TempDir
    Path tempDir;

    @Test
    void writesAndReadsFile() throws Exception {
        Path file = tempDir.resolve("notes.txt");

        FilzaFiles.write(file.toString(), "Hello Java");

        assertEquals("Hello Java", FilzaFiles.read(file.toString()));
    }

    @Test
    void appendsTextToFile() throws Exception {
        Path file = tempDir.resolve("notes.txt");

        FilzaFiles.write(file.toString(), "Hello ");
        FilzaFiles.append(file.toString(), "Ashar");

        assertEquals("Hello Ashar", FilzaFiles.read(file.toString()));
    }

    @Test
    void detectsExistingFile() throws Exception {
        Path file = tempDir.resolve("notes.txt");

        assertFalse(FilzaFiles.exists(file.toString()));

        FilzaFiles.write(file.toString(), "Test");

        assertTrue(FilzaFiles.exists(file.toString()));
    }

    @Test
    void countsWordsCorrectly() {
        assertEquals(4, TextTools.wordCount("Java makes life easier"));
        assertEquals(0, TextTools.wordCount("   "));
    }

    @Test
    void countsCharactersCorrectly() {
        assertEquals(5, TextTools.characterCount("Hello"));
    }

    @Test
    void countsLinesCorrectly() {
        assertEquals(2, TextTools.lineCount("First line\nSecond line"));
    }

    @Test
    void splitsParagraphsCorrectly() {
        String text = "First paragraph.\n\nSecond paragraph.";

        assertEquals(2, TextTools.paragraphCount(text));
    }

    @Test
    void replacesTextInString() {
        assertEquals(
                "Hello Filza",
                TextTools.replaceAll("Hello Java", "Java", "Filza")
        );
    }

    @Test
    void replacesTextInFile() throws Exception {
        Path file = tempDir.resolve("notes.txt");

        FilzaFiles.write(file.toString(), "Hello Java");

        TextTools.replaceInFile(
                file.toString(), "Java", "Filza"
        );

        assertEquals("Hello Filza", FilzaFiles.read(file.toString()));
    }


    @Test
    void findsFilesByNameRecursively() throws Exception {
        Path nested = tempDir.resolve("subfolder");
        Files.createDirectories(nested);

        FilzaFiles.write(
                tempDir.resolve("notes.txt").toString(),
                "Root file"
        );

        FilzaFiles.write(
                nested.resolve("notes.txt").toString(),
                "Nested file"
        );

        var results = FileSearch.findByName(
                tempDir.toString(), "notes.txt"
        );

        assertEquals(2, results.size());
    }

    @Test
    void findsFilesByExtensionRecursively() throws Exception {
        Path nested = tempDir.resolve("java");
        Files.createDirectories(nested);

        FilzaFiles.write(
                nested.resolve("Main.java").toString(),
                "class Main {}"
        );

        FilzaFiles.write(
                nested.resolve("notes.txt").toString(),
                "Some text"
        );

        var results = FileSearch.findByExtension(
                tempDir.toString(), ".java"
        );

        assertEquals(1, results.size());
        assertEquals("Main.java",
                results.get(0).getFileName().toString());
    }


    
    @Test
    void inspectsFileMetadata() throws Exception {
        Path file = tempDir.resolve("report.txt");
        FilzaFiles.write(file.toString(), "Hello");

        FileInfo info = FileInfo.inspect(file.toString());

        assertEquals("report.txt", info.getName());
        assertEquals("txt", info.getExtension());
        assertEquals(5, info.getSize());
        assertNotNull(info.getLastModified());
    }

    @Test
    void rejectsDirectoriesAsFiles() throws Exception {
        Path directory = tempDir.resolve("folder");
        Files.createDirectories(directory);

        assertThrows(
                java.io.IOException.class,
                () -> FileInfo.inspect(directory.toString())
        );
    }

        
    @Test
    void countsSentencesCorrectly() {
        assertEquals(
                3,
                TextTools.sentenceCount(
                        "Hello! How are you? I am fine."
                )
        );

        assertEquals(0, TextTools.sentenceCount("   "));
    }





}
