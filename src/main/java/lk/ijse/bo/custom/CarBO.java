package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CarDto;
import lk.ijse.entity.Car;

import java.util.List;

public interface CarBO extends SuperBO {
    List<CarDto> getAll();

    String getNewCarId();

    List<String> getCategories();

    boolean addCar(CarDto carDto);

    CarDto getCar(String text);

    boolean updateCar(CarDto carDto);

    boolean deleteCar(CarDto car);
}
