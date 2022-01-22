package lacquerednocap.commandLineParser;

public class Helper {
    public static void help() {
        System.out.println("Параметры программы задаются при запуске через аргументы командной строки, по порядку:\n" +
                "1. режим сортировки (-a или -d), необязательный, по умолчанию сортируем по возрастанию;\n" +
                "2. тип данных (-s или -i), обязательный;\n" +
                "3. имя выходного файла, обязательное;\n" +
                "4. остальные параметры – имена входных файлов, не менее одного.\n\n" +
                "Примеры запуска из командной строки:\n" +
                "java -jar FOCUSSTART-FilesMerger.jar -i -a out.txt in.txt (для целых чисел по возрастанию)\n" +
                "java -jar FOCUSSTART-FilesMerger.jar -s out.txt in1.txt in2.txt in3.txt (для строк по возрастанию)\n" +
                "java -jar FOCUSSTART-FilesMerger.jar -d -s out.txt in1.txt in2.txt (для строк по убыванию)\n");
    }

    public static void getHelpCommand() {
        System.out.println("Пример запуска справки из командной строки \n" +
                "java -jar FOCUSSTART-FilesMerger.jar -help");
    }
}