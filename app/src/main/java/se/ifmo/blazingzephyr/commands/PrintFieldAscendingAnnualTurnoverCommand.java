package se.ifmo.blazingzephyr.commands;

import java.util.stream.Collectors;
import se.ifmo.blazingzephyr.model.Organization;
import se.ifmo.blazingzephyr.utility.Context;

/**
 * Выводит значения поля annualTurnover всех элементов в порядке возрастания.
 * @author blazingzephyr
 * @version 1.0
 */
public class PrintFieldAscendingAnnualTurnoverCommand implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "print_field_ascending_annual_turnover";
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
        return "Выводит значения поля annualTurnover всех элементов в порядке возрастания";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Context ctx, String[] args) {

        return String.format(
            "| %-40s |\n|------------------------------------------|\n%s",
            "Годовая выручка в порядке возрастания.",
            ctx.collection().stream()
                .map(Organization::getAnnualTurnover)
                .sorted(Double::compareTo)
                .map(t -> String.format("| %-40.2f | ", t))
                .collect(Collectors.joining("\n"))
        );
    }
}
