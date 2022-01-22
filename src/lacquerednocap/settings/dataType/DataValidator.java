package lacquerednocap.settings.dataType;

import java.util.*;

public class DataValidator {
    public static String[] deleteNull(String[] strings) {
        List<String> list = new LinkedList<>(Arrays.asList(strings));
        list.removeAll(Collections.singleton(null));
        return list.toArray(new String[0]);
    }

    public static boolean isDigit(String data) {
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}