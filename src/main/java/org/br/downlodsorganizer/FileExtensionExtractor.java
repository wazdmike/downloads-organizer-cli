package org.br.downlodsorganizer;

import java.nio.file.Path;

public class FileExtensionExtractor {
    public String extract(Path file){
        String name = file.getFileName().toString();
        int dot = name.lastIndexOf('.');
        if(dot == -1 || dot == name.length() -1) return "";
        return name.substring(dot + 1).toLowerCase();
    }
}
