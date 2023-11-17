package caterpillar.kvaka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventors_inventions")
public class InventorsInventions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_record")
    private int id_record;

    @Column(name = "id_inventor")
    private int id_inventor;

    @Column(name = "id_invention")
    private int id_invention;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_inventor", referencedColumnName = "inventor_id", insertable = false, updatable = false)
    private Inventor inventor;

    @ManyToOne
    @JoinColumn(name = "id_invention", referencedColumnName = "invention_id", insertable = false, updatable = false)
    private Invention invention;

    public InventorsInventions(int id_record, int id_inventor, int id_invention) {
        this.id_record = id_record;
        this.id_inventor = id_inventor;
        this.id_invention = id_invention;
    }

    @Override
    public String toString() {
        return  "id_record=" + id_record +
                ", id_inventor=" + id_inventor +
                ", id_invention=" + id_invention;
    }
}
