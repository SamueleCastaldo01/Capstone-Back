package samuelecastaldo.Capstone.Corso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samuelecastaldo.Capstone.entities.Utente;

import java.util.List;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Long> {
    boolean existsByNomeCorso(String nomeCorso);
    List<Corso> findByUtente(Utente utente);


}
