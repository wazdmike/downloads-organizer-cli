package br.com.downloadsorganizer;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class FileOrganizerTest {

    @Test
    public void shouldNotMoveFilesWhenDryRun() throws IOException{
        FileOrganizer organizer = new FileOrganizer(true);
        Path tempDir = Files.createTempDirectory("organizer-temp");
        Path file = tempDir.resolve("document.pdf");
        Files.createFile(file);
        organizer.organize(tempDir);
        assertTrue(Files.exists(file));
        assertFalse(Files.exists(tempDir.resolve("pdf").resolve("document.pdf")));
    }

    @Test
    public void shouldOrganizeFileByExtension() throws IOException{
        FileOrganizer organizer = new FileOrganizer(false);
        Path tempDir = Files.createTempDirectory("organizer-temp");
        Path file = tempDir.resolve("document.pdf");
        Files.createFile(file);
        organizer.organize(tempDir);
        Path movedFile = tempDir.resolve("pdf").resolve("document.pdf");
        assertFalse(Files.exists(file));
        assertTrue(Files.exists(movedFile));
    }

    @Test
    public void shouldRenameDuplicateFiles()
            throws IOException {

        FileOrganizer organizer = new FileOrganizer(false);

        Path tempDir = Files.createTempDirectory("organizer-temp");
        Path original = tempDir.resolve("document.pdf");
        Path duplicate = tempDir.resolve("document-1.pdf");
        Files.createFile(original);
        Files.createFile(duplicate);
        organizer.organize(tempDir);
        Path firstFile = tempDir.resolve("pdf").resolve("document.pdf");
        Path secondFile = tempDir.resolve("pdf").resolve("document-1.pdf");

        assertTrue(Files.exists(firstFile));
        assertTrue(Files.exists(secondFile));
    }
}
