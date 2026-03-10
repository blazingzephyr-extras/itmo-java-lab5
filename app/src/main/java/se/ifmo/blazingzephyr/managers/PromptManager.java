package se.ifmo.blazingzephyr.managers;

import java.util.ArrayList;
import java.util.Arrays;

public class PromptManager {
    
    private final String[] commands;
    private final ArrayList<String> history;

    public PromptManager(String[] commands)
    {
        this.commands = commands;
        this.history = new ArrayList<>();
    }

    public Iterable<String> getHistory()
    {
        return this.history;
    }

    public record CommandPrompt(String command, int index, String[] args) {}
    public CommandPrompt evaluate(String input)
    {
        String[] parts = input.trim().split(" ");
        String cmd = parts[0].toLowerCase();
        String[] args = parts.length > 1 ? Arrays.copyOfRange(parts, 1, parts.length) : new String[] {};

        int index = 0;
        for (String command : commands)
        {
            if (command != null && command.toLowerCase().equals(cmd))
            {
                this.history.add(command);
                return new CommandPrompt(command, index, args);
            }

            index++;
        }

        return null;
    }
}
