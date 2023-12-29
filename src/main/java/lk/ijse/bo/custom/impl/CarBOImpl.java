package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CarBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CarDAO;
import lk.ijse.dao.custom.CategoryDAO;
import lk.ijse.dto.CarDto;
import lk.ijse.dto.CategoryDto;
import lk.ijse.entity.Car;
import lk.ijse.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CarBOImpl implements CarBO {
    CarDAO carDAO = (CarDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CAR);
    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CATEGORY);

    @Override
    public List<CarDto> getAll() {
        List<CarDto> carDtos = new ArrayList<>();
        for (Car car : carDAO.getAll()) {
            carDtos.add(new CarDto(
                    car.getId(),
                    car.getNumber(),
                    car.getBrand(),
                    car.getModel(),
                    car.getYear(),
                    car.getRate(),
                    car.getCategoryEntity().getId(),
                    car.getIsRentable(),
                    car.getDepositAmount()
            ));
        }
        return carDtos;
    }

    @Override
    public String getNewCarId() {
        String id=carDAO.getNewId();
        Integer idInt=Integer.parseInt(id.replace("C",""))+1;
        return String.format("C%03d",idInt);
    }

    @Override
    public List<String> getCategories() {
        List<String>cat=new ArrayList<>();
        for(Category category: categoryDAO.getAll()){
            cat.add(category.getId());
        }
        return cat;
    }

    @Override
    public boolean addCar(CarDto carDto) {
        return carDAO.save(new Car(
                carDto.getId(),
                carDto.getNumber(),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getYear(),
                carDto.getRate(),
                carDto.getIsRentable(),
                carDto.getDepositAmount(),
                categoryDAO.get(carDto.getCatId())
        ));
    }

    @Override
    public CarDto getCar(String text) {
        Car car=carDAO.get(text);
        if (car!=null){
            return new CarDto(
                    car.getId(),
                    car.getNumber(),
                    car.getBrand(),
                    car.getModel(),
                    car.getYear(),
                    car.getRate(),
                    car.getCategoryEntity().getId(),
                    car.getIsRentable(),
                    car.getDepositAmount()
            );
        }
        return null;
    }

    @Override
    public boolean updateCar(CarDto carDto) {
        return carDAO.update(new Car(
                carDto.getId(),
                carDto.getNumber(),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getYear(),
                carDto.getRate(),
                carDto.getIsRentable(),
                carDto.getDepositAmount(),
                categoryDAO.get(carDto.getCatId())
        ));
    }

    @Override
    public boolean deleteCar(CarDto car){
        return carDAO.delete(new Car(
                car.getId(),
                car.getNumber(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getRate(),
                car.getIsRentable(),
                car.getDepositAmount(),
                categoryDAO.get(car.getCatId())
        ));
    }

    @Override
    public List<String> getIds() {
        List<String>ids=new ArrayList<>();
        for(Car car:carDAO.getAll()){
            ids.add(car.getId());
        }
        return ids;
    }
}
