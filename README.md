![Java CI](https://github.com/wazdmike/downloads-organizer-cli/actions/workflows/maven.yml/badge.svg)
# Downloads Organizer CLI

Simple Java CLI tool to organize files by extension.

## Features

* Organizes files automatically
* Creates folders if they do not exist
* Separates files by category
* Uses Java NIO API (`java.nio.file`)
* Unit tests with JUnit

## Current Categories

| Folder | Extensions                                                   |
| ------ |--------------------------------------------------------------|
| pdf    | `.pdf`                                                       |
| img    | `.png`, `.jpg`, `.jpeg`, `.gif`, `.webp`                     |
| zip    | `.zip`, `.rar`, `.7z`                                        |
| doc    | `.txt`, `.doc`, `.docx`, `.md`                               |
| code   | `.java`, `.c`, `.py`, `.js`, `.ts`, `.html`, `.css`, `.json` |
| other  | unknown extensions                                           |

---

## Project Structure

```txt
src/
├── main/java/br/com/downloadsorganizer/
│   ├── DuplicateFileResolver.java
│   ├── FileCategory.java
│   ├── FileClassifier.java
│   ├── FileExtensionExtractor.java
│   ├── FileOrganizer.java
│   ├── Main.java
│   └── OrganizerSummary.java
│
├── test/java/br/com/downloadsorganizer/
│   ├── DuplicateFileResolverTest.java
│   ├── FileClassifierTest.java
│   ├── FileExtensionExtractorTest.java
│   └── FileOrganizerTest.java 
```

---

## Technologies

* Java 21
* Maven
* Java NIO

---

## How to Run

Clone the repository:

```bash
git clone https://github.com/wazdmike/downloads-organizer-cli.git
```

Build the project:

```bash
mvn clean package
```

Run the application passing the target directory.

### Linux

```bash
java -jar ./target/downloads-organizer.jar ~/Downloads
```

### Linux with dry run

```bash
java -jar ./target/downloads-organizer.jar ~/Downloads --dry-run
```

### Windows PowerShell

```powershell
java -jar .\target\downloads-organizer.jar "$env:USERPROFILE\Downloads"
```

### Windows PowerShell with dry run

```powershell
java -jar .\targe\tdownloads-organizer.jar "$env:USERPROFILE\Downloads" --dry-run
```


---

## Example

Before:

```txt
Downloads/
├── image.png
├── file.pdf
├── code.java
├── archive.zip
```

After:

```txt
Downloads/
├── img/
│   └── image.png
├── pdf/
│   └── file.pdf
├── code/
│   └── code.java
├── zip/
│   └── archive.zip
```

## Running Tests

Run all tests with Maven:

```bash
mvn test
```

Example output:

```txt
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
```
## Help

Show all available options:

```bash id="p1w91f"
java -jar ./target/downloads-organizer.jar --help
```

Output:

```txt id="4xib5x"
Downloads Organizer CLI

Usage:
  java -jar downloads-organizer.jar <directory> [options]

Options:
  --dry-run    Show what would be moved without changing files
  --help       Show this help message
```
