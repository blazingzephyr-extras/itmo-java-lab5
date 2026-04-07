
package se.ifmo.blazingzephyr.commands;

import java.util.stream.Collectors;
import se.ifmo.blazingzephyr.utility.Context;

/**
 * Выводит список предыдущих вызовов команд.
 * @author blazingzephyr
 * @version 1.0
 */
public class HistoryCommand implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "history";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSyntax() {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getArguments() {
        return new String[] { };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Выводит последние 15 команд (без их аргументов).";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {

        return String.format(
            "Последние 15 команд (исключая эту):\n%s",
            ctx.history()
                .stream()
                .map(Command::getName)
                .collect(Collectors.joining("\n"))
        );
    }
}
