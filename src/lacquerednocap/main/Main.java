package lacquerednocap.main;

import lacquerednocap.commandLineParser.CommandLineParser;
import lacquerednocap.commandLineParser.CommandLineParserImp;
import lacquerednocap.filesSorter.FilesSorter;
import lacquerednocap.settings.Settings;

public class Main {
    public static void main(String[] args) {
        CommandLineParser commandLineParser = new CommandLineParserImp();
        Settings settings = commandLineParser.parseCommandLine(args);
        FilesSorter filesSorter = new FilesSorter(settings);

        filesSorter.mergeSortFiles();
    }
}
