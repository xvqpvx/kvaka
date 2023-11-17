package caterpillar.kvaka.service.impl;

import caterpillar.kvaka.entity.Inventor;
import caterpillar.kvaka.entity.Status;
import caterpillar.kvaka.repos.InventorRepo;
import caterpillar.kvaka.service.InventorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventorServiceImpl implements InventorService {

    private final InventorRepo inventorRepo;

    @Autowired
    public InventorServiceImpl(InventorRepo inventorRepo) {
        this.inventorRepo = inventorRepo;
    }

    @Override
    public List<Inventor> findAllInventors() {
        return inventorRepo.findAll();
    }

    @Override
    public Inventor findInventorById(int inventor_id) {
        return inventorRepo.findById(inventor_id)
                .orElseThrow(() -> new RuntimeException("Inventor not found with id " + inventor_id));
    }

    @Override
    public void deleteInventorById(int inventor_id) {
        Inventor inventor = inventorRepo.findById(inventor_id)
                .orElseThrow(() -> new RuntimeException("Inventor not found with id " + inventor_id));
        inventor.setStatus(Status.NOT_ACTIVE);
        inventorRepo.deleteById(inventor_id);
    }

    @Override
    public Inventor save(Inventor inventor) {
        return inventorRepo.save(inventor);
    }
}
