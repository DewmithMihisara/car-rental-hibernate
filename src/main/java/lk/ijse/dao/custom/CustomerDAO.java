package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Customer;
import org.hibernate.Session;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    String getNextId();

    Customer getItem(String text);

    boolean updateAsRent(Session session, Customer customer);

    Customer getItem(String customerId, Session session);
}
