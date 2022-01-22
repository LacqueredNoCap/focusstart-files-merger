package lacquerednocap.sorter.comparators;

public class DescendingSortComparator implements SortComparator {
    @Override
    public <T extends Comparable<T>> int compare(T o1, T o2) {
        return o2.compareTo(o1);
    }
}