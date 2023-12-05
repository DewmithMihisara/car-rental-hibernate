package lk.ijse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OngoingRentDto {

    private Integer rentId;
    private Integer cusId;
    private Integer carId;
    private Double balanceToPay;
}
