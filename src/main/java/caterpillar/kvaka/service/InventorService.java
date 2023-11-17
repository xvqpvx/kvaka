package caterpillar.kvaka.service;

import caterpillar.kvaka.entity.Inventor;
import java.util.List;

public interface InventorService {
    List<Inventor> findAllInventors();
    Inventor findInventorById(int inventor_id);
    void deleteInventorById(int inventor_id);
    Inventor save(Inventor inventor);
}
