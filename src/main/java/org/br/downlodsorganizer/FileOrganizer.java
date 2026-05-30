package org.br.downlodsorganizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileOrganizer {
    private final FileClassifier classifier;
    private final boolean dryRun;
    private final OrganizerSummary summary;
    final FileExtensionExtractor extensionExtractor;
    final DuplicatedFileResolver duplicatedResolver;

    public FileOrganizer(boolean dryRun){
        this.dryRun = dryRun;
        this.classifier = new FileClassifier();
        this.summary = new OrganizerSummary();
        this.extensionExtractor = new FileExtensionExtractor();
        this.duplicatedResolver = new DuplicatedFileResolver();
    }
    private void moveFile(Path file){
        try{
            String extension = extensionExtractor.extract(file);
            FileCategory category = classifier.classify(extension);
            Path targetFolder = file.getParent().resolve(category.getFolderName());
            Path targetFile = duplicatedResolver.resolve(targetFolder.resolve(file.getFileName()));
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
}
