package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.utility.Context;

/**
 * Очищает коллекцию, уничтожая элементы.
 * @author blazingzephyr
 * @version 1.0
 */
public class ClearCommand implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "clear";
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
        return "Очищает коллекцию.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {
        ctx.collection().clear();
        return "Элементы коллекции были зачищены.\nПроверьте, используя show. Для дополнительных опций используйте help.";
    }
}
