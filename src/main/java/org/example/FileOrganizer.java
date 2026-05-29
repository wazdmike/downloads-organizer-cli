package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import static org.example.FileCategoryENUM.*;

public class FileOrganizer {
    private final boolean dryRun;
    public FileOrganizer(boolean dryRun){
        this.dryRun = dryRun;
    }
    private void moveFile(Path file){
        try{
            String extension = getExtension(file);
            FileCategoryENUM category = switch(extension) {
                case "pdf" -> PDF;
                case "png", "jpg", "jpeg", "gif", "webp" -> IMG;
                case "zip", "rar", "7z" -> ZIP;
                case "txt", "doc", "docx", "md" -> DOC;
                case "java", "c", "py", "js", "ts", "html", "css", "json" -> CODE;
                default -> OTHER;
            };
            Path targetFolder = file.getParent().resolve(category.getFolderName());
            Path targetFile = targetFolder.resolve(file.getFileName());
            if(dryRun){
                System.out.println("[DRY RUN] " + file.getFileName() + " -> " + category.getFolderName());
            } else {
                Files.createDirectories(targetFolder);
                Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("[OK] " + file.getFileName() + " -> " + category.getFolderName());
            }
        } catch (IOException e){
            System.out.println("[ERRO] " + file.getFileName());
        }
    }

    private String getExtension(Path file){
        String name = file.getFileName().toString();
        int extension = name.lastIndexOf('.');
        if(extension == -1){
            return "";
        }
        return name.substring(extension+1).toLowerCase();
    }


    public void organize(Path sourceFolder){
        try (Stream<Path> files = Files.list(sourceFolder)){
            files.filter(Files::isRegularFile).forEach(this::moveFile);
        } catch (IOException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
