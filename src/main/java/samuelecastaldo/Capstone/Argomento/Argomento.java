package samuelecastaldo.Capstone.Argomento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import samuelecastaldo.Capstone.Corso.Corso;
import samuelecastaldo.Capstone.entities.Utente;

import java.time.LocalDateTime;

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
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String contenuto;
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;
    private LocalDateTime dataCreazione;

    public Argomento() {}

    public Argomento(String titolo, Corso corso, String contenuto, Utente utente) {
        this.titolo = titolo;
        this.corso = corso;
        this.contenuto = contenuto;
        this.utente = utente;
        this.dataCreazione = LocalDateTime.now();
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

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
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
