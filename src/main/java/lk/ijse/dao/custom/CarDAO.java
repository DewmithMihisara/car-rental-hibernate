package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Car;

public interface CarDAO extends CrudDAO<Car,String> {
    String getNewId();
}
