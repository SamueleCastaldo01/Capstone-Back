package samuelecastaldo.Capstone.Argomento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArgomentoRepository extends JpaRepository<Argomento, Long> {
}
