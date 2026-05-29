package org.example;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args){
        boolean dryRun = false;
        for(String arg: args){
            if (arg.equals("--dry-run")) {
                dryRun = true;
                break;
            }
        }

        if(args.length == 0){
            System.out.println("Use: java -jar Main.java");
            return;
        }

        Path path = Path.of(args[0]);

        FileOrganizer organizer = new FileOrganizer(dryRun);
        organizer.organize(path);
    }
}