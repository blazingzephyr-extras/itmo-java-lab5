package se.ifmo.blazingzephyr.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import se.ifmo.blazingzephyr.utility.Context;
import se.ifmo.blazingzephyr.utility.FileUtility;

/**
 * Сохраняет базу данных в файл.
 * @author blazingzephyr
 * @version 1.0
 */
public class SaveCommand implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "save";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSyntax() {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getArguments() {
        return new String[] {};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Сохраняет коллекцию в файл.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) throws FileNotFoundException, IOException {
        FileUtility.WriteDatabase(ctx.filepath(), ctx.collection());
        return String.format("""
            Коллекция была успешно записана в файл %s.
            Выведите её элементы, используя show. Для дополнительных опций вызовите help.""",
            ctx.filepath());
    }
}
