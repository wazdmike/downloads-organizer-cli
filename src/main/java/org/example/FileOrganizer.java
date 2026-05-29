package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class FileOrganizer {
    private void moveFile(Path file){
        try{
            String extension = getExtension(file);
            String folder = switch(extension) {
                case "pdf" -> "pdf";
                case "png", "jpg", "jpeg", "gif", "webp" -> "img";
                case "zip", "rar", "7z" -> "zip";
                case "txt", "doc", "docx", "md" -> "doc";
                case "java", "c", "py", "js", "ts", "html", "css", "json" -> "code";
                default -> "other";
            };
            Path targetFolder = file.getParent().resolve(folder);
            Files.createDirectories(targetFolder);
            Path targetFile = targetFolder.resolve(file.getFileName());
            Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("[OK] " + file.getFileName() + " -> " + folder);
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
