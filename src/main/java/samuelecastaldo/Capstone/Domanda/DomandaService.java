package samuelecastaldo.Capstone.Domanda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samuelecastaldo.Capstone.Argomento.Argomento;
import samuelecastaldo.Capstone.Argomento.ArgomentoService;
import samuelecastaldo.Capstone.Corso.Corso;
import samuelecastaldo.Capstone.Corso.CorsoDTO;
import samuelecastaldo.Capstone.entities.Utente;
import samuelecastaldo.Capstone.exceptions.BadRequestException;
import samuelecastaldo.Capstone.exceptions.NotFoundException;

import java.util.List;

@Service
public class DomandaService {

    @Autowired
    DomandaRepository domandaRepository;

    @Autowired
    ArgomentoService argomentoService;

    //GET
    public Page<Domanda> findAll(int page, int size, String sortBy) {
        try {
            if (size > 100) size = 100;
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return this.domandaRepository.findAll(pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero delle fatture: " + e.getMessage());
        }
    }

    public Domanda findById(long id) {
        try {
            return this.domandaRepository.findById(id).orElseThrow(() -> new NotFoundException("Corso con id " + id + " non trovato"));
        } catch (NotFoundException e) {
            throw e; // Rilancia l'eccezione NotFoundException
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero della fattura: " + e.getMessage());
        }
    }

    @Transactional
    public List<Domanda> findByIdArgomento(Long idArgomento) {
        return domandaRepository.findByArgomentoId(idArgomento);
    }

    @Transactional
    public List<Domanda> findByIdCorso(Long idCorso) {
        return domandaRepository.findByIdCorso(idCorso);
    }

    //POST --------------------------------------------
    public Domanda save(DomandaDTO body, Utente utente) {
        Argomento argomento = argomentoService.findById(body.id_argomento());
        try {
            Domanda newDomanda = new Domanda(body.domanda(), body.rispostaDomanda(), body.rispostaS2(), body.rispostaS3(), body.rispostaS4(), argomento, utente);
            return domandaRepository.save(newDomanda);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio del corso: " + e.getMessage());
        }
    }

    //PUT --------------------------------------------
    public Domanda findByIdAndUpdate(long id, DomandaDTO body, Utente utente) {
        Domanda found = findById(id);

        if (found.getUtente().getId() != utente.getId()) {
            throw new BadRequestException("Non hai i permessi per eliminare appartiene ad un altro utente");
        }
        found.setDomanda(body.domanda());
        found.setRispostaDomanda(body.rispostaDomanda());
        found.setRispostaS2(body.rispostaS2());
        found.setRispostas3(body.rispostaS3());
        found.setRispostas4(body.rispostaS4());

        return this.domandaRepository.save(found);
    }

    //DELETE --------------------------------------------
    public void findByIdAndDelete(long id, Utente utente) {
        try {
            Domanda found = this.findById(id);
            if (found.getUtente().getId() != utente.getId()) {
                throw new BadRequestException("Non hai i permessi per eliminare appartiene ad un altro utente");
            }
            this.domandaRepository.delete(found);
        } catch (BadRequestException e) {
            throw e; // Rilancia l'eccezione BadRequestException
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione del corso: " + e.getMessage());
        }
    }

}
