package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.model.Organization;
import se.ifmo.blazingzephyr.utility.Context;
import se.ifmo.blazingzephyr.utility.TableUtility;

/**
 * Удаляет объект из коллекции по ID.
 * @author blazingzephyr
 * @version 1.0
 */
public class RemoveById implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "remove_by_id";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSyntax() {
        return "[--id: long]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getArguments() {
        return new String[] {
            "id: ID элемента, который требуется удалить."
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Удаляет элемент из коллекции по его id";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) throws IllegalArgumentException {
        final long id;
        if (args.length < 1) {
            throw new IllegalArgumentException("Недостаточно аргументов. Требуется 1 аргумент.");
        }
        if ((id = Long.parseLong(args[0])) < 0) {
            throw new IllegalArgumentException("ID элемента не может быть отрицательным.");
        }

        java.util.Optional<Organization> org = ctx
            .collection()
            .stream()
            .filter(o -> o.getId() == id)
            .findFirst();

        if (org.isEmpty())
        {
            throw new IllegalArgumentException("Организации с искомым ID не существует!");
        }
        else
        {
            ctx.collection().remove(org.get());
            return String.format("%s%n%s%nЭлемент с ID %d успешно удалён.",
                TableUtility.getHeader(), 
                TableUtility.getEntry(org.get()),
                id
            );
        }
    }
}
