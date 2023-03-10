package demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long idPerson;
    private Integer glycemia;
    private Integer insulin;
    private String comment;
    private String time;

    public Measurement() {}

    /**
     * Costruttore di Misurazione
     * @param idPerson id dell'utente che identifica la misurazione della persona
     * @param glycemia valore della glicemia della misurazione
     * @param insulin valore della insulina della misurazione
     * @param comment stringa contenente il commento della persona relativo alla misurazione
     */
    public Measurement(Long idPerson, Integer glycemia, Integer insulin, String comment) {
        this.idPerson = idPerson;
        this.glycemia = glycemia;
        this.insulin = insulin;
        this.comment = comment;

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
                "Measurement[id=%d, idPerson='%d', glycemia= '%d', insulin='%d', comment='%s', time='00:00']",
                id, idPerson, glycemia, insulin, comment);
    }

    /**
     * @return id della misurazione
     */
    public Long getId() {return id;}

    /**
     * @return id dell'utente della relativa misurazione
     */
    public Long getIdPerson() {return idPerson;}

    /**
     * @param idPerson id persona con cui si sostituisce l'id attuale
     * @return l'oggetto Misurazione con id persona modificato
     */
    public Measurement setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
        return this;
    }

    /**
     * @return glicemia della misurazione
     */
    public Integer getGlycemia() {return glycemia;}

    /**
     * @return insulina della misurazione
     */
    public double getInsulin() {return insulin;}

    /**
     * @return commento della misurazione
     */
    public String getComment() {return comment;}

    /**
     * @return time della misurazione
     */
    public String getTime() {return time;}
}
