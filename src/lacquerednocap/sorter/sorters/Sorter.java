package lacquerednocap.sorter.sorters;

import lacquerednocap.sorter.comparators.SortComparator;

public interface Sorter {
    <T extends Comparable<T>> void sort(T[] array, SortComparator sortComparator);
}