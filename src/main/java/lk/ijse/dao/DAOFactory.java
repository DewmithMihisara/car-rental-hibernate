package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public enum DAOTypes {
        CAR, CATEGORY, CUSTOMER, USER, RENT
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case USER:
                return new UserDAOImpl();
            case CAR:
                return new CarDAOImpl();
            case CATEGORY:
                return new CategoryDAOImpl();
            case RENT:
                return new RentDAOImpl();
            default:
                return null;
        }
    }
}
