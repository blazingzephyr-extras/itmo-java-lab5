package se.ifmo.blazingzephyr.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import se.ifmo.blazingzephyr.utility.Context;

/**
 * Выводит справку о коллекции.
 * @author blazingzephyr
 * @version 1.0
 */
public class InfoCommand implements Command {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "info";
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
        return "Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.).";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) throws IOException {

        BasicFileAttributes file = Files.readAttributes(Path.of(ctx.filepath()), BasicFileAttributes.class);
        return String.format(
            "Тип элементов коллекции: %s\nДата инициализации: %s\nКоличество элементов: %d",
            ctx.collection().get(0).getClass().getCanonicalName(),
            file.creationTime(),
            ctx.collection().size()
        );
    }
}
