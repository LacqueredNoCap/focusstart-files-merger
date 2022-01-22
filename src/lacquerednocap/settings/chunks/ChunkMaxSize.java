package lacquerednocap.settings.chunks;

public class ChunkMaxSize {
    public static final int defaultSize = 100_000;

    private final int size;

    public ChunkMaxSize(int size) {
        this.size = size;
    }

    public ChunkMaxSize() {
        size = ChunkMaxSize.defaultSize;
    }

    public int getSize() {
        return size;
    }
}