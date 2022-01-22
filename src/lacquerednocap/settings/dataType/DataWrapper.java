package lacquerednocap.settings.dataType;

public class DataWrapper {
    public static String getString(String data) {
        return data;
    }

    public static Integer getInteger(String data) throws NumberFormatException {
        return DataValidator.isDigit(data) ? Integer.parseInt(data) : null;
    }

    public static String[] getStringData(String[] data) {
        return DataValidator.deleteNull(data);
    }

    public static Integer[] getIntegerData(String[] data) throws NumberFormatException {
        String[] strings = new String[data.length];

        for (int i = 0; i < data.length; ++i) {
            if (DataValidator.isDigit(data[i])) {
                strings[i] = data[i];
            }
        }

        String[] clearedData = DataValidator.deleteNull(strings);
        Integer[] integers = new Integer[clearedData.length];

        for (int i = 0; i < clearedData.length; ++i) {
            integers[i] = Integer.parseInt(clearedData[i]);

        }

        return integers;
    }
}