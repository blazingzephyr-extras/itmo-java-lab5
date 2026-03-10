package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.utility.Context;

/**
 * Интерфейс команды.
 * @author blazingzephyr
 * @version 1.0
 */
public interface Command {
    /**
     * Возвращает наименование команды.
     * @return Короткое наименование команды.
     */
    String getName();
    
    /**
     * Возвращает краткое описание команды.
     * @return описание команды.
     */
    String getDescription();

    /**
     * Возвращает общий синтаксис команды.
     * @return синтаксис команды.
     */
    String getSyntax();

    /**
     * Возвращает информацию об аргументах команды.
     * @return аргументы команды.
     */
    String[] getArguments();

    /**
     * Исполняет команду.
     * @param context Контекст исполнения (дополнительные данные комманды).
     * @param args Аргументы команды.
     */
    void execute(Context context, String[] args);
}
