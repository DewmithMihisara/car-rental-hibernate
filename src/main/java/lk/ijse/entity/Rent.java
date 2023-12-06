package lk.ijse.entity;

import javax.persistence.*;
import java.sql.Date;
import lombok.*;

@Entity
@Table(name = "rent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Rent {
    @Id
    @Column(name = "RentId",nullable = false,length = 50)
    private String id;

    @Column(name = "rentDate",nullable = false,columnDefinition = "date")
    private Date date;

    @Column(name = "StartDate",nullable = false,columnDefinition = "date")
    private Date startDate;

    @Column(name = "EndDate",nullable = false,columnDefinition = "date")
    private Date endDate;

    @Column(name = "rate",nullable = false)
    private Double rate;

    @Column(name = "total",nullable = false)
    private Double total;

    @Column(name = "deposit",nullable = false)
    private Double deposit;

    @Column(name = "advancedPayment",nullable = false)
    private Double advancedPayment;

    @Column(name = "isActive",nullable = false)
    private Boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CarId",nullable = false)
    private Car carEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CustId",nullable = false)
    private Customer customerEntity;


}
