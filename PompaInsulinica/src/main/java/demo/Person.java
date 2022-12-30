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

    protected Person() {}

    public Person(String nome, String cognome, String email, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, nome= '%s', cognome='%s', email='%s', username='%s', password='%s']",
                id, nome, cognome, email, username, password);
    }

    public Long getId() {
        return id;
    }
    public String getNome() {return nome;}
    public String getCognome() {return cognome;}
    public String getEmail() {return email;}
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
