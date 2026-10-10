# FilzaFiles

### A Lightweight and Simplified Java Library for File Operations and Text Processing

FilzaFiles is a Java utility library designed to simplify common file-handling, file-searching, metadata-inspection, and text-processing tasks.

Instead of repeatedly writing boilerplate code for everyday file operations, developers can use FilzaFiles' reusable methods in their own Java applications.

[![Java](https://img.shields.io/badge/Java-21%2B-orange)](https://www.oracle.com/java/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](filzafiles/LICENSE)
[![Release](https://img.shields.io/badge/Release-v0.1.0-blue)](https://github.com/Peshimam-Ashar-Mohammed/FilzaFiles/releases/tag/v0.1.0)

**Current version:** `0.1.0`

**Java requirement:** Java 21 or later

**License:** MIT

**Status:** Initial release

---

## Table of Contents

- [Why FilzaFiles?](#why-filzafiles)
- [Features](#features)
- [Download](#download)
- [Requirements](#requirements)
- [Integration Guide](#integration-guide)
  - [Option 1: Plain Java Project](#option-1-plain-java-project-no-maven)
  - [Option 2: Maven Project](#option-2-maven-project)
  - [Option 3: Build from Source](#option-3-build-from-source)
- [Quick Start](#quick-start)
- [API Documentation](#api-documentation)
  - [FilzaFiles](#1-filzafiles-file-operations)
  - [TextTools](#2-texttools-text-processing)
  - [FileSearch](#3-filesearch-recursive-file-search)
  - [FileInfo](#4-fileinfo-file-metadata)
- [Exception Handling](#exception-handling)
- [Important Behavior and Limitations](#important-behavior-and-limitations)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)

---

## Why FilzaFiles?

Java already provides powerful file-handling APIs, but developers often need to combine multiple operations to accomplish common tasks.

FilzaFiles provides a reusable layer of utility methods for tasks such as:

- Creating files and directories.
- Reading, writing, and appending text.
- Copying, moving, deleting, and backing up files.
- Searching directories recursively.
- Inspecting file metadata.
- Counting words, characters, lines, paragraphs, and approximate sentences.
- Replacing literal text in strings and files.

The goal is to make common operations easier to reuse while keeping the API small and straightforward.

## Features

### File Operations

- Check whether a file or directory exists.
- Create files and directories.
- Read UTF-8 text files.
- Write and append text.
- Copy and move files.
- Delete files or empty directories.
- Create `.bak` backup copies.

### Text Processing

- Count whitespace-separated words.
- Count Unicode code points.
- Count lines.
- Split and count paragraphs.
- Estimate sentence counts.
- Replace literal text in strings and files.

### File Search

- Search recursively by filename.
- Search recursively by extension.
- Match filenames and extensions without case sensitivity.

### File Metadata

- Retrieve a filename.
- Retrieve a file extension.
- Retrieve file size in bytes.
- Retrieve the last-modified timestamp.
- Retrieve the inspected path.

---

## Download

### FilzaFiles v0.1.0

**[Download the latest release](https://github.com/Peshimam-Ashar-Mohammed/FilzaFiles/releases/latest)**

Direct JAR download:

**[filzafiles-0.1.0.jar](https://github.com/Peshimam-Ashar-Mohammed/FilzaFiles/releases/download/v0.1.0/filzafiles-0.1.0.jar)**

Source code:

[GitHub Repository](https://github.com/Peshimam-Ashar-Mohammed/FilzaFiles)

### Maven distribution status

FilzaFiles is **not yet published to Maven Central**. Until public Maven distribution is configured, developers can download the JAR and use it directly or install it into their local Maven repository.

---

## Requirements

- Java Development Kit (JDK) 21 or later.
- A Java IDE or text editor, such as IntelliJ IDEA, Eclipse, or Visual Studio Code.
- Maven only if you want to build the source code or use the Maven integration option.

Verify Java:

```bash
java -version
javac -version
```

Verify Maven if installed:

```bash
mvn -version
```

---

# Integration Guide

Choose the integration method that matches your project.

## Option 1: Plain Java Project (No Maven)

This method works with a regular Java project that uses `javac` and `java`.

### Step 1: Download the JAR

Download:

[filzafiles-0.1.0.jar](https://github.com/Peshimam-Ashar-Mohammed/FilzaFiles/releases/download/v0.1.0/filzafiles-0.1.0.jar)

### Step 2: Create the project structure

Create a project with this structure:

```text
MyJavaApp/
├── lib/
│   └── filzafiles-0.1.0.jar
├── src/
│   └── Example.java
└── out/
```

Place the downloaded JAR inside the `lib` directory.

Create `Example.java` inside `src`.

### Step 3: Write the Java code

```java
import io.github.ashar.filzafiles.FilzaFiles;
import io.github.ashar.filzafiles.TextTools;
import io.github.ashar.filzafiles.FileInfo;

import java.io.IOException;

public class Example {

    public static void main(String[] args) throws IOException {

        String path = "notes.txt";

        // Create the file and write some text.
        FilzaFiles.write(path, "Hello from FilzaFiles!");

        // Append additional text.
        FilzaFiles.append(path, "\nJava development made easier.");

        // Read the complete file.
        String content = FilzaFiles.read(path);

        System.out.println("File content:");
        System.out.println(content);

        // Analyze the text.
        System.out.println("Word count: "
                + TextTools.wordCount(content));

        System.out.println("Character count: "
                + TextTools.characterCount(content));

        System.out.println("Line count: "
                + TextTools.lineCount(content));

        // Inspect file metadata.
        FileInfo info = FileInfo.inspect(path);

        System.out.println("Filename: " + info.getName());
        System.out.println("Extension: " + info.getExtension());
        System.out.println("Size: " + info.getSize() + " bytes");
    }
}
```

### Step 4: Compile the application on Windows

Open Command Prompt in the `MyJavaApp` directory.

```bat
javac -cp "lib\filzafiles-0.1.0.jar" -d out src\Example.java
```

### Step 5: Run the application on Windows

```bat
java -cp "out;lib\filzafiles-0.1.0.jar" Example
```

The program creates `notes.txt`, writes and appends text, analyzes its contents, and prints metadata.

### Linux and macOS commands

Compile:

```bash
javac -cp "lib/filzafiles-0.1.0.jar" -d out src/Example.java
```

Run:

```bash
java -cp "out:lib/filzafiles-0.1.0.jar" Example
```

**Important:** Windows separates classpath entries with a semicolon (`;`). Linux and macOS use a colon (`:`).

You do not need to extract the JAR.

---

## Option 2: Maven Project

Because FilzaFiles is not yet on Maven Central, you must first install the downloaded JAR into your local Maven repository.

### Step 1: Download the JAR

Download [filzafiles-0.1.0.jar](https://github.com/Peshimam-Ashar-Mohammed/FilzaFiles/releases/download/v0.1.0/filzafiles-0.1.0.jar).

### Step 2: Install it into your local Maven repository

Replace the example path with the actual location of your downloaded JAR.

**Windows Command Prompt:**

```bat
mvn install:install-file -Dfile="C:\path\to\filzafiles-0.1.0.jar" -DgroupId=io.github.ashar -DartifactId=filzafiles -Dversion=0.1.0 -Dpackaging=jar
```

**Linux or macOS:**

```bash
mvn install:install-file -Dfile="/path/to/filzafiles-0.1.0.jar" -DgroupId=io.github.ashar -DartifactId=filzafiles -Dversion=0.1.0 -Dpackaging=jar
```

This installs the library into your local Maven repository, usually under:

```text
~/.m2/repository/io/github/ashar/filzafiles/0.1.0/
```

On Windows, `~` refers to your Windows user directory.

**Note:** A local Maven installation is available only on that computer unless the artifact is separately published or distributed.

### Step 3: Add the dependency

Open your application's `pom.xml`.

Inside the existing `<dependencies>` element, add:

```xml
<dependency>
    <groupId>io.github.ashar</groupId>
    <artifactId>filzafiles</artifactId>
    <version>0.1.0</version>
</dependency>
```

Do not create a second `<dependencies>` element if one already exists.

### Step 4: Import the library

In your Java source file:

```java
import io.github.ashar.filzafiles.FilzaFiles;
import io.github.ashar.filzafiles.TextTools;
import io.github.ashar.filzafiles.FileSearch;
import io.github.ashar.filzafiles.FileInfo;
```

Import only the classes you need.

### Step 5: Build the project

From the directory containing `pom.xml`, run:

```bash
mvn clean package
```

Maven will resolve FilzaFiles from your local repository.

---

## Option 3: Build from Source

Clone the repository:

```bash
git clone https://github.com/Peshimam-Ashar-Mohammed/FilzaFiles.git
```

Enter the Maven project directory:

```bash
cd FilzaFiles/filzafiles
```

Run the test suite and build the project:

```bash
mvn clean verify
```

To install the built library into your local Maven repository:

```bash
mvn install
```

The generated JAR is located at:

```text
target/filzafiles-0.1.0.jar
```

---

# Quick Start

The following example demonstrates several common operations.

```java
import io.github.ashar.filzafiles.FilzaFiles;
import io.github.ashar.filzafiles.TextTools;
import io.github.ashar.filzafiles.FileSearch;
import io.github.ashar.filzafiles.FileInfo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class QuickStart {

    public static void main(String[] args) throws IOException {

        String file = "data/notes.txt";

        // Write content and create missing parent directories.
        FilzaFiles.write(
                file,
                "Java makes development easier.\nFilzaFiles simplifies file handling."
        );

        // Read the file.
        String content = FilzaFiles.read(file);
        System.out.println(content);

        // Count words.
        System.out.println("Words: "
                + TextTools.wordCount(content));

        // Count lines.
        System.out.println("Lines: "
                + TextTools.lineCount(content));

        // Replace literal text in the file.
        TextTools.replaceInFile(
                file,
                "development",
                "software development"
        );

        // Inspect file information.
        FileInfo info = FileInfo.inspect(file);
        System.out.println("Size: " + info.getSize() + " bytes");

        // Search for a file by name.
        List<Path> matches =
                FileSearch.findByName("data", "notes.txt");

        matches.forEach(System.out::println);

        // Create a backup alongside the original file.
        Path backup = FilzaFiles.backup(file);
        System.out.println("Backup: " + backup);
    }
}
```

---

# API Documentation

All classes use the package:

```java
io.github.ashar.filzafiles
```

## 1. `FilzaFiles` — File Operations

Import:

```java
import io.github.ashar.filzafiles.FilzaFiles;
```

### `exists(String path)`

Checks whether a file or directory exists.

```java
boolean present = FilzaFiles.exists("notes.txt");
System.out.println(present);
```

**Returns:** `boolean`

### `createFile(String path)`

Creates a new file and missing parent directories.

```java
FilzaFiles.createFile("data/documents/notes.txt");
```

**Throws:** `IOException` if the file already exists or creation fails.

### `createDirectory(String path)`

Creates a directory and missing parent directories.

```java
FilzaFiles.createDirectory("data/documents");
```

**Throws:** `IOException` if directory creation fails.

### `read(String path)`

Reads a complete UTF-8 text file into a string.

```java
String content = FilzaFiles.read("notes.txt");
System.out.println(content);
```

**Returns:** `String`

**Throws:** `IOException` if reading fails.

### `write(String path, String content)`

Creates a file or replaces its existing content. Missing parent directories are created automatically.

```java
FilzaFiles.write("notes.txt", "Hello, Java!");
```

**Throws:** `IOException` if writing fails.

**Warning:** Existing content is overwritten.

### `append(String path, String content)`

Appends content to a file. If the file does not exist, it is created.

```java
FilzaFiles.append("notes.txt", "\nAnother line.");
```

**Throws:** `IOException` if appending fails.

**Note:** The parent directory must already exist.

### `copy(String source, String destination)`

Copies a file to a destination, replacing an existing destination if necessary. Missing destination parent directories are created.

```java
FilzaFiles.copy(
        "notes.txt",
        "backup/notes-copy.txt"
);
```

**Throws:** `IOException` if copying fails.

### `move(String source, String destination)`

Moves a file to a destination, replacing an existing destination if necessary. Missing destination parent directories are created.

```java
FilzaFiles.move(
        "notes.txt",
        "archive/notes.txt"
);
```

**Throws:** `IOException` if moving fails.

### `delete(String path)`

Deletes a file or an empty directory.

```java
FilzaFiles.delete("notes.txt");
```

**Throws:** `IOException` if the target does not exist or deletion fails.

### `backup(String source)`

Creates a backup alongside the original file, with `.bak` appended to its filename.

```java
Path backup = FilzaFiles.backup("notes.txt");
System.out.println(backup);
```

For example, `notes.txt` produces `notes.txt.bak`.

**Returns:** `Path` to the backup.

**Throws:** `IOException` if the source is not a regular file, copying fails, or the backup already exists.

**Note:** Existing backups are not overwritten.

---

## 2. `TextTools` — Text Processing

Import:

```java
import io.github.ashar.filzafiles.TextTools;
```

### `wordCount(String text)`

Counts whitespace-separated words.

```java
long words = TextTools.wordCount(
        "Java makes file handling simple"
);
System.out.println(words); // 5
```

**Returns:** `long`

Whitespace-only text produces zero words.

### `wordCountFile(String path)`

Counts whitespace-separated words in a file.

```java
long words = TextTools.wordCountFile("notes.txt");
```

**Returns:** `long`

**Throws:** `IOException` if the file cannot be read.

### `characterCount(String text)`

Counts Unicode code points rather than UTF-16 code units.

```java
long count = TextTools.characterCount("A😀B");
System.out.println(count); // 3
```

**Returns:** `long`

Some visually combined characters, such as a letter plus a combining accent, can count as multiple code points.

### `characterCountFile(String path)`

Counts Unicode code points in a file.

```java
long count = TextTools.characterCountFile("notes.txt");
```

**Returns:** `long`

**Throws:** `IOException` if reading fails.

### `lineCount(String text)`

Counts lines using Java's `String.lines()` behavior.

```java
long lines = TextTools.lineCount(
        "First line\nSecond line\nThird line"
);
System.out.println(lines); // 3
```

**Returns:** `long`

An empty string produces zero lines. A final line terminator does not create an additional trailing line.

### `lineCountFile(String path)`

Counts lines in a text file.

```java
long lines = TextTools.lineCountFile("notes.txt");
```

**Returns:** `long`

**Throws:** `IOException` if reading fails.

### `splitParagraphs(String text)`

Splits text at groups of line breaks, trims paragraph boundaries, and excludes empty paragraphs.

```java
var paragraphs = TextTools.splitParagraphs(
        "First paragraph.\n\nSecond paragraph."
);

paragraphs.forEach(System.out::println);
```

**Returns:** `List<String>`

### `paragraphCount(String text)`

Counts paragraphs.

```java
long paragraphs = TextTools.paragraphCount(
        "First paragraph.\n\nSecond paragraph."
);
System.out.println(paragraphs); // 2
```

**Returns:** `long`

### `paragraphCountFile(String path)`

Counts paragraphs in a file.

```java
long paragraphs =
        TextTools.paragraphCountFile("article.txt");
```

**Returns:** `long`

**Throws:** `IOException` if reading fails.

### `sentenceCount(String text)`

Estimates the number of sentences using common terminal punctuation (`.`, `!`, and `?`) followed by whitespace or the end of the text.

```java
long sentences = TextTools.sentenceCount(
        "Hello there! How are you? I am fine."
);
System.out.println(sentences); // 3
```

**Returns:** `long`

**Important:** This is an approximation, not a complete linguistic sentence parser. Abbreviations, decimal numbers, and unusual punctuation can affect the result.

### `sentenceCountFile(String path)`

Estimates sentence count in a text file.

```java
long sentences =
        TextTools.sentenceCountFile("article.txt");
```

**Returns:** `long`

**Throws:** `IOException` if reading fails.

### `replaceAll(String text, String target, String replacement)`

Replaces literal occurrences of a target string.

```java
String result = TextTools.replaceAll(
        "Java is useful. Java is popular.",
        "Java",
        "FilzaFiles"
);

System.out.println(result);
```

This method uses literal string replacement, not regular expressions.

### `replaceInFile(String path, String target, String replacement)`

Replaces literal text in a file.

```java
TextTools.replaceInFile(
        "notes.txt",
        "old value",
        "new value"
);
```

**Throws:** `IOException` if reading or writing fails.

**Caution:** The original file is rewritten directly. This operation does not guarantee an atomic update. Back up important files before replacing content.

---

## 3. `FileSearch` — Recursive File Search

Import:

```java
import io.github.ashar.filzafiles.FileSearch;
import java.nio.file.Path;
import java.util.List;
```

### `findByName(String directory, String fileName)`

Searches recursively for regular files whose filenames match the requested name, ignoring case.

```java
List<Path> results = FileSearch.findByName(
        "documents",
        "notes.txt"
);

results.forEach(System.out::println);
```

**Returns:** `List<Path>`

**Throws:** `IOException` if the directory cannot be searched.

### `findByExtension(String directory, String extension)`

Searches recursively for regular files matching a file extension, ignoring case.

```java
List<Path> results = FileSearch.findByExtension(
        "documents",
        ".txt"
);

results.forEach(System.out::println);
```

The extension may be supplied with or without the leading dot.

**Returns:** `List<Path>`

**Throws:** `IOException` if the directory cannot be searched.

**Note:** The search does not follow symbolic links by default.

---

## 4. `FileInfo` — File Metadata

Import:

```java
import io.github.ashar.filzafiles.FileInfo;
```

### `inspect(String filePath)`

Inspects a regular file and captures its metadata.

```java
FileInfo info = FileInfo.inspect("notes.txt");

System.out.println("Name: " + info.getName());
System.out.println("Extension: " + info.getExtension());
System.out.println("Size: " + info.getSize());
System.out.println("Last modified: " + info.getLastModified());
System.out.println("Path: " + info.getPath());
```

**Returns:** `FileInfo`

**Throws:** `IOException` if the path is not a regular file or its metadata cannot be read.

### Metadata methods

| Method | Return type | Description |
|---|---|---|
| `getName()` | `String` | Returns the filename. |
| `getExtension()` | `String` | Returns the extension without the dot, or an empty string. |
| `getSize()` | `long` | Returns file size in bytes. |
| `getLastModified()` | `FileTime` | Returns the last-modified timestamp. |
| `getPath()` | `Path` | Returns the inspected path. |
| `toString()` | `String` | Returns a string representation of the metadata. |

`FileInfo` is a snapshot: if the file changes after inspection, the existing object does not automatically refresh.

---

# Exception Handling

Most file operations can throw `IOException`. Handle errors where appropriate rather than assuming every operation will succeed.

```java
import io.github.ashar.filzafiles.FilzaFiles;
import java.io.IOException;

public class SafeRead {

    public static void main(String[] args) {
        try {
            String content = FilzaFiles.read("notes.txt");
            System.out.println(content);
        } catch (IOException exception) {
            System.err.println(
                    "Could not read file: "
                    + exception.getMessage()
            );
        }
    }
}
```

Common causes of I/O errors include:

- A file does not exist.
- The application lacks filesystem permissions.
- A destination cannot be created.
- A file is locked or inaccessible.
- The requested path is invalid for the environment.

---

# Important Behavior and Limitations

- **Java version:** FilzaFiles v0.1.0 targets Java 21.
- **Text encoding:** File text operations use UTF-8.
- **Word counting:** Words are separated by whitespace; the library does not perform language-aware tokenization.
- **Character counting:** Unicode code points are counted, not grapheme clusters or displayed symbols.
- **Sentence counting:** Sentence counts are estimates and may be inaccurate for abbreviations or unusual punctuation.
- **Memory usage:** File-based text-analysis methods read file content into memory. They may not be appropriate for extremely large files.
- **Replacement safety:** `replaceInFile()` directly rewrites the original file; it is not guaranteed to be atomic.
- **Backup behavior:** `backup()` refuses to overwrite an existing `.bak` file.
- **Search behavior:** Recursive searches do not follow symbolic links by default.
- **Metadata:** `FileInfo` stores a snapshot and does not automatically refresh.
- **Maven distribution:** The library is not yet available from Maven Central; the Maven workflow above installs it locally.

---

# Testing

Run the library's automated test suite from the `filzafiles/` directory:

```bash
mvn clean verify
```

The project's development checks included:

- **19 library unit tests passed** during local verification.
- **22 separate Windows integration checks passed**, with zero failures.
- A standalone Java application successfully used the compiled JAR.

Integration checks covered file creation, existence, UTF-8 reading and writing, appending, text analysis, text replacement, copying, moving, deletion, backups, metadata inspection, recursive searching, empty-file handling, and overwrite behavior.

These results represent the tested operations in the current Windows environment; they do not guarantee exhaustive edge-case or cross-platform coverage.

---

# Project Structure

The repository is organized as follows:

```text
FilzaFiles/
├── README.md
├── .gitignore
└── filzafiles/
    ├── pom.xml
    ├── README.md
    ├── LICENSE
    └── src/
        ├── main/
        │   └── java/
        │       └── io/github/ashar/filzafiles/
        │           ├── FilzaFiles.java
        │           ├── TextTools.java
        │           ├── FileSearch.java
        │           └── FileInfo.java
        └── test/
            └── java/
                └── io/github/ashar/filzafiles/
                    └── FilzaFilesTest.java
```

---

# Roadmap

Potential future improvements include:

- Additional file utilities and safer replacement workflows.
- More comprehensive edge-case testing.
- Streaming support for large text files.
- More detailed examples and usage patterns.
- Public Maven repository distribution.
- Expanded documentation and cross-platform testing.

These are possible future improvements, not promises about a specific release date.

---

# Contributing

Contributions, bug reports, and suggestions are welcome.

1. Fork the repository.
2. Create a branch for your change.
3. Make the change and add tests where appropriate.
4. Run `mvn clean verify` from the `filzafiles/` directory.
5. Open a pull request describing the change.

Please include steps to reproduce a bug and the expected behavior.

---

# License

FilzaFiles is released under the **MIT License**.

See [LICENSE](filzafiles/LICENSE) for the full license text.

---

## Links

- **[Download FilzaFiles v0.1.0](https://github.com/Peshimam-Ashar-Mohammed/FilzaFiles/releases/tag/v0.1.0)**
- [Direct JAR download](https://github.com/Peshimam-Ashar-Mohammed/FilzaFiles/releases/download/v0.1.0/filzafiles-0.1.0.jar)
- [Source repository](https://github.com/Peshimam-Ashar-Mohammed/FilzaFiles)
- [Issues and bug reports](https://github.com/Peshimam-Ashar-Mohammed/FilzaFiles/issues)

**FilzaFiles — less boilerplate, more building.**
