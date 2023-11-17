package caterpillar.kvaka.repos;

import caterpillar.kvaka.entity.Invention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventionRepo extends JpaRepository<Invention, Integer> {
}
