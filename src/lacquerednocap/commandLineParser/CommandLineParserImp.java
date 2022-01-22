package lacquerednocap.commandLineParser;

import lacquerednocap.settings.Settings;
import lacquerednocap.settings.SettingsImp;
import lacquerednocap.settings.SortOrder;
import lacquerednocap.settings.chunks.ChunkMaxSize;
import lacquerednocap.settings.chunks.ChunkService;
import lacquerednocap.settings.dataType.DataType;

import java.util.ArrayList;
import java.util.List;

public class CommandLineParserImp implements CommandLineParser {

    @Override
    public Settings parseCommandLine(String[] args) {

        if (args.length > 0 && args[0].equals("-help")) {
            Helper.help();

            System.exit(0);
        }

        SortOrder sortOrder = SortOrder.ASCENDING;
        DataType dataType = DataType.NOT_INSTALLED;
        ChunkMaxSize chunkMaxSize = new ChunkMaxSize();
        List<String> filesList = new ArrayList<>();

        try {
            if (args.length < 3) {
                throw new IllegalArgumentException("Введены не все параметры. Пожалуйста, введите команду заново или воспользуйтесь -help");
            }

            for (String arg : args) {
                if (ChunkService.isChunkCommand(arg)) {
                    int size = ChunkService.getCount(arg);
                    if (size > 0) {
                        chunkMaxSize = ChunkService.getSortingChunkMaxSize(size);
                    }

                    continue;
                }

                switch (arg) {
                    case ("-d"):
                        sortOrder = SortOrder.DESCENDING;
                        break;
                    case ("-a"):
                        sortOrder = SortOrder.ASCENDING;
                        break;
                    case ("-i"):
                        dataType = DataType.INTEGER;
                        break;
                    case ("-s"):
                        dataType = DataType.STRING;
                        break;
                    default:
                        filesList.add(arg);
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

            Helper.getHelpCommand();

            System.exit(0);
        }

        return new SettingsImp(sortOrder, dataType, filesList, chunkMaxSize);
    }
}