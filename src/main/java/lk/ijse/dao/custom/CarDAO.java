package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Car;
import org.hibernate.Session;

public interface CarDAO extends CrudDAO<Car,String> {
    String getNewId();

    boolean updateAsRent(Session session, Car car);

    Car getCarByNum(String carNumber);

    Car getCarByNum(String carNumber, Session session);
}
