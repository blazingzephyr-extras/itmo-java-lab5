package se.ifmo.blazingzephyr;

import java.io.FileNotFoundException;
import java.io.IOException;
import se.ifmo.blazingzephyr.commands.*;
import se.ifmo.blazingzephyr.managers.*;
import se.ifmo.blazingzephyr.managers.PromptManager.CommandPrompt;
import se.ifmo.blazingzephyr.utility.Context;

public class App {

    public static void main(String[] args) {

        IoManager io = new IoManager();
        String fpath = System.getenv("DATA_SHEET_PATH");
        if (fpath == null || fpath.isEmpty())
        {
            io.printToOutput("Отсутствует переменная среды пути файла! Выбрано стандартное значение.");
            fpath = "data.csv";
        }

        FileManager file = new FileManager().setFilePath(fpath);
        try
        {
            file.openForReading();
        }
        catch (FileNotFoundException ex)
        {
            io.printToOutput("Искомый файл базы данных не был найден: " + ex.getMessage());
            return;
        }

        CollectionManager collection = new CollectionManager();
        try
        {
            for (String line : file.readAllLines())
            {
                collection.addFromCsv(line);
            }
        }
        catch (IOException ex)
        {
            io.printToOutput("Возникла ошибка доступа к файла: " + ex.getMessage());
            return;
        }

        Command[] commands = new Command[] { 
            new HelpCommand(), new HistoryCommand(), new ExitCommand(), new InfoCommand(), new Show(), new AddCommand(), new ClearCommand(),
            new Save(), new Reorder()
        };

        String[] commandNames = new String[commands.length];

        for (int i = 0; i < commands.length; i++)
        {
            commandNames[i] = commands[i].getName();
        }

        PromptManager prompts = new PromptManager(commandNames);
        Context context = new Context(commands, prompts, collection, io, file);
        
        try
        {
            while (io.waitForInput())
            {
                CommandPrompt prompt = prompts.evaluate(io.lastReadLine());
                if (prompt == null)
                {
                    io.printToOutput("Искомой команды не найдено! Попробуйте воспользоваться командой help!");
                }
                else
                {
                    commands[prompt.index()].execute(context, prompt.args());
                }
            }
        }
        catch (IOException ex)
        {
            io.printToOutput("Возникла ошибка во время чтения ввода из консоли: " + ex.getMessage());
        }

        try
        {
            io.closeInputReader();
        }
        catch (IOException ex)
        {
            io.printToOutput("Возникла ошибка во время закрытия потока чтения из консоли: " + ex.getMessage());
        }
    }
}