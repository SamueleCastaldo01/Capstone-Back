package samuelecastaldo.Capstone.Corso;

import jakarta.persistence.*;
import samuelecastaldo.Capstone.Argomento.Argomento;
import samuelecastaldo.Capstone.entities.Utente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private String coloreCopertina;
    private LocalDateTime dataCreazione;
    @OneToMany(mappedBy = "corso", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Argomento> argomenti;

    public Corso() {}

    public Corso(Utente utente, String nomeCorso, String coloreCopertina) {
        this.utente = utente;
        this.nomeCorso = nomeCorso;
        this.coloreCopertina = coloreCopertina;
        this.dataCreazione = LocalDateTime.now();
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

    public String getColoreCopertina() {
        return coloreCopertina;
    }

    public void setColoreCopertina(String coloreCopertina) {
        this.coloreCopertina = coloreCopertina;
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
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
