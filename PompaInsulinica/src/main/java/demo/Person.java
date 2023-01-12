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

    public Person(String nome, String cognome, String email, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {this.nome = nome;}

    public void setCognome(String cognome) {this.cognome = cognome;}

    public void setEmail(String email) {this.email = email;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
