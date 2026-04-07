package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.utility.Context;
import se.ifmo.blazingzephyr.utility.ScriptManager;

/**
 * Выполняет команды из файла.
 * @author blazingzephyr
 * @version 1.0
 */
public class ExecuteScriptCommand implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "execute_script";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSyntax() {
        return "file_name";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getArguments() {
        return new String[] {
            "file_name: Файл, из которого требуется выполнить команду."
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Считывает и исполняет скрипт из указанного файла. " + 
            "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {

        if (args.length < 1) {
            return "Ошибка: Не указан путь к файлу скрипта.";
        }

        String filePath = args[0];
        return ScriptManager.execute(filePath, ctx);
    }
}
