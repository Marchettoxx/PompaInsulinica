package demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class PompaInsulinica {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long idUtente;
    private Integer glicemia;
    private Integer insulina;
    private String commento;
    private String time;

    public PompaInsulinica(Long idUtente, Integer glicemia, Integer insulina, String commento) {
        this.idUtente = idUtente;
        this.glicemia = glicemia;
        this.insulina = insulina;
        this.commento = commento;

        long ms = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(ms);
        this.time = sdf.format(resultdate);
    }

    @Override
    public String toString() {
        return String.format(
                "Iniezione[id=%d, idUtente='%d', glicemia= '%d', insulina='%d', commento='%s', time='%s']",
                id, idUtente, glicemia, insulina, commento, time);
    }

    public Long getIdUtente() { return idUtente; }
    public Integer getGlicemia() {
        return glicemia;
    }
    public double getInsulina() {return insulina;}
    public String getCommento() {return commento;}
    public String getTime() {return time;}
}
