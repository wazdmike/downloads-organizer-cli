package org.br.downlodsorganizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileOrganizer {
    private final FileClassifier classifier;
    private final boolean dryRun;
    private final OrganizerSummary summary;

    public FileOrganizer(boolean dryRun){
        this.dryRun = dryRun;
        this.classifier = new FileClassifier();
        this.summary = new OrganizerSummary();
    }
    private void moveFile(Path file){
        try{
            String extension = getExtension(file);
            FileCategory category = classifier.classify(extension);
            Path targetFolder = file.getParent().resolve(category.getFolderName());
            Path targetFile = resolveDuplicate(targetFolder.resolve(file.getFileName()));
            if(dryRun){
                summary.incrementSimulated();
                System.out.println("[DRY RUN] " + file.getFileName() + " -> " + category.getFolderName());
            } else {
                Files.createDirectories(targetFolder);
                Files.move(file, targetFile);
                summary.incrementMoved();
                System.out.println("[OK] " + file.getFileName() + " -> " + category.getFolderName());
            }
        } catch (IOException e){
            summary.incrementError();
            System.out.println("[ERROR] " + file.getFileName() + " - " + e.getMessage());
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
        if (!Files.exists(sourceFolder)){
            System.out.println("Error: source folder doesn't exist: " + sourceFolder);
            return;
        }
        if (!Files.isDirectory(sourceFolder)){
            System.out.println("Error: source folder is not a directory: " + sourceFolder);
            return;
        }
        try (Stream<Path> files = Files.list(sourceFolder)){
            files.filter(Files::isRegularFile).forEach(this::moveFile);
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        summary.print(dryRun);
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
}
