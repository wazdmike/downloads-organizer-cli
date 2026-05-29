# Downloads Organizer CLI

Simple Java CLI tool to organize files by extension.

## Features

* Organizes files automatically
* Creates folders if they do not exist
* Separates files by category
* Uses Java NIO API (`java.nio.file`)
* Unit tests with JUnit

## Current Categories

| Folder | Extensions                                                  |
| ------ | ----------------------------------------------------------- |
| pdf    | `.pdf`                                                      |
| img    | `.png`, `.jpg`, `.jpeg`, `.gif`, `.webp`                    |
| zip    | `.zip`, `.rar`, `.7z`                                       |
| doc    | `.txt`, `.doc`, `.docx`, `.md`                              |
| code   | `.java`, `.c`, `.py` `.js`, `.ts`, `.html`, `.css`, `.json` |
| other  | unknown extensions                                          |

---

## Project Structure

```txt
downloads-organizer/
├── src/
├── README.md
└── pom.xml
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
java -jar ./target/downloads-organizer-cli-1.0-SNAPSHOT.jar ~/Downloads
```

### Linux with dry run

```bash
java -jar ./target/downloads-organizer-cli-1.0-SNAPSHOT.jar ~/Downloads --dry-run
```

### Windows PowerShell

```powershell
java -jar .\target\downloads-organizer-cli-1.0-SNAPSHOT.jar "$env:USERPROFILE\Downloads"
```

### Windows PowerShell with dry run

```powershell
java -jar .\target\downloads-organizer-cli-1.0-SNAPSHOT.jar "$env:USERPROFILE\Downloads" --dry-run
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

