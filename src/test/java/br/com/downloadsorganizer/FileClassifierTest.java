package br.com.downloadsorganizer;

import org.junit.Test;

import static br.com.downloadsorganizer.FileCategory.*;
import static org.junit.Assert.assertEquals;

public class FileClassifierTest {
    private final FileClassifier fileClassifier = new FileClassifier();

    @Test
    public void shouldClassifyPdf(){
        assertEquals(PDF, fileClassifier.classify("pdf"));
    }
    @Test
    public void shouldClassifyImage(){
        assertEquals(IMG, fileClassifier.classify("png"));
    }
    @Test
    public void shouldClassifyOther(){
        assertEquals(OTHER, fileClassifier.classify("any"));
    }
}
