package caterpillar.kvaka.repos;

import caterpillar.kvaka.dto.InventorsInventionsDto;
import caterpillar.kvaka.entity.InventorsInventions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventorsInventionsRepo extends JpaRepository<InventorsInventions, Integer> {
    @Query("SELECT new caterpillar.kvaka.dto.InventorsInventionsDto(ii.id_record, i.inventor_id, inv.invention_id, i.firstname, i.lastname, inv.invention) " +
            "FROM InventorsInventions ii " +
            "JOIN ii.inventor i " +
            "JOIN ii.invention inv")
    List<InventorsInventionsDto> getAllInventorsAndInventions();
}