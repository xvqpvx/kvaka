package caterpillar.kvaka.dto;

import lombok.Data;

@Data
public class InventorsInventionsDto {
    private int id_record;
    private int id_inventor;
    private int id_invention;
    private String firstname;
    private String lastname;
    private String invention;

    public InventorsInventionsDto(int idRecord, int idInventor, int idInvention, String firstname, String lastname, String invention) {
        this.id_record = idRecord;
        this.id_inventor = idInventor;
        this.id_invention = idInvention;
        this.firstname = firstname;
        this.lastname = lastname;
        this.invention = invention;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname + " " + invention;
    }
}
