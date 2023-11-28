package lk.ijse.entity;

import javax.persistence.*;
import lk.ijse.entity.embedded.UserFullName;
import lombok.*;

@Entity
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {
    @Id
    @Column(name = "UserId",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UserName",length = 50,nullable = false,unique = true)
    private String userName;

    @Column(name = "password",length = 100,nullable = false)
    private String password;

    private UserFullName fullName;

    @Column(name = "role",length = 20,nullable = false)
    private String role;

}
