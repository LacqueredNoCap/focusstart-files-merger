package lacquerednocap.filesSorter;

import lacquerednocap.settings.dataType.DataType;
import lacquerednocap.settings.dataType.DataValidator;
import lacquerednocap.settings.dataType.DataWrapper;
import lacquerednocap.settings.Settings;

import java.io.*;
import java.util.*;

public class FileSplitter {
    private final Settings settings;

    public FileSplitter(Settings settings) {
        this.settings = settings;
    }

    public ArrayList<File> splitFiles() {
        ArrayList<File> files = new ArrayList<>();

        for (String fileName : settings.getFileNamesList()) {
            files.addAll(splitFile(new File(fileName)));
        }

        return files;
    }

    private ArrayList<File> splitFile(File file) {
        ArrayList<File> tempFiles = new ArrayList<>();
        String[] chunk = new String[settings.getChunkMaxSize()];

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            int currentChunkSize = 0;
            String currentLine;

            while (true) {
                currentLine = bufferedReader.readLine();
                if (currentLine != null) {
                    chunk[currentChunkSize] = currentLine;

                    if (currentChunkSize == settings.getChunkMaxSize() - 1) {
                        tempFiles.add(createTempFile(chunk));
                        currentChunkSize = 0;

                        Arrays.fill(chunk, null);
                    } else {
                        currentChunkSize += 1;
                    }
                } else {
                    if (currentChunkSize > 0) {
                        tempFiles.add(createTempFile(chunk));
                    }

                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return tempFiles;
    }

    private File createTempFile(String[] chunk) throws IOException {
        String tempFilePrefix = "sort-temp-file-";

        File newFile = File.createTempFile(tempFilePrefix, null);
        try (FileWriter fileWriter = new FileWriter(newFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            String[] correctChunk = DataValidator.deleteNull(chunk);

            if (settings.getDataType() == DataType.INTEGER) {
                Integer[] data = DataWrapper.getIntegerData(correctChunk);

                settings.getSorter().sort(data, settings.getSortComparator());

                for (Integer s : data) {
                    printWriter.println(s);
                }
            } else {
                String[] data = DataWrapper.getStringData(correctChunk);

                settings.getSorter().sort(data, settings.getSortComparator());

                for (String s : data) {
                    printWriter.println(s);
                }
            }
        }

        return newFile;
    }
}