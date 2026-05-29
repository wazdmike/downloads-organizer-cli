package org.example;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args){
        if(args.length == 0){
            System.out.println("Uso: java -jar Main.java");
            return;
        }

        Path path = Path.of(args[0]);

        FileOrganizer organizer = new FileOrganizer();
        organizer.organize(path);
    }
}