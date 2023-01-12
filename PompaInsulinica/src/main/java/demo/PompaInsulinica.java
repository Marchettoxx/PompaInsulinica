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
    private final Integer glicemia;
    private final Integer insulina;
    private final String commento;
    private final String time;

    public PompaInsulinica(Long idUtente, Integer glicemia, Integer insulina, String commento) {
        this.idUtente = idUtente;
        this.glicemia = glicemia;
        this.insulina = insulina;
        this.commento = commento;

        long ms = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date resultdate = new Date(ms);
        this.time = sdf.format(resultdate);
    }

    public Long getId() {return id;}

    public Long getIdUtente() {return idUtente;}
    public PompaInsulinica setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
        return this;
    }
    public Integer getGlicemia() {return glicemia;}
    public double getInsulina() {return insulina;}
    public String getCommento() {return commento;}
    public String getTime() {return time;}
}
