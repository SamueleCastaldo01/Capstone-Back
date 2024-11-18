package samuelecastaldo.Capstone.Corso;

import jakarta.validation.constraints.NotEmpty;

public record CorsoDTO (
        @NotEmpty(message = "Inserisi il nome del Corso")
        String nomeCorso
) {
}
