package org.br.downlodsorganizer;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args){
        boolean dryRun = false;
        Path sourcePath = null;

        for(String arg : args){
            switch (arg){
                case "--dry-run" -> dryRun = true;
                case "--help" -> {
                    showHelp();
                    return;
                }
                default -> {
                    if(!arg.startsWith("--")){
                        sourcePath = Path.of(arg);
                    }
                }
            }
        }
        if(sourcePath == null){
            System.out.println("Error: source folder not provided");
            showHelp();
            return;
        }
        FileOrganizer organizer = new FileOrganizer(dryRun);
        organizer.organize(sourcePath);
    }

    private static void showHelp(){

        System.out.println("""
                Downloads Organizer CLI

                Usage:
                  java -jar .\\target\\downloads-organizer.jar [directory] [options]

                Options:
                  --dry-run    Show what would be moved without changing files
                  --help       Show this help message
                """);
    }
}