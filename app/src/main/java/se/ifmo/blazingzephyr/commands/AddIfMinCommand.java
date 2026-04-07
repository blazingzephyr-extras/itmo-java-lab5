package se.ifmo.blazingzephyr.commands;

import se.ifmo.blazingzephyr.model.Organization;
import se.ifmo.blazingzephyr.utility.Context;
import se.ifmo.blazingzephyr.utility.OrganizationQuery;

/**
 * Добавляет элемент в коллекцию.
 * @author blazingzephyr
 * @version 1.0
 */
public class AddIfMinCommand implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "add_if_min";
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
        return "Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {

        OrganizationQuery asker = new OrganizationQuery(ctx.scanner());
        Organization organization = asker.queryNew();
        Organization min = ctx.collection().stream().min(Organization::compareTo).get();

        if (organization.compareTo(min) < 0)
        {
            ctx.collection().add(organization);
            return String.format(
                "Организация '%s' успешно добавлена с ID %d.%n",
                organization.getName(),
                organization.getId());
        }
        else {
            return "Организация не была добавлена.";
        }
    }
}
