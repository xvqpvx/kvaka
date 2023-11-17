package caterpillar.kvaka.service.impl;

import caterpillar.kvaka.entity.Invention;
import caterpillar.kvaka.entity.Status;
import caterpillar.kvaka.repos.InventionRepo;
import caterpillar.kvaka.service.InventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventionServiceImpl implements InventionService {

    private final InventionRepo inventionRepo;

    @Autowired
    public InventionServiceImpl(InventionRepo inventionRepo) {
        this.inventionRepo = inventionRepo;
    }

    @Override
    public List<Invention> findAllInventions() {
        return inventionRepo.findAll();
    }

    @Override
    public Invention findInventionById(int invention_id) {
        return inventionRepo.findById(invention_id)
                .orElseThrow(() -> new RuntimeException("Invention not found with id " + invention_id));
    }

    @Override
    public void deleteInventionById(int invention_id) {
        Invention invention = inventionRepo.findById(invention_id)
                .orElseThrow(() -> new RuntimeException("Invention not found with id " + invention_id));
        invention.setStatus(Status.NOT_ACTIVE);

        inventionRepo.save(invention);
    }

    @Override
    public Invention save(Invention invention) {
        return inventionRepo.save(invention);
    }

}
