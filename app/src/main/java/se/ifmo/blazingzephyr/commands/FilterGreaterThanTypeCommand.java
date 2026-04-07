package se.ifmo.blazingzephyr.commands;

import java.util.stream.Collectors;
import se.ifmo.blazingzephyr.model.OrganizationType;
import se.ifmo.blazingzephyr.utility.Context;
import se.ifmo.blazingzephyr.utility.TableUtility;

/**
 * Выводит элементы коллекции, чей тип больше, чем искомый.
 * @author blazingzephyr
 * @version 1.0
 */
public class FilterGreaterThanTypeCommand implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "filter_greater_than_type";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSyntax() {
        return "type";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getArguments() {
        return new String[] {
            "type: Значение поля type [" + OrganizationType.getTypes() + "]"
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Выводит элементы, значение поля type которых больше заданного.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {
        if (args.length < 1) {
            return "Ошибка: Не указан тип для сравнения. Доступные типы: " + OrganizationType.getTypes();
        }

        try {
            OrganizationType targetType = OrganizationType.valueOf(args[0].toUpperCase());
            String result = ctx.collection().stream()
                    .filter(org -> org.getOrganizationType() != null && org.getOrganizationType().compareTo(targetType) > 0)
                    .map(TableUtility::getEntry)
                    .collect(Collectors.joining("\n"));

            if (result.isEmpty()) {
                return "Элементов с типом больше " + targetType + " не найдено.";
            }

            return String.format("Элементы с типом, больше чем %s:%n%s%n%s",
                targetType,
                TableUtility.getHeader(),
                result);

        } catch (IllegalArgumentException ex) {
            return String.format("Ошибка: %s%nИмеющиеся типы: %s", ex.getMessage(), OrganizationType.getTypes());
        }
    }
}
