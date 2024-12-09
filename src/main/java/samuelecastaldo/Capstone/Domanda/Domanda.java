package samuelecastaldo.Capstone.Domanda;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import samuelecastaldo.Capstone.Argomento.Argomento;
import samuelecastaldo.Capstone.entities.Utente;

import java.time.LocalDateTime;

@Entity
@Table(name = "domanda")
public class Domanda {
    @Id
    @GeneratedValue
    private long id;
    private String domanda;
    private String rispostaDomanda;
    private String rispostaS2;
    private String rispostas3;
    private String rispostas4;
    @ManyToOne
    @JoinColumn(name = "id_argomento")
    private Argomento argomento;
    private long idCorso;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;
    private  String difficolta;
    private LocalDateTime dataModificaDifficolta;
    private long ritardo;   //qui lo facciamo valere in minuti

    public Domanda() {}

    public Domanda(String domanda, String rispostaDomanda, String rispostaS2, String rispostas3, String rispostas4, Argomento argomento, Utente utente, String difficolta) {
        this.domanda = domanda;
        this.rispostaDomanda = rispostaDomanda;
        this.rispostaS2 = rispostaS2;
        this.rispostas3 = rispostas3;
        this.rispostas4 = rispostas4;
        this.argomento = argomento;
        this.utente = utente;
        this.idCorso = argomento.getCorso().getId();
        this.difficolta = difficolta;
        this.dataModificaDifficolta = LocalDateTime.now();
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public long getId() {
        return id;
    }

    public String getDomanda() {
        return domanda;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public String getRispostaDomanda() {
        return rispostaDomanda;
    }

    public void setRispostaDomanda(String rispostaDomanda) {
        this.rispostaDomanda = rispostaDomanda;
    }

    public String getRispostaS2() {
        return rispostaS2;
    }

    public void setRispostaS2(String rispostaS2) {
        this.rispostaS2 = rispostaS2;
    }

    public String getRispostas3() {
        return rispostas3;
    }

    public void setRispostas3(String rispostas3) {
        this.rispostas3 = rispostas3;
    }

    public String getRispostas4() {
        return rispostas4;
    }

    public void setRispostas4(String rispostas4) {
        this.rispostas4 = rispostas4;
    }

    public Argomento getArgomento() {
        return argomento;
    }

    public void setArgomento(Argomento argomento) {
        this.argomento = argomento;
    }

    public long getIdCorso() {
        return idCorso;
    }

    public long getRitardo() {
        return ritardo;
    }

    public void setRitardo(long ritardo) {
        this.ritardo = ritardo;
    }

    public LocalDateTime getDataModificaDifficolta() {
        return dataModificaDifficolta;
    }

    public void setDataModificaDifficolta(LocalDateTime dataModificaDifficolta) {
        this.dataModificaDifficolta = dataModificaDifficolta;
    }

    public String getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(String difficolta) {
        this.difficolta = difficolta;
    }

    public void setIdCorso(long idCorso) {
        this.idCorso = idCorso;
    }

    @Override
    public String toString() {
        return "Domanda{" +
                "id=" + id +
                ", domanda='" + domanda + '\'' +
                ", rispostaDomanda='" + rispostaDomanda + '\'' +
                ", rispostaS2='" + rispostaS2 + '\'' +
                ", rispostas3='" + rispostas3 + '\'' +
                ", rispostas4='" + rispostas4 + '\'' +
                ", argomento=" + argomento +
                '}';
    }
}
