package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.RentBO;
import lk.ijse.configaration.SessionFactoryConfig;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CarDAO;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.RentDAO;
import lk.ijse.dto.RentDto;
import lk.ijse.entity.Car;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Rent;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RentBOImpl implements RentBO {
    Session session= SessionFactoryConfig.getInstance().getSession();
    RentDAO rentDAO= (RentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RENT);
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    CarDAO carDAO= (CarDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CAR);
    @Override
    public String getNewCarId() {
        String id=rentDAO.getNewId();
        Integer idInt=Integer.parseInt(id.replace("R",""))+1;
        return String.format("R%03d",idInt);
    }

    @Override
    public boolean placeRent(RentDto rentDto) {
        Customer customer=customerDAO.getItem(rentDto.getCustomerId());
        Car car=carDAO.get(rentDto.getCarNumber());

        Rent rent=new Rent(
                rentDto.getId(),
                rentDto.getRentDate(),
                rentDto.getStartDate(),
                rentDto.getEndDate(),
                rentDto.getRate(),
                rentDto.getTotal(),
                rentDto.getDepositAmount(),
                rentDto.getAdvancePayment(),
                true,
                car,
                customer
        );
        Transaction transaction=session.beginTransaction();

        if (rentDAO.addRent(rent,session)) {
            customer.setToReturn(rentDto.getEndDate());
            if (customerDAO.updateAsRent(session, customer)) {
                car.setIsRentable(false);
                if (carDAO.updateAsRent(session, car)) {
                    transaction.commit();
                    return true;
                } else {
                    transaction.rollback();
                    return false;
                }
            } else {
                transaction.rollback();
                return false;
            }
        }else {
            transaction.rollback();
            return false;
        }
    }
}
