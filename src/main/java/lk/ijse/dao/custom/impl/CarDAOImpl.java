package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dao.custom.CarDAO;
import lk.ijse.entity.Car;

import java.util.List;

public class CarDAOImpl implements CarDAO {
    @Override
    public boolean save(Car entity){
        return false;
    }

    @Override
    public boolean update(Car entity){
        return false;
    }

    @Override
    public boolean delete(Car id){
        return false;
    }

    @Override
    public Car get(String s){
        return null;
    }

    @Override
    public List<Car> getAll(){
        return null;
    }
}
