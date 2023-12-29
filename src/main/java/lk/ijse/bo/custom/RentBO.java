package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.RentDto;

public interface RentBO extends SuperBO {
    String getNewCarId();

    boolean placeRent(RentDto rentDto);

    RentDto getRent(String text);
    String closeRent(RentDto rentDto);
}
