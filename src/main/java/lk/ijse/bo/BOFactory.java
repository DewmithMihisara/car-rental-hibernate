package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory factory;
    private BOFactory(){}
    public static BOFactory getInstance(){
        return factory == null ? new BOFactory() : factory;
    }
    public enum BOTypes{
        CAR, CATEGORY, CUSTOMER, USER, RENT
    }
    public <T extends SuperBO>T getBO(BOTypes types){
        switch (types){
            case CAR:
                return (T) new CarBOImpl();
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case CATEGORY:
                return (T) new CategoryBOImpl();
            case USER:
                return (T) new UserBOImpl();
            case RENT:
                return (T) new RentBOImpl();
            default:
                return null;
        }
    }
}
