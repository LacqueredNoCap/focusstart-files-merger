package lacquerednocap.settings;

import lacquerednocap.settings.dataType.DataType;
import lacquerednocap.sorter.comparators.SortComparator;
import lacquerednocap.sorter.sorters.Sorter;

import java.util.List;

public interface Settings {

    DataType getDataType();

    SortComparator getSortComparator();

    Sorter getSorter();

    String getOutputFileName();

    List<String> getFileNamesList();

    int getChunkMaxSize();
}