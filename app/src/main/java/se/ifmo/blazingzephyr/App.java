package se.ifmo.blazingzephyr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import se.ifmo.blazingzephyr.commands.Command;
import se.ifmo.blazingzephyr.model.Organization;
import se.ifmo.blazingzephyr.utility.CommandUtility;
import se.ifmo.blazingzephyr.utility.Context;
import se.ifmo.blazingzephyr.utility.FileUtility;

/**
 * Класс, содержащий точку входа.
 * @author blazingzephyr
 * @version 1.0
 */
public class App {

    /**
     * Точка входа в программу.
     * @param args пользовательские аргументы (игнорируются).
     */
    public static void main(String[] args) {
        String fpath = System.getenv("DATA_SHEET_PATH");
        if (fpath == null || fpath.isEmpty()) {
            System.out.println("Отсутствует переменная среды пути файла! Выбрано стандартное значение.");
            fpath = "data.csv";
        }

        Stack<Organization> collection = new Stack<>();
        try {
            collection = FileUtility.ReadDatabase(fpath);
        } catch (FileNotFoundException ex) {
            System.out.println("Искомый файл базы данных не был найден: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Возникла ошибка доступа к файла: " + ex.getMessage());
        }

        CommandUtility commands = new CommandUtility();
        ArrayList<Command> history = new ArrayList<>(15);
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Context context = new Context(fpath, commands, history, collection, scanner);

        try {
            while (true) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) continue;

                String output = commands.execute(input, context);
                System.out.println(output);
            }
        } catch (Exception ex) {
            System.out.println("Возникла ошибка во время чтения ввода из консоли: " + ex.getMessage());
        }
    }
}
