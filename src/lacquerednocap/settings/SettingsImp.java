package lacquerednocap.settings;

import lacquerednocap.commandLineParser.Helper;
import lacquerednocap.settings.chunks.ChunkMaxSize;
import lacquerednocap.settings.dataType.DataType;
import lacquerednocap.sorter.comparators.AscendingSortComparator;
import lacquerednocap.sorter.comparators.DescendingSortComparator;
import lacquerednocap.sorter.comparators.SortComparator;
import lacquerednocap.sorter.sorters.MergeSortGeneric;
import lacquerednocap.sorter.sorters.Sorter;

import java.util.List;

public class SettingsImp implements Settings {
    private final DataType dataType;
    private final SortComparator sortComparator;
    private final Sorter sorter;
    private final String outputFileName;
    private final List<String> sortableFilesNameList;
    private final ChunkMaxSize chunkMaxSize;

    public SettingsImp(SortOrder sortOrder, DataType dataType, List<String> filesList, ChunkMaxSize chunkMaxSize) {
        SettingsImp.validateSettings(dataType, filesList);

        this.dataType = dataType;
        this.sortComparator = sortOrder.equals(SortOrder.ASCENDING) ? new AscendingSortComparator() : new DescendingSortComparator();
        this.sorter = new MergeSortGeneric();
        this.outputFileName = filesList.get(0);
        this.sortableFilesNameList = filesList.subList(1, filesList.size());
        this.chunkMaxSize = chunkMaxSize;
    }

    public static void validateSettings(DataType dataType, List<String> filesList) {
        try {
            if (dataType.equals(DataType.NOT_INSTALLED)) {
                throw new IllegalArgumentException("Необходимо задать тип данных: \"-i\" для чисел или \"-s\" для строк (или воспользуйтесь \"-help\")");
            }

            if (filesList.size() < 2) {
                throw new IllegalArgumentException("Необходимо задать название выходного файла и " +
                        "название не менее одного файла для сортировки (или воспользуйтесь \"-help\")");
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

            Helper.getHelpCommand();

            System.exit(0);
        }
    }

    @Override
    public DataType getDataType() {
        return dataType;
    }

    @Override
    public SortComparator getSortComparator() {
        return sortComparator;
    }

    @Override
    public Sorter getSorter() {
        return sorter;
    }

    @Override
    public String getOutputFileName() {
        return outputFileName;
    }

    @Override
    public List<String> getFileNamesList() {
        return sortableFilesNameList;
    }

    @Override
    public int getChunkMaxSize() {
        return chunkMaxSize.getSize();
    }
}