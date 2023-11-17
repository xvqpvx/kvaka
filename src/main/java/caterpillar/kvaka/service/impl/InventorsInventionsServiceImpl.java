package caterpillar.kvaka.service.impl;

import caterpillar.kvaka.dto.InventorsInventionsDto;
import caterpillar.kvaka.entity.InventorsInventions;
import caterpillar.kvaka.entity.Status;
import caterpillar.kvaka.repos.InventorsInventionsRepo;
import caterpillar.kvaka.service.InventorsInventionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventorsInventionsServiceImpl implements InventorsInventionsService {

    private final InventorsInventionsRepo recordsRepo;

    @Autowired
    public InventorsInventionsServiceImpl(InventorsInventionsRepo recordsRepo) {
        this.recordsRepo = recordsRepo;
    }

    @Override
    public InventorsInventions findRecordById(int idRecord) {
        return recordsRepo.findById(idRecord)
                .orElseThrow(() -> new RuntimeException("Record not found with id " + idRecord));
    }

    @Override
    public void deleteRecordById(int idRecord) {
        InventorsInventions record = recordsRepo.findById(idRecord)
                .orElseThrow(() -> new RuntimeException("Record not found with id " + idRecord));
        record.setStatus(Status.NOT_ACTIVE);
    }

    @Override
    public InventorsInventions save(InventorsInventions newRecord) {
        return recordsRepo.save(newRecord);
    }

    @Override
    public List<InventorsInventionsDto> getInventorsAndInventions() {
        return recordsRepo.getAllInventorsAndInventions();
    }

    @Override
    public List<InventorsInventions> findAll(int page, int size, String sortField, Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));
        Page<InventorsInventions> resultPage = recordsRepo.findAll(pageable);
        return resultPage.getContent();
    }
}
