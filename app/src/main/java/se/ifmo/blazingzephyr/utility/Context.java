package se.ifmo.blazingzephyr.utility;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import se.ifmo.blazingzephyr.commands.Command;
import se.ifmo.blazingzephyr.model.Organization;

/**
 * Data-transfer-only объект.
 * Причина, по которой он не был удалён: чтобы не передавать в Command.execute изменяющийся набор аргументов.
 * 
 * @param filepath Путь к файлу БД, из которого ведётся чтение и в который ведётся запись.
 * @param commands Объект, управляющий исполнением команд.
 * @param history История вызова команд.
 * @param collection Объект базы данных.
 * @param io Интерфейс ввода-вывода пользовательских данных.
 * 
 * @author blazingzephyr
 * @version 1.0
 */
public record Context(String filepath, CommandUtility commands, List<Command> history, Stack<Organization> collection, Scanner scanner) { }
