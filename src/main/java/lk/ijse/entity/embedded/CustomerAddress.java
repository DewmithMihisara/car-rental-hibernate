package lk.ijse.entity.embedded;
import javax.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class CustomerAddress {

    @Column(length = 100,nullable = false)
    private String street;

    @Column( length = 100,nullable = false)
    private String city;

    @Column(length = 100,nullable = false)
    private String district;
}
