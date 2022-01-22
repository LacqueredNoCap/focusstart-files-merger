package lacquerednocap.settings.chunks;

import lacquerednocap.settings.dataType.DataValidator;
import lacquerednocap.settings.dataType.DataWrapper;

public class ChunkService {
    public static String chunkCommand = "-chunk";

    public static ChunkMaxSize getSortingChunkMaxSize(Integer chunkSize) {
        return (chunkSize > 0) ? new ChunkMaxSize(chunkSize) : new ChunkMaxSize();
    }

    public static boolean isChunkCommand(String command) {
        if (command.contains(chunkCommand)) {
            return DataValidator.isDigit(command.substring(chunkCommand.length()));
        }

        return false;
    }

    public static Integer getCount(String string) {
        return DataWrapper.getInteger(string.substring(chunkCommand.length()));
    }
}