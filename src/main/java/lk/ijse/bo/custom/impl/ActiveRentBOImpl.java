package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ActiveRentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.RentDAO;
import lk.ijse.dto.CarDto;
import lk.ijse.dto.RentDto;
import lk.ijse.entity.Car;
import lk.ijse.entity.Rent;

import java.util.ArrayList;
import java.util.List;

public class ActiveRentBOImpl implements ActiveRentBO {
    RentDAO rentDAO= (RentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RENT);
    @Override
    public List<RentDto> getAllActive() {
        List<RentDto> rentDtos = new ArrayList<>();
        for (Rent rent : rentDAO.getAllActive()) {
            rentDtos.add(new RentDto(
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
            ));
        }
        return rentDtos;
    }
}
