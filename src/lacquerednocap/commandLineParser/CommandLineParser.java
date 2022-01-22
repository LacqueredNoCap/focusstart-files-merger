package lacquerednocap.commandLineParser;

import lacquerednocap.settings.Settings;

public interface CommandLineParser {
    Settings parseCommandLine(String[] commandLine);
}