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

import java.time.LocalDate;

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
        System.out.println(customer == null);
        Car car=carDAO.getCarByNum(rentDto.getCarNumber());
        System.out.println(car == null);
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
        if (rentDAO.addRent(rent, session)){
            customer.setToReturn(rentDto.getEndDate());
            if (customerDAO.updateAsRent(session, customer)) {
                System.out.println("customer ok");
                car.setIsRentable(false);
                if (carDAO.updateAsRent(session, car)) {
                    System.out.println("car ok");
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

    @Override
    public RentDto getRent(String text) {
        Rent rent=rentDAO.get(text);
        return new RentDto(
                rent.getId(),
                rent.getDate(),
                rent.getStartDate(),
                rent.getEndDate(),
                rent.getAdvancedPayment(),
                rent.getDeposit(),
                rent.getCustomerEntity().getId(),
                rent.getCarEntity().getCategoryEntity().getId(),
                rent.getCarEntity().getNumber(),
                rent.getRate(),
                rent.getTotal(),
                rent.getIsActive()
        );
    }

    @Override
    public String closeRent(RentDto rentDto) {
        try{
            Transaction transaction=session.beginTransaction();

            Rent rent=rentDAO.get(rentDto.getId(),session);
            Customer customer=customerDAO.getItem(rentDto.getCustomerId(),session);
            Car car=carDAO.getCarByNum(rentDto.getCarNumber(),session);

            rent.setIsActive(false);
            if (rentDAO.updateRent(session, rent)) {

                System.out.println("rent ok");

                customer.setToReturn(null);

                customerDAO.updateAsRent(session, customer);

                Customer customer1=customerDAO.getItem(rentDto.getCustomerId());

                System.out.println(customer1.getToReturn());

                if (customer1.getToReturn()==null){
                    car.setIsRentable(true);
                    System.out.println("customer ok");

                    if (carDAO.updateAsRent(session, car)) {
                        transaction.commit();
                        System.out.println("success");
                        return "success";
                    } else {
                        System.out.println("Car Update Error");
                        transaction.rollback();
                        return "Car Update Error";
                    }
                } else {
                    System.out.println("Customer Update Error");
                    transaction.rollback();
                    return "Customer Update Error";
                }
            } else {
                System.out.println("Rent Save Error");
                transaction.rollback();
                return "Rent Save Error";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }
}
