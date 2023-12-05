package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarDto {

    private Integer id;
    private String number;
    private String brand;
    private String model;
    private Integer year;
    private Double rate;
    private Integer catId;
    private Boolean isRentable;
    private Double depositAmount;


    public CarDto(String number, String brand, String model, Integer year, Double rate, Integer catId) {
        this.number = number;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rate = rate;
        this.catId = catId;
    }



}
