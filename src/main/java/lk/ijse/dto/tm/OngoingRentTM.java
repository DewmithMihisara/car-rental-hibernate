package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OngoingRentTM {
    private String rentId;
    private String customerName;
    private String carNumber;
    private String returnDate;
    private String overdue;
    private String balanceToPay;
    private String rentDate;
}
