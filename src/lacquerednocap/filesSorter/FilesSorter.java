package lacquerednocap.filesSorter;

import lacquerednocap.commandLineParser.Helper;
import lacquerednocap.settings.Settings;

import java.io.IOException;

public class FilesSorter {
    private final Settings settings;

    public FilesSorter(Settings settings) {
        this.settings = settings;
    }

    public void mergeSortFiles() {
        FileSplitter fileSplitter = new FileSplitter(settings);

        FilesMerger filesMerger = new FilesMerger(settings);

        try {
            filesMerger.mergeFiles(fileSplitter.splitFiles());
        } catch (IOException e) {
            System.out.println(e.getMessage());

            Helper.getHelpCommand();
        }
    }
}