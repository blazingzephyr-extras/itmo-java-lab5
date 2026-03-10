package se.ifmo.blazingzephyr.utility;

import java.io.IOException;
import java.util.Date;
import java.util.Stack;
import se.ifmo.blazingzephyr.commands.Command;
import se.ifmo.blazingzephyr.managers.CollectionManager;
import se.ifmo.blazingzephyr.managers.FileManager;
import se.ifmo.blazingzephyr.managers.IoManager;
import se.ifmo.blazingzephyr.managers.PromptManager;
import se.ifmo.blazingzephyr.model.Organization;

public class Context
{
    private final Command[] commands;
    private final PromptManager prompts;
    private final CollectionManager collection;
    private final IoManager io;
    private final FileManager file;

    public Context(Command[] commands, PromptManager prompts, CollectionManager collection, IoManager io, FileManager file)
    {
        this.commands = commands;
        this.prompts = prompts;
        this.collection = collection;
        this.io = io;
        this.file = file;
    }

    public Command[] getAvailableCommands()
    {
        return this.commands;
    }

    public Iterable<String> getCommandHistory()
    {
        return this.prompts.getHistory();
    }

    public String getCollectionType()
    {
        return this.collection.getCollectionType();
    }

    public Date getCollectionInitDate()
    {
        return this.collection.getInitializationDate();
    }

    public int getCollectionSize()
    {
        return this.collection.size();
    }

    public Iterable<Organization> getElements()
    {
        return this.collection.getElements();
    }

    public void println(String message)
    {
        this.io.printToOutput(message);
    }

    public void println()
    {
        this.io.printToOutput("");
    }

    public String readLine() throws IOException
    {
        this.io.waitForInput();
        return this.io.lastReadLine();
    }

    public void addNew(Organization organization)
    {
        this.collection.addNew(organization);
    }

    public void clearCollection()
    {
        this.collection.clear();
    }

    public void saveCollection() throws IOException
    {
        this.file.openForWriting();

        Stack<Organization> e = this.collection.getElements();
        int s = this.collection.size();

        for (int i = 0; i < s - 1; i++)
        {
            this.file.write(e.get(i).toCsv());
            this.file.write("\n");
        }

        this.file.write(e.get(s - 1).toCsv());
        this.file.closeAfterWriting();
    }

    public void reorder()
    {
        this.collection.reorder();
    }
}
