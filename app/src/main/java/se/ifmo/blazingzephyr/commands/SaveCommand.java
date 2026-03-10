package se.ifmo.blazingzephyr.commands;

import java.io.IOException;
import se.ifmo.blazingzephyr.utility.Context;

public class SaveCommand implements Command {
    
    @Override
    public String getName()
    {
        return "save";
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
        return "Сохраняет коллекцию в файл.";
    }

    @Override
    public void execute(Context context, String[] args)
    {
        try
        {
            context.saveCollection();
        } catch (IOException ex)
        {
            context.println("Ошибка записи в файл: " + ex.getMessage());
        }

        context.println();
    }
}
