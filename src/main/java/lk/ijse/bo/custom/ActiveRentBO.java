package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.RentDto;

import java.util.List;

public interface ActiveRentBO extends SuperBO {
    List<RentDto> getAllActive();
}
