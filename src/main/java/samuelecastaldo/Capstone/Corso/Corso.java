package samuelecastaldo.Capstone.Corso;

import jakarta.persistence.*;
import samuelecastaldo.Capstone.entities.Utente;

@Entity
@Table(name = "corso")
public class Corso {
    @Id
    @GeneratedValue
    private long id;
    private String nomeCorso;
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    public Corso() {}

    public Corso(Utente utente, String nomeCorso) {
        this.utente = utente;
        this.nomeCorso = nomeCorso;
    }

    public long getId() {
        return id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", nomeCorso='" + nomeCorso + '\'' +
                ", utente=" + utente +
                '}';
    }
}
