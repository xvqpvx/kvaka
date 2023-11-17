package caterpillar.kvaka.service;

import caterpillar.kvaka.dto.InventorsInventionsDto;
import caterpillar.kvaka.entity.InventorsInventions;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface InventorsInventionsService {
    InventorsInventions findRecordById(int idRecord);
    void deleteRecordById(int idRecord);
    InventorsInventions save(InventorsInventions newRecord);
    List<InventorsInventionsDto> getInventorsAndInventions();
    List<InventorsInventions> findAll(int page, int size, String sortField, Sort.Direction sortDirection);
}
