package org.br.downlodsorganizer;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args){
        boolean dryRun = false;

        if(args.length == 0){
            showHelp();
            return;
        }

        for(String arg : args){
            if(arg.equals("--help")){
                showHelp();
                return;
            }
            if(arg.equals("--dry-run")){
                dryRun = true;
            }
        }
        Path path = Path.of(args[0]);
        FileOrganizer organizer = new FileOrganizer(dryRun);
        organizer.organize(path);
    }

    private static void showHelp(){

        System.out.println("""
                Downloads Organizer CLI

                Usage:
                  java -jar .\\target\\downloads-organizer.jar <directory> [options]

                Options:
                  --dry-run    Show what would be moved without changing files
                  --help       Show this help message
                """);
    }
}