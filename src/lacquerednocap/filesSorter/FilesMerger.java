package lacquerednocap.filesSorter;

import lacquerednocap.settings.Settings;
import lacquerednocap.settings.dataType.DataType;
import lacquerednocap.settings.dataType.DataWrapper;

import java.io.*;
import java.util.*;

public class FilesMerger {
    private final Settings settings;

    public FilesMerger(Settings settings) {
        this.settings = settings;
    }

    public void mergeFiles(ArrayList<File> tempFiles) throws IOException {
        ArrayList<String> filesMaxNumbers = new ArrayList<>();
        ArrayList<BufferedReader> bufferedReaders = new ArrayList<>();

        BufferedReader bufferedReader;
        String currentWriteLine;

        for (File tempFile : tempFiles) {
            bufferedReader = getBufferedRead(tempFile);
            bufferedReaders.add(bufferedReader);

            currentWriteLine = bufferedReader.readLine();
            filesMaxNumbers.add(currentWriteLine);
        }

        File outputFile = new File(settings.getOutputFileName());

        try (FileWriter fileWriter = new FileWriter(outputFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            String nextWriteItem;
            int nextWriteItemFileIndex;

            while (bufferedReaders.size() > 0) {
                nextWriteItem = filesMaxNumbers.get(0);
                nextWriteItemFileIndex = 0;

                String[] tmp = new String[2];

                for (int j = 0; j < bufferedReaders.size(); j++) {
                    tmp[0] = nextWriteItem;
                    tmp[1] = filesMaxNumbers.get(j);

                    if (settings.getDataType() == DataType.INTEGER) {
                        Integer one = DataWrapper.getInteger(tmp[0]);
                        Integer two = DataWrapper.getInteger(tmp[1]);

                        if (settings.getSortComparator().compare(one, two) > 0) {
                            nextWriteItem = filesMaxNumbers.get(j);
                            nextWriteItemFileIndex = j;
                        }
                    } else {
                        String one = DataWrapper.getString(tmp[0]);
                        String two = DataWrapper.getString(tmp[1]);

                        if (settings.getSortComparator().compare(one, two) > 0) {
                            nextWriteItem = filesMaxNumbers.get(j);
                            nextWriteItemFileIndex = j;
                        }
                    }
                }

                printWriter.println(nextWriteItem);

                currentWriteLine = bufferedReaders.get(nextWriteItemFileIndex).readLine();
                if (currentWriteLine != null) {
                    filesMaxNumbers.set(nextWriteItemFileIndex, currentWriteLine);
                } else {
                    filesMaxNumbers.remove(nextWriteItemFileIndex);
                    bufferedReaders.get(nextWriteItemFileIndex).close();
                    bufferedReaders.remove(nextWriteItemFileIndex);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader getBufferedRead(File file) throws FileNotFoundException {
        return new BufferedReader(new FileReader(file));
    }
}