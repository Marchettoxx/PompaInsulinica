package demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Misurazione {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long idUtente;
    private Integer glicemia;
    private Integer insulina;
    private String commento;
    private String time;

    public Misurazione() {}

    /**
     * Costruttore di Misurazione
     * @param idUtente id dell'utente che identifica la misurazione dell'utente
     * @param glicemia valore della glicemia della misurazione
     * @param insulina valore della insulina della misurazione
     * @param commento stringa contenente il commento dell'utente relativo alla misurazione
     */
    public Misurazione(Long idUtente, Integer glicemia, Integer insulina, String commento) {
        this.idUtente = idUtente;
        this.glicemia = glicemia;
        this.insulina = insulina;
        this.commento = commento;

        long ms = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date resultDate = new Date(ms);
        this.time = sdf.format(resultDate);
    }

    /**
     * @return stringa contenente tutti i parametri della classe
     */
    @Override
    public String toString() {
        return String.format(
                "Misurazione[id=%d, idUtente='%d', glicemia= '%d', insulina='%d', commento='%s', time='00:00']",
                id, idUtente, glicemia, insulina, commento);
    }

    /**
     * @return id della misurazione
     */
    public Long getId() {return id;}

    /**
     * @return id dell'utente della relativa misurazione
     */
    public Long getIdUtente() {return idUtente;}

    /**
     * @param idUtente id utente con cui si sostituisce l'id attuale
     * @return l'oggetto Misurazione con id utente modificato
     */
    public Misurazione setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
        return this;
    }

    /**
     * @return glicemia della misurazione
     */
    public Integer getGlicemia() {return glicemia;}

    /**
     * @return insulina della misurazione
     */
    public double getInsulina() {return insulina;}

    /**
     * @return commento della misurazione
     */
    public String getCommento() {return commento;}

    /**
     * @return time della misurazione
     */
    public String getTime() {return time;}
}
