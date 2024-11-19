package samuelecastaldo.Capstone.Argomento;

import jakarta.persistence.*;
import samuelecastaldo.Capstone.Corso.Corso;
import samuelecastaldo.Capstone.entities.Utente;

@Entity
@Table(name = "argomento")
public class Argomento {
    @Id
    @GeneratedValue
    private long id;
    private String titolo;
    @ManyToOne
    @JoinColumn(name = "id_corso")
    private Corso corso;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String contenuto;
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    public Argomento() {}

    public Argomento(String titolo, Corso corso, String contenuto, Utente utente) {
        this.titolo = titolo;
        this.corso = corso;
        this.contenuto = contenuto;
        this.utente = utente;
    }

    public long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Argomento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", corso=" + corso +
                ", contenuto='" + contenuto + '\'' +
                '}';
    }
}
