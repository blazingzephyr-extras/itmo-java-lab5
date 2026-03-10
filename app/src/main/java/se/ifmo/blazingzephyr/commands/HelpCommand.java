
package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.utility.Context;

public class HelpCommand implements Command {
    
    @Override
    public String getName()
    {
        return "help";
    }

    @Override
    public String getSyntax()
    {
        return "[--command: String]";
    }

    @Override
    public String[] getArguments()
    {
        return new String[]
        {
            "command: Команда, информацию о которой требуется узнать.",
            "   Аргумент можно опустить для получения информации о всех командах."
        };
    }

    @Override
    public String getDescription()
    {
        return "Выводит справку по доступным командам.";
    }

    @Override
    public void execute(Context context, String[] args)
    {
        StringBuilder builder = new StringBuilder();
        if (args.length == 0)
        {
            builder.append("Список имеющихся команд:");
            builder.append('\n');
    
            for (Command command : context.getAvailableCommands()) {
                
                builder.append(command.getName());
                builder.append(" - ");
                builder.append(command.getDescription());
                builder.append('\n');
            }
            
        }
        else
        {
            String cmd = args[0].toLowerCase();
            for (Command command : context.getAvailableCommands())
            {
                if (command.getName() != null && command.getName().toLowerCase().equals(cmd))
                {
                    builder.append(command.getName());
                    builder.append(' ');
                    builder.append(command.getSyntax());
                    builder.append('\n');
                    builder.append("- ");
                    builder.append(command.getDescription());
                    builder.append('\n');
                    
                    if (command.getArguments().length > 0)
                    {
                        builder.append('\n');

                        for (String s : command.getArguments())
                        {
                            builder.append(s);
                            builder.append('\n');
                        }
                    }
                    
                    break;
                }
            }
        }
        
        context.println(builder.toString());
    }
}
