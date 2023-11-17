package caterpillar.kvaka.service;

import caterpillar.kvaka.entity.Invention;
import java.util.List;

public interface InventionService {
    List<Invention> findAllInventions();
    Invention findInventionById(int invention_id);
    void deleteInventionById(int invention_id);
    Invention save(Invention invention);
}
