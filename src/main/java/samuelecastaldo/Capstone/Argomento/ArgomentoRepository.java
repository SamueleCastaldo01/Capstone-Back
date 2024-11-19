package samuelecastaldo.Capstone.Argomento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import samuelecastaldo.Capstone.Corso.Corso;
import samuelecastaldo.Capstone.entities.Utente;

import java.util.List;

@Repository
public interface ArgomentoRepository extends JpaRepository<Argomento, Long> {
    List<Argomento> findByCorsoId(long id);

    @Query("SELECT a FROM Argomento a WHERE a.utente.id = :userId")
    List<Argomento> findByUtenteId(@Param("userId") Long userId);



}
