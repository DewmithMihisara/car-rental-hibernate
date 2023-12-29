package lk.ijse.entity;

import javax.persistence.*;
import java.sql.Date;
import lk.ijse.entity.embedded.CustomerAddress;
import lk.ijse.entity.embedded.CustomerFullName;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    @Id
    @Column(name = "CustId",nullable = false,length = 50)
    private String id;

    @Column(name = "UserName",length = 50,nullable = false)
    private String userName;

    @Column(name = "email",length = 225,nullable = false)
    private String email;

    private CustomerFullName fullName;

    private CustomerAddress address;

    @Column(name = "postalcode",length = 20,nullable = false)
    private String postalCode;

    @Column(name = "returnDate", length = 20)
    private LocalDate toReturn;

   @Column(name = "mobile",length = 20)
    private String mobiles;

    @OneToMany(mappedBy = "customerEntity",targetEntity = Rent.class)
    List<Rent> rentEntities;


    public Customer(String id, String userName, String email, CustomerFullName customerFullName, CustomerAddress customerAddress, String postalCode, String mobile) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.fullName = customerFullName;
        this.address = customerAddress;
        this.postalCode = postalCode;
        this.mobiles = mobile;
    }
}
