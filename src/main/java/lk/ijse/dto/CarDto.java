package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarDto {

    private String id;
    private String number;
    private String brand;
    private String model;
    private Integer year;
    private Double rate;
    private String catId;
    private Boolean isRentable;
    private Double depositAmount;


    public CarDto(String number, String brand, String model, Integer year, Double rate, String catId) {
        this.number = number;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rate = rate;
        this.catId = catId;
    }



}
