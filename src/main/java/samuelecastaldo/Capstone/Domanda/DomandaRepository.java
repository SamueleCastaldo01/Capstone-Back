package samuelecastaldo.Capstone.Domanda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomandaRepository extends JpaRepository<Domanda, Long> {
    List<Domanda> findByArgomentoId(Long argomentoId);
    List<Domanda> findByIdCorso(Long idCorso);
}
