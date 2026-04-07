
package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.utility.Context;

/**
 * Справка о доступных коммандах.
 * @author blazingzephyr
 * @version 1.0
 */
public class HelpCommand implements Command {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "help";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSyntax() {
        return "[--command: String]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getArguments() {
        return new String[]
        {
            "command: Команда, информацию о которой требуется узнать.",
            "   Аргумент можно опустить для получения информации о всех командах."
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Выводит справку по доступным командам.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {
        StringBuilder builder = new StringBuilder();
        if (args.length == 0) {
            builder.append("Список имеющихся команд:\n");

            for (Command command : ctx.commands().getCommands().values()) {
                builder.append(String.format(" - %s: %s\n", command.getName(), command.getDescription()));
            }
        }
        else {
            String cmd = args[0].toLowerCase();
            if (ctx.commands().getCommands().containsKey(cmd))
            {
                Command command = ctx.commands().getCommands().get(cmd);
                builder.append(String.format("%s %s\n- %s", 
                    command.getName(),
                    command.getSyntax(),
                    command.getDescription()));

                if (command.getArguments().length > 0) {
                    builder.append('\n');

                    for (String s : command.getArguments()) {
                        builder.append('\n');
                        builder.append(s);
                    }
                }
            }
        }

        return builder.toString();
    }
}
