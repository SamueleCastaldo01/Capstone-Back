package samuelecastaldo.Capstone.Corso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import samuelecastaldo.Capstone.entities.Utente;
import samuelecastaldo.Capstone.exceptions.BadRequestException;
import samuelecastaldo.Capstone.exceptions.NotFoundException;

@Service
public class CorsoService {

    @Autowired
    CorsoRepository corsoRepository;

    public Page<Corso> findAll(int page, int size, String sortBy) {
        try {
            if (size > 100) size = 100;
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return this.corsoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero delle fatture: " + e.getMessage());
        }
    }

    public Corso findById(long id) {
        try {
            return this.corsoRepository.findById(id).orElseThrow(() -> new NotFoundException("Corso con id " + id + " non trovato"));
        } catch (NotFoundException e) {
            throw e; // Rilancia l'eccezione NotFoundException
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero della fattura: " + e.getMessage());
        }
    }

    //POST --------------------------------------------
    public Corso save(CorsoDTO body, Utente utente) {
        try {
            if (corsoRepository.existsByNomeCorso(body.nomeCorso())) {
                throw new BadRequestException("Un corso con questo nome esiste già.");
            }

            Corso newCorso = new Corso(utente, body.nomeCorso());
            return corsoRepository.save(newCorso);

        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio del corso: " + e.getMessage());
        }
    }

    //POST --------------------------------------------
    public Corso findByIdAndUpdate(long id, CorsoDTO body, Utente utente) {
        Corso found = findById(id);
        if (found.getUtente().getId() != utente.getId()) {
            throw new BadRequestException("Non hai i permessi per eliminare questa fattura");
        }
        found.setNomeCorso(body.nomeCorso());
        return this.corsoRepository.save(found);
    }

    //PUT --------------------------------------------

    //DELETE --------------------------------------------
    public void findByIdAndDelete(long id, Utente utente) {
        try {
            Corso found = this.findById(id);
            if (found.getUtente().getId() != utente.getId()) {
                throw new BadRequestException("Non hai i permessi per eliminare questa fattura");
            }
            this.corsoRepository.delete(found);
        } catch (BadRequestException e) {
            throw e; // Rilancia l'eccezione BadRequestException
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione del corso: " + e.getMessage());
        }
    }

}
