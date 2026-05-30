package org.br.downlodsorganizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileOrganizer {
    private final FileClassifier classifier;
    private final boolean dryRun;
    private int movedCount;
    private int simulatedCount;
    private int errorCount;

    public FileOrganizer(boolean dryRun){
        this.dryRun = dryRun;
        this.classifier = new FileClassifier();
    }
    private void moveFile(Path file){
        try{
            String extension = getExtension(file);
            FileCategory category = classifier.classify(extension);
            Path targetFolder = file.getParent().resolve(category.getFolderName());
            Path targetFile = resolveDuplicate(targetFolder.resolve(file.getFileName()));
            if(dryRun){
                simulatedCount++;
                System.out.println("[DRY RUN] " + file.getFileName() + " -> " + category.getFolderName());
            } else {
                Files.createDirectories(targetFolder);
                Files.move(file, targetFile);
                movedCount++;
                System.out.println("[OK] " + file.getFileName() + " -> " + category.getFolderName());
            }
        } catch (IOException e){
            errorCount++;
            System.out.println("[ERROR] " + file.getFileName());
        }
    }

    public String getExtension(Path file){
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
            System.out.println("Error: " + e.getMessage());
        }
        printSummary();
    }

    public Path resolveDuplicate(Path targetFile){
        if(!Files.exists(targetFile)){
            return targetFile;
        }
        String filename = targetFile.getFileName().toString();
        String name = filename;
        String extension = "";
        int dot = filename.lastIndexOf(".");
        if(dot != -1){
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

    private void printSummary(){
        System.out.println();
        System.out.println("Summary:");
        System.out.println("Moved: " + movedCount);
        System.out.println("Simulated: " + simulatedCount);
        System.out.println("Errors: " + errorCount);
        System.out.println("Dry run: " + dryRun);
    }
}
