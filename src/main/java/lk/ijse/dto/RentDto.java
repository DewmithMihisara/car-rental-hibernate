package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RentDto {

    private String id;
    private LocalDate rentDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double advancePayment;
    private Double depositAmount;
    private String customerId;
    private String carCategory;
    private String carNumber;
    private Double rate;
    private Double total;
    private Boolean isActive;
}
