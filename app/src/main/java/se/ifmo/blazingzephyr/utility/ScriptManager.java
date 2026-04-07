package se.ifmo.blazingzephyr.utility;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Класс, управляющий исполнением пользовательских скриптов из файла.
 * @author blazingzephyr
 * @version 1.0
 */
public class ScriptManager {
    // Стэк путей файлов, исполняемых на данный момент.
    private static final Set<String> scriptStack = new HashSet<>();

    /**
     * Исполняет указанный скрипт.
     * @param filePath путь к пользовательскому скрипту.
     * @param context контекст, в котором требуется исполнить программы.
     * @return результат исполнения программы.
     */
    public static String execute(String filePath, Context context) {
        
        if (scriptStack.contains(filePath)) {
            return "Ошибка: Обнаружена рекурсия! Файл " + filePath + " уже вызван выше по стеку.";
        }

        try {
            try (BufferedReader reader = FileUtility.ReadBuffer(filePath)) {
                scriptStack.add(filePath);

                StringBuilder scriptOutput = new StringBuilder("---" + filePath + "---\n");
                Scanner scriptScanner = new Scanner(reader);
                Context scriptCtx = new Context(context.filepath(), context.commands(), context.history(), context.collection(), scriptScanner);
    
                while (scriptScanner.hasNextLine()) {
                    String line = scriptScanner.nextLine().trim();
                    if (line.isEmpty()) continue;
    
                    String result =  context.commands().execute(line, scriptCtx);
                    scriptOutput.append(result);
                }
                
                scriptStack.remove(filePath);
                return scriptOutput.append("---").append(filePath).append(" завершен---").toString();
            }

        } catch (Exception e) {
            scriptStack.remove(filePath);
            return String.format("Возникла ошибка при исполнение скрипта '%s': %s", filePath, e.getMessage());
        }
    }
}
