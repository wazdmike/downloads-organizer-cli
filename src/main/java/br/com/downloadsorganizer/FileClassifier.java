package br.com.downloadsorganizer;

import static br.com.downloadsorganizer.FileCategory.*;
import static br.com.downloadsorganizer.FileCategory.CODE;
import static br.com.downloadsorganizer.FileCategory.DOC;
import static br.com.downloadsorganizer.FileCategory.OTHER;

public class FileClassifier {
    public FileCategory classify(String extension){
        return switch(extension){
            case "pdf" -> PDF;
            case "png", "jpg", "jpeg", "gif", "webp" -> IMG;
            case "zip", "rar", "7z" -> ZIP;
            case "txt", "doc", "docx", "md" -> DOC;
            case "java", "c", "py", "js", "ts", "html", "css", "json" -> CODE;
            default -> OTHER;
        };
    }
}
