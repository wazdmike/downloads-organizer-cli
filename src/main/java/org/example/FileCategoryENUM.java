package org.example;

public enum FileCategoryENUM {

    PDF("pdf"),
    IMG("img"),
    ZIP("zip"),
    DOC("doc"),
    CODE("code"),
    OTHER("other");

    private final String folderName;

    FileCategoryENUM(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }
}
