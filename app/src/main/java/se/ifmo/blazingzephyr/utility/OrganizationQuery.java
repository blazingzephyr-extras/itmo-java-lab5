package se.ifmo.blazingzephyr.utility;

import java.util.Scanner;
import se.ifmo.blazingzephyr.model.*;

/**
 * Класс для интерактивного чтения полей Organization.
 * @author blazingzephyr
 * @version 1.0
 */
public class OrganizationQuery {
    private final Scanner scanner;

    /**
     * Создаёт запрос об интерактивном чтении данных из консоли.
     * @param scanner Объект, из которого ведётся чтение данных организации.
     */
    public OrganizationQuery(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Запрашивает обновлённые значения полей организации и присваивает их существующему объекту.
     * @param organization объект организации, который требуется обновить.
     */
    public void queryExisting(Organization organization)
    {
        organization.setName(queryName());
        organization.setCoordinates(askCoordinates());
        organization.setAnnualTurnover(askAnnualTurnover());
        organization.setFullName(askFullName());
        organization.setOrganizationType(askType());
        organization.setOfficialAddress(askAddress());
    }

    /**
     * Запрашивает у пользователя данные для создания нового объекта организации.
     * @return новая организация.
     */
    public Organization queryNew() {
        Organization organization = new Organization();
        queryExisting(organization);
        return organization;
    }

    /**
     * Запрашивает у пользователя имя и производит его валидацию.
     * @return название организации.
     */
    private String queryName() {
        while (true) {
            System.out.println("Название организации: String, не может быть пустым.");
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) return input;
            System.out.println("Некорректные данные: названием не может быть пустым.");
        }
    }

    /**
     * Запрашивает у пользователя координаты и производит их валидацию.
     * @return
     */
    private Coordinates askCoordinates() {
        while (true) {
            try {
                System.out.println("Координаты организации: [x: float; y: float].");
                String input = scanner.nextLine().trim();
                String[] coord = input.split(" ");
                
                if (input.isBlank()) throw new IllegalArgumentException("Введена пустая строка в поле 'координаты'.");
                if (coord.length < 2) throw new IllegalArgumentException("Недостаточно аргументов в поле 'координаты'.");

                return new Coordinates()
                        .setX(Double.valueOf(coord[0]))
                        .setY(Float.parseFloat(coord[1]));
            } catch (Exception ex) {
                System.out.println("Некорректные данные: " + ex.getLocalizedMessage());
            }
        }
    }

    /**
     * Запрашивает у пользователя данные о годовой выручке компании и возвращает их после валидации
     * @return объём годовой выручки
     */
    private Double askAnnualTurnover() {
        while (true) {
            try {
                System.out.println("Ежегодная выручка: Double, не может быть null, должно быть больше нуля.");
                String input = scanner.nextLine().trim();
                Double annualTurnover = Double.valueOf(input);
                if (annualTurnover <= 0) throw new IllegalArgumentException("Должно быть больше нуля.");
                return annualTurnover;
            } catch (Exception ex) {
                System.out.println("Некорректные данные: " + ex.getLocalizedMessage());
            }
        }
    }

    /**
     * Запрашивает у пользователя полное имя организации и возвращает данные после валидации.
     * @return полное имя.
     */
    private String askFullName() {
        while (true) {
            System.out.println("Полное название: String, не может быть пустым.");
            String input = scanner.nextLine().trim();
            
            if (!input.isEmpty()) return input;
            System.out.println("Некорректные данные: названием не может быть пустым.");
        }
    }

    /**
     * Запрашивает у пользователя полный тип организации и возвращает его после валидации.
     * @return тип организации.
     */
    private OrganizationType askType() {
        while (true) {
            try {
                System.out.println("Тип организации: " + OrganizationType.getTypes() + ".");
                String input = scanner.nextLine().trim().toUpperCase();
                return OrganizationType.valueOf(input);
            } catch (Exception ex) {
                System.out.println("Некорректные данные: Такого типа не существует.");
            }
        }
    }

    /**
     * Запрашивает улицу и почтовый код организации.
     * @return полный адрес организации.
     */
    private Address askAddress() {
        while (true) {
            try {
                System.out.println("Адрес: [street: String, не null; zipCode: String, не null].");
                String input = scanner.nextLine().trim();
                String[] addr = input.split(" ");

                if (input.isBlank()) throw new IllegalArgumentException("Введена пустая строка в поле 'адрес'.");
                if (addr.length < 2) throw new IllegalArgumentException("Недостаточно аргументов в поле 'адрес'.");

                return new Address()
                        .setStreet(addr[0])
                        .setZipCode(addr[1]);
            } catch (Exception ex) {
                System.out.println("Некорректные данные: " + ex.getLocalizedMessage());
            }
        }
    }
}