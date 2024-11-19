package samuelecastaldo.Capstone.Argomento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import samuelecastaldo.Capstone.Corso.Corso;
import samuelecastaldo.Capstone.Corso.CorsoController;
import samuelecastaldo.Capstone.Corso.CorsoDTO;
import samuelecastaldo.Capstone.entities.Utente;
import samuelecastaldo.Capstone.exceptions.BadRequestException;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/argomento")
public class ArgomentoController {

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @Autowired
    ArgomentoService argomentoService;

    //----------------------------------------------
    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Argomento> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        try {
            return this.argomentoService.findAll(page, size, sortBy);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero del corso: " + e.getMessage());
        }
    }

    //----------------------------------------------
    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Argomento save(@RequestBody @Validated ArgomentoDTO body, BindingResult validationResult, @AuthenticationPrincipal Utente currentAuthenticatedUser) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        try {
            return this.argomentoService.save(body, currentAuthenticatedUser);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio del corso: " + e.getMessage());
        }
    }

    //----------------------------------------------
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")  // Il permesso per aggiornare il corso
    @ResponseStatus(HttpStatus.OK)
    public Argomento updateCorso(@PathVariable long id,
                             @RequestBody @Validated ArgomentoDTO body,
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
            return this.argomentoService.findByIdAndUpdate(id, body, currentAuthenticatedUser);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'aggiornamento del corso: " + e.getMessage());
        }
    }


    //con lo stesso id utente di chi lo ha creato
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id, @AuthenticationPrincipal Utente currentAuthenticatedUser) {
            this.argomentoService.findByIdAndDelete(id, currentAuthenticatedUser);

    }


}
