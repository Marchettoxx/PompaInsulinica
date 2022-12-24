package demo;

import javax.persistence.Entity;

@Entity
public class PompaInsulinica {

    private Long idUtente;
    private Integer glicemia;

    private double insulina;

    protected PompaInsulinica() {}

    public PompaInsulinica(Long idUtente, Integer glicemia) {
        this.idUtente = idUtente;
        this.glicemia = glicemia;
        this.insulina = glicemia /0.5*3;
    }

    public Long getIdUtente() { return idUtente; }
    public Integer getGlicemia() {
        return glicemia;
    }
    public double getInsulina() {return insulina;}

}
