package se.ifmo.blazingzephyr.commands;

import java.io.IOException;
import se.ifmo.blazingzephyr.model.Address;
import se.ifmo.blazingzephyr.model.Coordinates;
import se.ifmo.blazingzephyr.model.Organization;
import se.ifmo.blazingzephyr.model.OrganizationType;
import se.ifmo.blazingzephyr.utility.Context;

public class AddCommand implements Command {
    
    @Override
    public String getName()
    {
        return "add";
    }

    @Override
    public String getSyntax()
    {
        return "{--element: Organization}";
    }

    @Override
    public String[] getArguments()
    {
        return new String[] {
            "element: Элемент, который требуется добавить в коллекцию."
        };
    }

    @Override
    public String getDescription()
    {
        return "Добавляет новый элемент в коллекцию.";
    }

    @Override
    public void execute(Context context, String[] args)
    {
        try
        {
            Organization organization = new Organization();

            context.println("Название организации: String, не может быть пустым.");
            organization.setName(context.readLine());

            context.println("Координаты организации: [x: float; y: float].");
            String input = context.readLine();
            String[] coord;
            if (input.isBlank())
            {
                throw new IllegalArgumentException("Введена пустая строка в поле 'координаты'.");
            }
            else if ((coord = input.split(" ")).length < 2)
            {
                throw new IllegalArgumentException("Недостаточно аргументов в поле 'координаты'.");
            }
            else
            {
                Coordinates coordinates = new Coordinates()
                    .setX(Double.valueOf(coord[0].isBlank() ? null : coord[0]))
                    .setY(Float.parseFloat(coord[1].isBlank() ? null : coord[1]));

                organization.setCoordinates(coordinates);
            }
            
            context.println("Ежегодная выручка: Double, не может быть null, должно быть больше нуля.");
            Double annualTurnover = Double.valueOf(context.readLine());
            organization.setAnnualTurnover(annualTurnover);

            context.println("Полное название: String, не может быть пустым.");
            organization.setFullName(context.readLine());

            context.println("Тип организации: " + OrganizationType.getTypes() + ".");
            OrganizationType type = OrganizationType.valueOf(context.readLine());
            organization.setOrganizationType(type);

            context.println("Адрес: [street: String, не null; zipCode: String, не null].");
            String[] addr = context.readLine().split(" ");
            Address address = new Address()
                .setStreet(addr[0])
                .setZipCode(addr[1]);

            organization.setOfficialAddress(address);
            context.addNew(organization);

            context.println();
            context.println(organization.toString());
            context.println();
            context.println("Успешно добавлено в базу данных.");
        }
        catch (IllegalArgumentException ex)
        {
            context.println("Некорректные данные: " + ex.getLocalizedMessage());
        }
        catch (IOException ex)
        {
            context.println("Ошибки памяти: " + ex.getLocalizedMessage());
        }
        
        context.println();
    }
}
