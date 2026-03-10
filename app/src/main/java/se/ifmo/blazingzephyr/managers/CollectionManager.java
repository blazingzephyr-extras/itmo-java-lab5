package se.ifmo.blazingzephyr.managers;

import java.util.Collections;
import java.util.Date;
import java.util.Stack;
import se.ifmo.blazingzephyr.model.Organization;

/**
 * Осуществляет единоличное управление коллекцией объектов типа Организация.
 */
public class CollectionManager {
    
    private final Stack<Organization> organizations;
    private Date initializationDate;

    public CollectionManager()
    {
        this.organizations = new Stack<>();
        this.initializationDate = new Date();
    }

    /**
     * 
     * @return
     */
    public Stack<Organization> getElements()
    {
        return this.organizations;
    }

    /**
     * A
     * @return B
     */
    public String getCollectionType()
    {
        return organizations.get(0).getClass().getCanonicalName();
    }

    /**
     * Возвращает дату инициализации данной коллекции (дату создания первого элемента в ней).
     * @return A
     */
    public Date getInitializationDate()
    {
        return this.initializationDate;
    }

    /**
     * Возвращает размер данной коллекции.
     * @return Размер внутреннего стэка коллекции.
     */
    public int size()
    {
        return this.organizations.size();
    }

    /**
     * Парсит и добавляет в коллекцию элемент.
     * @param string
     */
    public void addFromCsv(String string)
    {
        Organization org = Organization.fromCsv(string);
        this.organizations.add(org);

        if (org.getCreationDate().before(this.initializationDate))
        {
            this.initializationDate = org.getCreationDate();
        }
    }

    /**
     * 
     * @param organization
     */
    public void addNew(Organization organization)
    {
        this.organizations.add(organization);
    }

    public void clear()
    {
        this.organizations.clear();
    }

    public void reorder()
    {
        Collections.reverse(this.organizations);
    }
}
