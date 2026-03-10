package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.utility.Context;

public class InfoCommand implements Command {
    
    @Override
    public String getName()
    {
        return "info";
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
        return "Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.).";
    }

    @Override
    public void execute(Context context, String[] args)
    {
        context.println("Тип коллекции: " + context.getCollectionType());
        context.println("Дата инициализации: " + context.getCollectionInitDate());
        context.println("Количество элементов: " + context.getCollectionSize());
        context.println();
    }
}
