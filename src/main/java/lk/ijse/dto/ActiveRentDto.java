package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveRentDto {
    private String rentId;
    private String cusId;
    private String carId;
    private Double balanceToPay;
}
