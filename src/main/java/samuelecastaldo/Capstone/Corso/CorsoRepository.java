package samuelecastaldo.Capstone.Corso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Long> {
    boolean existsByNomeCorso(String nomeCorso);



}
