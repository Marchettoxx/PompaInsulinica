package demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PompaInsulinica {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long idUtente;
    private Integer glicemia;
    private Integer insulina;
    private String commento;

    protected PompaInsulinica() {}

    public PompaInsulinica(Long idUtente, Integer glicemia, Integer insulina, String commento) {
        this.idUtente = idUtente;
        this.glicemia = glicemia;
        this.insulina = insulina;
        this.commento = commento;
    }

    @Override
    public String toString() {
        return String.format(
                "Iniezione[id=%d, idUtente='%d', glicemia= '%d', insulina='%d', commento='%s']",
                id, idUtente, glicemia, insulina, commento);
    }

    public Long getIdUtente() { return idUtente; }
    public Integer getGlicemia() {
        return glicemia;
    }
    public double getInsulina() {return insulina;}
}
