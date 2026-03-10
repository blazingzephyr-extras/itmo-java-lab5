
package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.utility.Context;

public class HistoryCommand implements Command {
    
    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public String[] getArguments() {
        return new String[] { };
    }

    @Override
    public String getDescription() {
        return "Выводит последние 15 команд (без их аргументов).";
    }

    @Override
    public void execute(Context context, String[] args) {

        StringBuilder builder = new StringBuilder();
        for (String commandName : context.getCommandHistory())
        {
            builder.append(commandName);
            builder.append('\n');
        }

        context.println(builder.toString());
    }
}
