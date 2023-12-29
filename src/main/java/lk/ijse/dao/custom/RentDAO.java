package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Rent;
import org.hibernate.Session;

public interface RentDAO extends CrudDAO<Rent, String> {
    String getNewId();

    boolean addRent(Rent rent, Session session);
}
