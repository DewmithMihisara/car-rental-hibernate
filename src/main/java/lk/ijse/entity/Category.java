package lk.ijse.entity;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "carcategory")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Category {

    @Id
    @Column(name = "CatId",nullable = false,length = 50)
    private String id;

    @Column(name = "Catname",length = 100, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "categoryEntity",targetEntity = Car.class)
    List<Car> carEntities;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
