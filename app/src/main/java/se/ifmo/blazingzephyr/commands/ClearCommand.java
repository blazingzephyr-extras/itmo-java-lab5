package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.utility.Context;

public class ClearCommand implements Command {
    
    @Override
    public String getName()
    {
        return "clear";
    }

    @Override
    public String getSyntax()
    {
        return "";
    }

    @Override
    public String[] getArguments()
    {
        return new String[] {};
    }

    @Override
    public String getDescription()
    {
        return "Очищает коллекцию.";
    }

    @Override
    public void execute(Context context, String[] args)
    {
        context.clearCollection();
        context.println();
    }
}
