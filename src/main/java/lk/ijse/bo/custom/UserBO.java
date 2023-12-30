package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDto;

import java.util.List;

public interface UserBO extends SuperBO {
    List<UserDto> getAllUsers();

    boolean saveUser(UserDto userDto);

    String getNewUserId();

    UserDto getUser(String text);

    boolean updateUser(UserDto userDto);

    boolean deleteUser(UserDto userDto);

    UserDto getUserByUserName(String text);
}
