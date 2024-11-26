package samuelecastaldo.Capstone.Argomento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ArgomentoDTO(
        @NotNull(message = "Il titolo è obbligatorio")
        @Size(min = 1, max = 100, message = "Il titolo deve essere tra 5 e 100 caratteri")
        String titolo,
        @NotNull(message = "L'ID del corso è obbligatorio")
        Long id_corso,
        @NotNull(message = "Il contenuto è obbligatorio")
        String contenuto
) {
}
