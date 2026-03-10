package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.utility.Context;

public class ExitCommand implements Command {
    
    @Override
    public String getName()
    {
        return "exit";
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
        return "Завершает программу (без сохранения в файл).";
    }

    @Override
    public void execute(Context context, String[] args)
    {
        System.exit(0);
    }
}
