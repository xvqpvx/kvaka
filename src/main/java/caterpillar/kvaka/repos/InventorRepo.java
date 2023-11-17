package caterpillar.kvaka.repos;

import caterpillar.kvaka.entity.Inventor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventorRepo extends JpaRepository<Inventor, Integer> {
}
