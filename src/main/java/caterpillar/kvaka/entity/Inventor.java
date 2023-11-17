package caterpillar.kvaka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventors")
public class Inventor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventor_id")
    private int inventor_id;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Inventor(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return " " + inventor_id + " " + lastname + " " + firstname;
    }
}
