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
    private String coloreCopertina;

    public Corso() {}

    public Corso(Utente utente, String nomeCorso, String coloreCopertina) {
        this.utente = utente;
        this.nomeCorso = nomeCorso;
        this.coloreCopertina = coloreCopertina;
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

    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", nomeCorso='" + nomeCorso + '\'' +
                ", utente=" + utente +
                '}';
    }
}
