package lk.ijse.dao;

import lk.ijse.dao.custom.impl.CarDAOImpl;
import lk.ijse.dao.custom.impl.CategoryDAOImpl;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance(){
        return (daoFactory==null)?(daoFactory=new DAOFactory()):daoFactory;
    }
    public enum DAOTypes{
        CAR, CATEGORY, CUSTOMER, USER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case USER:
                return new UserDAOImpl();
            case CAR:
                return new CarDAOImpl();
            case CATEGORY:
                return new CategoryDAOImpl();
            default:
                return null;
        }
    }
}
