package samuelecastaldo.Capstone.Argomento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samuelecastaldo.Capstone.Argomento.Argomento;
import samuelecastaldo.Capstone.Argomento.ArgomentoDTO;
import samuelecastaldo.Capstone.Corso.Corso;
import samuelecastaldo.Capstone.Corso.CorsoService;
import samuelecastaldo.Capstone.entities.Utente;
import samuelecastaldo.Capstone.exceptions.BadRequestException;
import samuelecastaldo.Capstone.exceptions.NotFoundException;

import java.util.List;

@Service
public class ArgomentoService {
    @Autowired
    ArgomentoRepository argomentoRepository;

    @Autowired
    CorsoService corsoService;

    public Page<Argomento> findAll(int page, int size, String sortBy) {
        try {
            if (size > 100) size = 100;
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return this.argomentoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero delle fatture: " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------
    @Transactional
    public List<Argomento> findByUtente(Utente utente) {
        return argomentoRepository.findByUtenteId(utente.getId());  // Utilizza la query personalizzata
    }

    //------------------------------------------------------------------------

    public Argomento findById(long id) {
        try {
            return this.argomentoRepository.findById(id).orElseThrow(() -> new NotFoundException("Argomento con id " + id + " non trovata"));
        } catch (NotFoundException e) {
            throw e; // Rilancia l'eccezione NotFoundException
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero della fattura: " + e.getMessage());
        }
    }

    @Transactional
    public List<Argomento> findByCorsoId(long id) {
        return argomentoRepository.findByCorsoIdOrderByDataCreazione(id);
    }

    //POST --------------------------------------------
    public Argomento save(ArgomentoDTO body, Utente utente) {
        try {
            Corso corso = corsoService.findById(body.id_corso());

            Argomento newArgomento = new Argomento(body.titolo(), corso, body.contenuto(), utente);
            return argomentoRepository.save(newArgomento);

        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio del corso: " + e.getMessage());
        }
    }

    //PUT --------------------------------------------
    public Argomento findByIdAndUpdate(long id, ArgomentoDTO body, Utente utente) {
        Argomento found = findById(id); //trova prima l'argomento tramite id
        if (found.getUtente().getId() != utente.getId()) {
            throw new BadRequestException("Non hai i permessi per modificare questo argomento");
        }
        found.setTitolo(body.titolo());
        found.setContenuto(body.contenuto());
        return this.argomentoRepository.save(found);
    }


    //DELETE --------------------------------------------
    public void findByIdAndDelete(long id, Utente utente) {
        try {
            Argomento found = this.findById(id);
            if (found.getUtente().getId() != utente.getId()) {
                throw new BadRequestException("Non hai i permessi per eliminare questo argomento");
            }
            this.argomentoRepository.delete(found);
        } catch (BadRequestException e) {
            throw e; // Rilancia l'eccezione BadRequestException
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione del argomento: " + e.getMessage());
        }
    }

}
