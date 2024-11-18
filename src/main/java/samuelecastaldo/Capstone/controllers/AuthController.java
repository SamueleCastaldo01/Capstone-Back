package samuelecastaldo.Capstone.controllers;


import samuelecastaldo.Capstone.entities.Utente;
import samuelecastaldo.Capstone.exceptions.BadRequestException;
import samuelecastaldo.Capstone.payloads.NewUtenteDTO;
import samuelecastaldo.Capstone.payloads.UserLoginDTO;
import samuelecastaldo.Capstone.payloads.UserLoginResponseDTO;
import samuelecastaldo.Capstone.services.AuthService;
import samuelecastaldo.Capstone.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService dipendenteService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente save(@RequestBody @Validated NewUtenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.dipendenteService.save(body);
    }
}
