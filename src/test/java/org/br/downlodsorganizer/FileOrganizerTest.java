package org.br.downlodsorganizer;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class FileOrganizerTest {
    private final FileOrganizer organizer = new FileOrganizer(false);

    @Test
    public void shouldReturnPdfExtension(){
        Path file = Path.of("document.pdf");
        String extension = organizer.extensionExtractor.extract(file);

        assertEquals("pdf", extension);
    }

    @Test
    public void shouldReturnEmptyExtension(){
        Path file = Path.of("document");
        String extension = organizer.extensionExtractor.extract(file);

        assertEquals("", extension);
    }

    @Test
    public void shouldResolveDuplicateFilename() throws IOException{
        FileOrganizer organizer = new FileOrganizer(false);
        Path tempDir = Files.createTempDirectory("organizer-temp");
        Path existingFile = tempDir.resolve("photo.png");
        Files.createFile(existingFile);
        Path resolvedPath = organizer.duplicatedResolver.resolve(existingFile);
        assertEquals("photo-1.png", resolvedPath.getFileName().toString());
    }

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
}
