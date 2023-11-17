package caterpillar.kvaka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventions")
public class Invention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invention_id")
    private int invention_id;

    @Column(name = "invention")
    private String invention;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Invention(String invention) {
        this.invention = invention;
    }

    @Override
    public String toString() {
        return invention_id + " " + invention + '\'';
    }
}






