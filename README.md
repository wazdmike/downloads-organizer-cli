# Downloads Organizer CLI

Simple Java CLI tool to organize files by extension.

## Features

* Organizes files automatically
* Creates folders if they do not exist
* Separates files by category
* Uses Java NIO API (`java.nio.file`)

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
git clone <repository-url>
```

Build the project:

```bash
mvn clean install
```

Run the application passing the target directory.

### Linux

```bash
java -jar organizer.jar ~/Downloads
```

### Windows

```powershell
java -jar organizer.jar "%USERPROFILE%\Downloads"
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
