package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.model.Organization;
import se.ifmo.blazingzephyr.utility.Context;
import se.ifmo.blazingzephyr.utility.OrganizationQuery;

/**
 * Добавляет элемент в коллекцию.
 * @author blazingzephyr
 * @version 1.0
 */
public class AddCommand implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "add";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSyntax() {
        return "{--element: Organization}";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getArguments() {
        return new String[] {
            "element: Элемент, который требуется добавить в коллекцию."
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Добавляет новый элемент в коллекцию.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {

        OrganizationQuery asker = new OrganizationQuery(ctx.scanner());
        Organization organization = asker.queryNew();

        ctx.collection().add(organization);

        return String.format(
            "Организация '%s' успешно добавлена с ID %d.%n",
            organization.getName(),
            organization.getId());
    }
}
