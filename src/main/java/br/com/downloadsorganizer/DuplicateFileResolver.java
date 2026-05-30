package br.com.downloadsorganizer;

import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicateFileResolver {
    public Path resolve(Path targetFile){
        if (!Files.exists(targetFile)) return targetFile;

        String filename = targetFile.getFileName().toString();
        String name = filename;
        String extension = "";
        int dot = filename.lastIndexOf('.');
        if (dot != -1){
            name = filename.substring(0, dot);
            extension = filename.substring(dot);
        }
        Path parent = targetFile.getParent();
        int counter = 1;
        Path newTarget;

        do {
            String newFileName = name + "-" + counter + extension;
            newTarget = parent.resolve(newFileName);
            counter++;
        } while(Files.exists(newTarget));
        return newTarget;
    }
}
