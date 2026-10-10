# FilzaFiles

**A lightweight Java utility library for file operations, file searching, metadata inspection, and text analysis.**

FilzaFiles provides a simple static API for common file-handling and text-processing tasks, built on Java's standard library.

## Features

- File creation, reading, writing, appending, copying, moving, and deletion
- Automatic creation of parent directories for file creation, writing, copying, and moving
- File backups that refuse to overwrite an existing backup
- Recursive file searches by filename or extension
- File metadata inspection
- Word, character, line, sentence, and paragraph counting
- Paragraph splitting and text replacement

## Requirements

- Java 21 or later
- Maven 3.9+ recommended for building from source

## Installation

**Current status:** FilzaFiles is under development. A published Maven dependency will be documented once an artifact has been released to a Maven repository.

To build locally, clone the repository and run:

```bash
mvn clean test
mvn package
```

## Quick Start

```java
import io.github.ashar.filzafiles.FilzaFiles;
import io.github.ashar.filzafiles.TextTools;

public class Example {
    public static void main(String[] args) throws Exception {
        String path = "notes.txt";

        FilzaFiles.write(path, "Hello from FilzaFiles!");
        FilzaFiles.append(path, "\nJava made easier.");

        String content = FilzaFiles.read(path);
        long words = TextTools.wordCount(content);

        System.out.println(content);
        System.out.println("Words: " + words);
    }
}
```

# API Reference

## 1. `FilzaFiles` — File Operations

Import:

```java
import io.github.ashar.filzafiles.FilzaFiles;
```

### `exists(String path)`

Checks whether a path exists.

```java
boolean found = FilzaFiles.exists("notes.txt");
```

**Returns:** `boolean`

### `createFile(String path)`

Creates a new file. Missing parent directories are created automatically. Throws an exception if the file already exists.

```java
FilzaFiles.createFile("data/notes.txt");
```

**Returns:** `void`  
**Throws:** `IOException`

### `createDirectory(String path)`

Creates a directory and any missing parent directories. Existing directories are allowed.

```java
FilzaFiles.createDirectory("data/reports/2026");
```

**Returns:** `void`  
**Throws:** `IOException`

### `read(String path)`

Reads an entire text file using UTF-8.

```java
String content = FilzaFiles.read("notes.txt");
```

**Returns:** `String`  
**Throws:** `IOException`

### `write(String path, String content)`

Creates a file if needed or replaces its existing content. Missing parent directories are created automatically.

```java
FilzaFiles.write("data/notes.txt", "Hello Java!");
```

**Returns:** `void`  
**Throws:** `IOException`

### `append(String path, String content)`

Appends text to the end of a file. Creates the file if it does not exist, provided its parent directory already exists.

```java
FilzaFiles.append("notes.txt", "\nAnother line.");
```

**Returns:** `void`  
**Throws:** `IOException`

### `copy(String source, String destination)`

Copies a file, replacing an existing destination. Missing destination parent directories are created.

```java
FilzaFiles.copy("notes.txt", "backups/notes.txt");
```

**Returns:** `void`  
**Throws:** `IOException`

### `move(String source, String destination)`

Moves or renames a file, replacing an existing destination. Missing destination parent directories are created.

```java
FilzaFiles.move("draft.txt", "archive/draft.txt");
```

**Returns:** `void`  
**Throws:** `IOException`

### `delete(String path)`

Deletes a file or an empty directory. It throws an exception if the path does not exist or cannot be deleted.

```java
FilzaFiles.delete("temporary.txt");
```

**Returns:** `void`  
**Throws:** `IOException`

### `backup(String source)`

Creates a sibling backup named by adding `.bak` to the original filename. Existing backups are not overwritten.

```java
var backupPath = FilzaFiles.backup("notes.txt");
System.out.println(backupPath);
```

For example, `notes.txt` produces `notes.txt.bak`.

**Returns:** `Path`  
**Throws:** `IOException`

## 2. `TextTools` — Text Analysis

Import:

```java
import io.github.ashar.filzafiles.TextTools;
```

### `wordCount(String text)`

Counts whitespace-separated words.

```java
long count = TextTools.wordCount("Java is powerful");
```

**Returns:** `long`

### `wordCountFile(String path)`

Reads a text file and counts its whitespace-separated words.

```java
long count = TextTools.wordCountFile("essay.txt");
```

**Returns:** `long`  
**Throws:** `IOException`

### `characterCount(String text)`

Counts Unicode code points rather than UTF-16 code units. Combining marks may count separately from their base characters.

```java
long count = TextTools.characterCount("Hello");
```

**Returns:** `long`

### `characterCountFile(String path)`

Counts Unicode code points in a text file.

```java
long count = TextTools.characterCountFile("essay.txt");
```

**Returns:** `long`  
**Throws:** `IOException`

### `lineCount(String text)`

