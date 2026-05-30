package br.com.downloadsorganizer;

import org.junit.Test;
import java.nio.file.Path;
import static org.junit.Assert.*;

public class FileExtensionExtractorTest {
    private final FileExtensionExtractor extractor = new FileExtensionExtractor();

    @Test
    public void shouldExtractPdfExtension(){
        String extension = extractor.extract(Path.of("file.pdf"));
        assertEquals("pdf", extension);
    }

    @Test
    public void shouldReturnEmptyWhenHasNoExtension(){
        String extension = extractor.extract(Path.of("file"));
        assertEquals("", extension);
    }

    @Test
    public void shouldHandleUppercaseExtension(){
        String extension = extractor.extract(Path.of("FILE.PNG"));
        assertEquals("png", extension);
    }
}
