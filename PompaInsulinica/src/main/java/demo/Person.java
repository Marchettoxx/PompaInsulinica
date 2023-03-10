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
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;

    public Person() {}

    /**
     *
     * @param name nome della persona
     * @param surname cognome della persona
     * @param email email della persona
     * @param username username della persona
     * @param password password della persona
     */
    public Person(String name, String surname, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
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
                "Person[id=%d, name= '%s', surname='%s', email='%s', username='%s', password='%s']",
                id, name, surname, email, username, password);
    }

    /**
     * @return id persona
     */
    public Long getId() {
        return id;
    }

    /**
     * @return nome persona
     */
    public String getName() {return name;}

    /**
     * @param name nuovo valore di name che viene sostituito con il vecchio nome della persona
     */
    public void setName(String name) {this.name = name;}

    /**
     * @return cognome persona
     */
    public String getSurname() {return surname;}

    /**
     *
     * @param surname cognome persona
     */
    public void setSurname(String surname) {this.surname = surname;}

    /**
     * @return email persona
     */
    public String getEmail() {return email;}

    /**
     * @param email nuovo valore di email che viene sostituito con la vecchia email della persona
     */
    public void setEmail(String email) {this.email = email;}

    /**
     * @return username persona
     */
    public String getUsername() {return username;}

    /**
     * @param username nuovo valore di username che viene sostituito con il vecchio username della persona
     */
    public void setUsername(String username) {this.username = username;}

    /**
     * @return password persona
     */
    public String getPassword() {return password;}

    /**
     * @param password nuovo valore di password che viene sostituito con la vecchia password della persona
     */
    public void setPassword(String password) {this.password = password;}
}
