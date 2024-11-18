package samuelecastaldo.Capstone.Corso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import samuelecastaldo.Capstone.entities.Utente;
import samuelecastaldo.Capstone.exceptions.BadRequestException;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/corso")
public class CorsoController {

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }


    @Autowired
    CorsoService corsoService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Corso> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        try {
            return this.corsoService.findAll(page, size, sortBy);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero del corso: " + e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Corso save(@RequestBody @Validated CorsoDTO body, BindingResult validationResult, @AuthenticationPrincipal Utente currentAuthenticatedUser) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        try {
            return this.corsoService.save(body, currentAuthenticatedUser);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio del corso: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")  // Il permesso per aggiornare il corso
    @ResponseStatus(HttpStatus.OK)
    public Corso updateCorso(@PathVariable long id,
                             @RequestBody @Validated CorsoDTO body,
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
            return this.corsoService.findByIdAndUpdate(id, body, currentAuthenticatedUser);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'aggiornamento del corso: " + e.getMessage());
        }
    }

    //l' eliminazione deve avvenire solamente per un organizzatore
    //con lo stesso id utente di chi lo ha creato
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id, @AuthenticationPrincipal Utente currentAuthenticatedUser) {
        try {
            this.corsoService.findByIdAndDelete(id, currentAuthenticatedUser);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Corso con id " + id + " non trovata.");
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione della fattura: " + e.getMessage());
        }
    }

}
