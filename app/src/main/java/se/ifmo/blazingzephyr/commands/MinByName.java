package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.model.Organization;
import se.ifmo.blazingzephyr.utility.Context;
import se.ifmo.blazingzephyr.utility.TableUtility;

/**
 * Выводит объект из коллекции, name которого является минимальным.
 * @author blazingzephyr
 * @version 1.0
 */
public class MinByName implements Command {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "min_by_name";
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
        return "Выводит любой объект из коллекции, значение поля name которого является минимальным.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {
        Organization org = ctx.collection().stream()
            .min(Organization::compareTo)
            .orElse(null);

        return String.format(
            "Элемент с минимальным значением name.\n%s\n%s",
            TableUtility.getHeader(),
            TableUtility.getEntry(org));
    }
}
