package se.ifmo.blazingzephyr.utility;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import se.ifmo.blazingzephyr.commands.*;

/**
 * Управляет исполнением команд.
 * @author blazingzephyr
 * @version 1.0
 */
public class CommandUtility {
    
    private final Map<String, Command> commands;

    public CommandUtility()
    {
        this.commands = Stream.of(
            new HelpCommand(),
            new HistoryCommand(),
            new ExitCommand(),
            new InfoCommand(),
            new ShowCommand(),
            new AddCommand(),
            new ClearCommand(),
            new SaveCommand(),
            new ReorderCommand(),
            new PrintFieldAscendingAnnualTurnoverCommand(),
            new MinByName(),
            new RemoveById(),
            new FilterGreaterThanTypeCommand(),
            new UpdateCommand(),
            new AddIfMinCommand(),
            new ExecuteScriptCommand()
        )
        .collect(Collectors.toMap(Command::getName, Function.identity()));
    }

    /**
     * Возвращает исполняемые команды.
     * @return набор поддерживаемых команд
     */
    public Map<String, Command> getCommands()
    {
        return this.commands;
    }

    /**
     * Исполняет введённые пользователем команды.
     * @param input Пользовательский ввод, который требуется обработать.
     * @param context Контекст, в котором исполняется команда.
     * @return Возвращает результат исполнения программы.
     * @throws Exception Ошибка исполнения команды.
     */
    public String execute(String input, Context context) throws Exception
    {
        String[] parts = input.trim().split(" ");
        String cmd = parts[0].toLowerCase();
        String[] arguments = parts.length > 1 ? Arrays.copyOfRange(parts, 1, parts.length) : new String[] {};
        
        if (!this.commands.containsKey(cmd)) {
            return "Ну ты и дурашка, такой команды не существует! Используй хелп!\n";
        }
        else {
            Command command = commands.get(cmd);
            String output = command.execute(context, arguments);
            
            if (context.history().size() == 15) context.history().remove(14);
            context.history().add(0, command);

            return output + "\n";
        }
    }
}
