package org.br.downlodsorganizer;

import static org.br.downlodsorganizer.FileCategory.*;
import static org.br.downlodsorganizer.FileCategory.CODE;
import static org.br.downlodsorganizer.FileCategory.DOC;
import static org.br.downlodsorganizer.FileCategory.OTHER;

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
