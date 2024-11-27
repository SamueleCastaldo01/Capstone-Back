package samuelecastaldo.Capstone.Domanda;

import jakarta.validation.constraints.NotNull;

public record DomandaDTO(
        @NotNull(message = "La domanda è obbligatoria")
        String domanda,
        @NotNull(message = "L'ID del argomento è obbligatorio")
        long id_argomento,
        @NotNull(message = "La risposta corretta è obbligatoria")
        String rispostaDomanda,
        String rispostaS2,
        String rispostaS3,
        String rispostaS4,
        String difficolta
) {
}