Counts lines using Java's `String.lines()` behavior. A trailing line terminator does not create an additional counted line.

```java
long count = TextTools.lineCount("First line\nSecond line");
```

**Returns:** `long`

### `lineCountFile(String path)`

Counts lines in a text file.

```java
long count = TextTools.lineCountFile("essay.txt");
```

**Returns:** `long`  
**Throws:** `IOException`

### `splitParagraphs(String text)`

Splits text at recognized groups of line breaks, trims paragraph boundaries, and excludes empty paragraphs.

```java
var paragraphs = TextTools.splitParagraphs(
    "First paragraph.\n\nSecond paragraph."
);
```

**Returns:** `List<String>`

### `paragraphCount(String text)`

Counts the paragraphs returned by `splitParagraphs()`.

```java
long count = TextTools.paragraphCount(
    "First paragraph.\n\nSecond paragraph."
);
```

**Returns:** `long`

### `paragraphCountFile(String path)`

Counts paragraphs in a text file.

```java
long count = TextTools.paragraphCountFile("essay.txt");
```

**Returns:** `long`  
**Throws:** `IOException`

### `sentenceCount(String text)`

Counts sentence-ending punctuation sequences (`.`, `!`, `?`) followed by whitespace or the end of the string. This is an approximation and may miscount abbreviations or unusual punctuation.

```java
long count = TextTools.sentenceCount(
    "Hello! How are you? I am fine."
);
```

**Returns:** `long`

### `sentenceCountFile(String path)`

Counts sentences in a text file using the same approximation.

```java
long count = TextTools.sentenceCountFile("essay.txt");
```

**Returns:** `long`  
**Throws:** `IOException`

### `replaceAll(String text, String target, String replacement)`

Replaces literal occurrences of a string. This is not a regular-expression replacement.

```java
String updated = TextTools.replaceAll(
    "Hello Java", "Java", "FilzaFiles"
);
```

**Returns:** `String`

### `replaceInFile(String path, String target, String replacement)`

Reads a file, replaces matching literal text, and writes the updated content back to the same file.

```java
TextTools.replaceInFile(
    "notes.txt", "old value", "new value"
);
```

**Returns:** `void`  
**Throws:** `IOException`

**Caution:** This operation overwrites the original file and is not an atomic update. Back up important files before using it.

## 3. `FileSearch` — Recursive File Searching

Import:

```java
import io.github.ashar.filzafiles.FileSearch;
```

### `findByName(String directory, String fileName)`

Recursively finds regular files with a matching filename. Matching is case-insensitive.

```java
var results = FileSearch.findByName(
    "C:/projects", "pom.xml"
);

results.forEach(System.out::println);
```

**Returns:** `List<Path>`  
**Throws:** `IOException`

### `findByExtension(String directory, String extension)`

Recursively finds regular files by extension. Matching is case-insensitive. The extension may include the leading dot.

```java
var results = FileSearch.findByExtension(
    "C:/projects", ".java"
);

results.forEach(System.out::println);
```

**Returns:** `List<Path>`  
**Throws:** `IOException`

Both search methods include nested directories. They do not follow symbolic links by default.

## 4. `FileInfo` — File Metadata

Import:

```java
import io.github.ashar.filzafiles.FileInfo;
```

### `inspect(String filePath)`

Inspects an existing regular file and captures its path, size, and last-modified timestamp.

```java
FileInfo info = FileInfo.inspect("notes.txt");

System.out.println(info.getName());
System.out.println(info.getExtension());
System.out.println(info.getSize());
System.out.println(info.getLastModified());
System.out.println(info.getPath());
```

**Returns:** `FileInfo`  
**Throws:** `IOException` if the path is not a regular file or metadata cannot be read.

Available getters:

| Method | Return type | Description |
|---|---|---|
| `getName()` | `String` | Filename |
| `getExtension()` | `String` | Extension without the dot, or empty string |
| `getSize()` | `long` | File size in bytes |
| `getLastModified()` | `FileTime` | Last-modified timestamp |
| `getPath()` | `Path` | Inspected path |

The metadata represents the values captured during inspection; it is not automatically refreshed if the file changes.

## Error Handling

File operations can fail because of missing files, inaccessible paths, permissions, or other filesystem errors. Methods that declare `IOException` should be called inside appropriate exception handling.

```java
try {
    String content = FilzaFiles.read("notes.txt");
    System.out.println(content);
} catch (java.io.IOException exception) {
    System.err.println("Could not read file: " + exception.getMessage());
}
```

## Build and Test

Run the test suite:

```bash
mvn clean test
```

Build the JAR:

```bash
mvn package
```

The generated artifact is placed in `target/`.

## Project Status

FilzaFiles is an early-stage Java library. The initial release focuses on common file operations, recursive searching, metadata, and basic text analysis.

The API may change before a stable 1.0 release.

## License

See `LICENSE` for licensing terms.
