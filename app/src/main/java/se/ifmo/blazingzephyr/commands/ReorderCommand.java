package se.ifmo.blazingzephyr.commands;

import java.util.Collections;
import se.ifmo.blazingzephyr.utility.Context;

/**
 * Обращает порядок элементов в коллекции.
 * @author blazingzephyr
 * @version 1.0
 */
public class ReorderCommand implements Command {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "reorder";
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
    public String getDescription()
    {
        return "Отсортировывает коллекцию в порядке, обратном нынешнему.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {
        Collections.reverse(ctx.collection());
        return """
            Порядок элементов коллекции был обращён.
            Проверьте, используя show. Для дополнительных опций вызовите help.
            """;
    }
}
