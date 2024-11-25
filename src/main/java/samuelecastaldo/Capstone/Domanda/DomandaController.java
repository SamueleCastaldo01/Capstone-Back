package samuelecastaldo.Capstone.Domanda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import samuelecastaldo.Capstone.Argomento.Argomento;
import samuelecastaldo.Capstone.entities.Utente;
import samuelecastaldo.Capstone.exceptions.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/domanda")
public class DomandaController {

    @Autowired
    DomandaService domandaService;

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Domanda> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        try {
            return this.domandaService.findAll(page, size, sortBy);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero del corso: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Domanda getArgomentoById(@PathVariable long id) {
        return domandaService.findById(id);
    }

    @GetMapping("/argomento/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<Domanda> getArgomentiByCorsoId(@PathVariable long id) {
        return domandaService.findByIdArgomento(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Domanda save(@RequestBody @Validated DomandaDTO body, BindingResult validationResult, @AuthenticationPrincipal Utente currentAuthenticatedUser) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        try {
            return this.domandaService.save(body, currentAuthenticatedUser);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio del corso: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")  // Il permesso per aggiornare il corso
    @ResponseStatus(HttpStatus.OK)
    public Domanda updateCorso(@PathVariable long id,
                             @RequestBody @Validated DomandaDTO body,
                             BindingResult validationResult,
                             @AuthenticationPrincipal Utente currentAuthenticatedUser) {
        // Verifica se ci sono errori di validazione nel body della richiesta
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        try {
            return this.domandaService.findByIdAndUpdate(id, body, currentAuthenticatedUser);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'aggiornamento del corso: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id, @AuthenticationPrincipal Utente currentAuthenticatedUser) {
        try {
            this.domandaService.findByIdAndDelete(id, currentAuthenticatedUser);
        } catch (DomandaController.ResourceNotFoundException e) {
            throw new DomandaController.ResourceNotFoundException("Domanda con id " + id + " non trovata.");
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione della fattura: " + e.getMessage());
        }
    }

}
