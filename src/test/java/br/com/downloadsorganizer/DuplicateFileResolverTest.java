package br.com.downloadsorganizer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;

public class DuplicateFileResolverTest {
    @Rule
    public TemporaryFolder folderTemp = new TemporaryFolder();

    private final DuplicateFileResolver duplicateFileResolver = new DuplicateFileResolver();

    @Test
    public void shouldCreateNewNameWhenFileAlreadyExists() throws IOException{
        Path folder = folderTemp.newFolder().toPath();
        Path existingFile = folder.resolve("test.txt");
        Files.createFile(existingFile);
        Path resolved = duplicateFileResolver.resolve(existingFile);
        assertTrue(resolved.getFileName().toString().contains("test-1.txt"));
    }
}
