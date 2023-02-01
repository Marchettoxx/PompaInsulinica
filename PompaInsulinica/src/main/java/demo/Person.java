package demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;

    public Person() {}

    /**
     *
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param email email dell'utente
     * @param username username dell'utente
     * @param password password dell'utente
     */
    public Person(String nome, String cognome, String email, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @return stringa contenente tutti i parametri dell'utente
     */
    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, nome= '%s', cognome='%s', email='%s', username='%s', password='%s']",
                id, nome, cognome, email, username, password);
    }

    /**
     * @return id utente
     */
    public Long getId() {
        return id;
    }

    /**
     * @return nome utente
     */
    public String getNome() {return nome;}

    /**
     * @param nome nuovo valore di nome che viene sostituito con il vecchio nome dell'utente
     */
    public void setNome(String nome) {this.nome = nome;}

    /**
     * @return cognome utente
     */
    public String getCognome() {return cognome;}

    public void setCognome(String cognome) {this.cognome = cognome;}

    /**
     * @return email utente
     */
    public String getEmail() {return email;}

    /**
     * @param email nuovo valore di email che viene sostituito con la vecchia email dell'utente
     */
    public void setEmail(String email) {this.email = email;}

    /**
     * @return username utente
     */
    public String getUsername() {return username;}

    /**
     * @param username nuovo valore di username che viene sostituito con il vecchio username dell'utente
     */
    public void setUsername(String username) {this.username = username;}

    /**
     * @return password utente
     */
    public String getPassword() {return password;}

    /**
     * @param password nuovo valore di password che viene sostituito con la vecchia password dell'utente
     */
    public void setPassword(String password) {this.password = password;}
}
