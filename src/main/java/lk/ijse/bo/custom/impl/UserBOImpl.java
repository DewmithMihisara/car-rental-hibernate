package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.CarDto;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.Car;
import lk.ijse.entity.User;
import lk.ijse.entity.embedded.UserFullName;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO= (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : userDAO.getAll()) {
            userDtos.add(new UserDto(
                    user.getId(),
                    user.getUserName(),
                    user.getPassword(),
                    user.getRole(),
                    user.getFullName().getFirstName(),
                    user.getFullName().getLastName()
            ));
        }
        return userDtos;
    }

    @Override
    public boolean saveUser(UserDto userDto) {
        UserFullName userFullName = new UserFullName(
                userDto.getFirstName(),
                userDto.getLastName()
        );
        return userDAO.save(new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getPassWord(),
                userFullName,
                userDto.getRole()
        ));
    }

    @Override
    public String getNewUserId() {
        String id=userDAO.getNewId();
        Integer idInt=Integer.parseInt(id.replace("U",""))+1;
        return String.format("U%03d",idInt);
    }

    @Override
    public UserDto getUser(String text) {
        User user = userDAO.get(text);
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getRole(),
                user.getFullName().getFirstName(),
                user.getFullName().getLastName()
        );
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        UserFullName userFullName = new UserFullName(
                userDto.getFirstName(),
                userDto.getLastName()
        );
        return userDAO.update(new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getPassWord(),
                userFullName,
                userDto.getRole()
        ));
    }

    @Override
    public boolean deleteUser(UserDto userDto) {
        UserFullName userFullName = new UserFullName(
                userDto.getFirstName(),
                userDto.getLastName()
        );
        return userDAO.delete(new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getPassWord(),
                userFullName,
                userDto.getRole()
        ));
    }
}
