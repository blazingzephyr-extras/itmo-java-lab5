package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.model.Organization;
import se.ifmo.blazingzephyr.utility.Context;

public class ShowCommand implements Command {
    
    @Override
    public String getName()
    {
        return "show";
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
        return "Выводит в стандартный поток вывода все элементы коллекции в строковом представлении.";
    }

    @Override
    public void execute(Context context, String[] args)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Элементы коллекции: {");
        builder.append(context.getCollectionSize());
        builder.append("}");
        builder.append('\n');

        for (Organization org : context.getElements())
        {
            builder.append('\n');
            builder.append(org);
            builder.append('\n');
        }

        context.println(builder.toString());
    }
}
