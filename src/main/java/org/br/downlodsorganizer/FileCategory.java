package org.br.downlodsorganizer;

public enum FileCategory {

    PDF("pdf"),
    IMG("img"),
    ZIP("zip"),
    DOC("doc"),
    CODE("code"),
    OTHER("other");

    private final String folderName;

    FileCategory(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }
}
