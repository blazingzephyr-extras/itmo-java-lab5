package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.model.Organization;
import se.ifmo.blazingzephyr.utility.Context;
import se.ifmo.blazingzephyr.utility.OrganizationQuery;

/**
 * Обновляет элемент в коллекции.
 * @author blazingzephyr
 * @version 1.0
 */
public class UpdateCommand implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "update";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSyntax() {
        return "id {--element: Organization}";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getArguments() {
        return new String[] {
            "id: ID элемента, который требуется изменить",
            "element: Новые параметры данного элемента."
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Обновляет значение элемента коллекции, id которого равен заданному.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {
        final long id;
        if (args.length < 1) {
            return "Недостаточно аргументов. Требуется 1 аргумент.";
        }
        if ((id = Long.parseLong(args[0])) < 0) {
            return "ID элемента не может быть отрицательным.";
        }
        
        java.util.Optional<Organization> org = ctx
            .collection()
            .stream()
            .filter(o -> o.getId() == id)
            .findFirst();

        if (org.isEmpty())
        {
            return "Организации с искомым ID не существует.";
        }
        else
        {
            Organization organization = org.get();
            OrganizationQuery asker = new OrganizationQuery(ctx.scanner());
            asker.queryExisting(organization);

            return String.format("Организация с ID %d успешно изменена.%n", organization.getId());
        }
    }
}
