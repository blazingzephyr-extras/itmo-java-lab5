package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.utility.Context;

/**
 * Завершает программу без сохранения.
 * @author blazingzephyr
 * @version 1.0
 */
public class ExitCommand implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "exit";
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
        return "Завершает программу (без сохранения в файл).";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {
        System.exit(0);
        return null;
    }
}
