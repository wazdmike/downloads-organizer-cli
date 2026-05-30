package br.com.downloadsorganizer;

public class OrganizerSummary {
    private int movedCount;
    private int simulatedCount;
    private int errorCount;

    public void incrementMoved(){
        movedCount++;
    }
    public void incrementSimulated(){
        simulatedCount++;
    }
    public void incrementError(){
        errorCount++;
    }
    public void print(boolean dryRun){
        System.out.println();
        System.out.println("Summary:");
        System.out.println("Moved: " + movedCount);
        System.out.println("Simulated: " + simulatedCount);
        System.out.println("Errors: " + errorCount);
        System.out.println("Dry run: " + dryRun);
    }
}
