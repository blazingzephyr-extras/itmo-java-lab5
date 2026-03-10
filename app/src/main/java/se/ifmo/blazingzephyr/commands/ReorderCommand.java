package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.utility.Context;

public class ReorderCommand implements Command {
    
    @Override
    public String getName()
    {
        return "reorder";
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
        return "Отсортировывает коллекцию в порядке, обратном нынешнему.";
    }

    @Override
    public void execute(Context context, String[] args)
    {
        context.reorder();
        context.println();
    }
}
