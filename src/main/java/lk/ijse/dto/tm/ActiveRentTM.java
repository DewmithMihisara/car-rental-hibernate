package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveRentTM {
    private String rentId;
    private String cusName;
    private String carNumber;
    private String rentDate;
    private String returnDate;
    private String overView;
    private String balanceToPay;
}
