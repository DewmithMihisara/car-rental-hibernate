package lk.ijse.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    private String id;
    private String userName;
    private String passWord;
    private String role;
    private String firstName;
    private String lastName;

}
