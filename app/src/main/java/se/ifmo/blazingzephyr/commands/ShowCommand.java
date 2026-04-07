package se.ifmo.blazingzephyr.commands;

import java.util.stream.Collectors;
import se.ifmo.blazingzephyr.utility.Context;
import se.ifmo.blazingzephyr.utility.TableUtility;

/**
 * Выводит коллекцию в удобный для пользователя вид.
 * @author blazingzephyr
 * @version 1.0
 */
public class ShowCommand implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "show";
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
        return new String[] {};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Выводит в стандартный поток вывода все элементы коллекции в строковом представлении.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {    
        if (ctx.collection().isEmpty()) {
            return "Коллекция пуста.";
        }

        return String.format(
            "Количество элементов: %d%n%s%n%s",
            ctx.collection().size(),
            TableUtility.getHeader(),
            ctx.collection()
                .stream()
                .map(TableUtility::getEntry)
                .collect(Collectors.joining("\n"))
        );
    }
}
