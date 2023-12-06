package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarTM {
    private String id;
    private String number;
    private String brand;
    private String model;
    private double rate;
    private boolean toRent;
}
