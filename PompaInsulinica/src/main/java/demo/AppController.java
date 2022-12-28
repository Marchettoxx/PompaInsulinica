package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PompaInsulinicaRepository repositoryInsulina;

    Integer MIN_USERNAME = 6;
    Integer MAX_USERNAME = 15;
    Integer MIN_PASSWORD = 8;
    Integer MAX_PASSWORD = 12;
    Integer MIN_GLICEMIA = 100;
    Integer MAX_GLICEMIA = 300;
    Integer MIN_INSULINA = 2;
    Integer MAX_INSULINA = 10;

    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/back")
    public String back(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("person", result.get());
            return "utente";
        }
        else
            return "notfound";
    }

    @RequestMapping("/logout")
    public String logOut(Model model){
        return "login";
    }

    @RequestMapping("/login")
    public String logIn(
            @RequestParam(name="username", required=true) String username,
            @RequestParam(name="password", required=true) String password,
            Model model){
        Optional<Person> result = repository.findByUsername(username);
        if (result.isPresent()) {
            if (result.get().getPassword().equals(password)) {
                model.addAttribute("person", result.get());
                return "utente";
            }
        }
        model.addAttribute("errore", "CREDENZIALI ERRATE");
        return "login";
    }

    @RequestMapping("/create")
    public String create(Model model){
        return "create";
    }

    @RequestMapping("/nuovoutente")
    public String creaUtente(
            @RequestParam(name="nome", required=true) String nome,
            @RequestParam(name="cognome", required=true) String cognome,
            @RequestParam(name="email", required=true) String email,
            @RequestParam(name="username", required=true) String username,
            @RequestParam(name="password", required=true) String password,
            Model model){
        Optional<Person> result = repository.findByUsername(username);
        if (result.isPresent()) {
            model.addAttribute("errore", "UTENTE GIA' PRESENTE");
            return "create";
        } else {
            if ((username.length() >= MIN_USERNAME && username.length() <= MAX_USERNAME)
                    && (password.length() >= MIN_PASSWORD && password.length() <= MAX_PASSWORD)) {
                if (valid(password)) {
                    if (email.contains("@gmail.com") || email.contains("@yahoo.it")) {
                        Person person = new Person(nome, cognome, email, username, password);
                        repository.save(person);
                        model.addAttribute("person", person);
                        return "login";
                    }
                    else {
                        model.addAttribute("errore", "INSERISCI EMAIL VALIDA");
                        return "create";
                    }
                }
                else {
                    model.addAttribute("errore", "PASSWORD DEVE CONTENERE ALMENO 1 NUMERO E 1 SEGNO DI PUNTEGGIATURA (!,?,-,.)");
                    return "create";
                }
            }
            else {
                model.addAttribute("errore", String.format("DIMENSIONI USERNAME (%d/%d) PASSWORD (%d/%d)", MIN_USERNAME, MAX_USERNAME, MIN_PASSWORD, MAX_PASSWORD));
                return "create";
            }
        }
    }

    private Boolean valid (String password) {
        int digit = 0;
        int special = 0;
        String charSpecial = "!?.-";
        for (char c: password.toCharArray()) {
            if (Character.isDigit(c)) {
                digit += 1;
            } else if (charSpecial.indexOf(c) != -1) {
                special += 1;
            }
        }
        if (digit >= 1 && special >= 1) {
            return true;
        } else
            return false;
    }

    @RequestMapping("/profilo")
    public String edit(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            Person person = result.get();
            model.addAttribute("person", person);
            return "profilo";
        }
        else
            return "notfound";
    }

    @RequestMapping("/modifica")
    public String editDatiUtente(
            @RequestParam(name="id", required=true) Long id,
            @RequestParam(name="nome", required=true) String nome,
            @RequestParam(name="cognome", required=true) String cognome,
            @RequestParam(name="email", required=true) String email,
            @RequestParam(name="username", required=true) String username,
            @RequestParam(name="password", required=true) String password,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {

            repository.delete(result.get());
            Person person = new Person(nome, cognome, email, username, password);
            repository.save(person);
            model.addAttribute("person", person);
            return "profilo";
        }
        else
            return "notfound";
    }

    @RequestMapping("/pompainsulinica")
    public String pompaInsulinica(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("person", result.get());
            return "insulina";
        }
        else
            return "notfound";
    }

    @RequestMapping("/salva")
    public String saveIniezione(
            @RequestParam(name="id", required=true) Long id,
            @RequestParam(name="glicemia", required=false) String glicemia,
            @RequestParam(name="insulina", required=false) String insulina,
            @RequestParam(name="commento", required=true) String commento,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("person", result.get());
            if (!Objects.equals(glicemia, "") && !Objects.equals(insulina, "")) {
                try {
                    int int_glicemia = Integer.parseInt(glicemia);
                    int int_insulina = Integer.parseInt(insulina);
                    if ((int_glicemia < MIN_GLICEMIA || int_glicemia > MAX_GLICEMIA) ||
                            int_insulina < MIN_INSULINA || int_insulina > MAX_INSULINA) {
                        return "insulina";
                    }
                    PompaInsulinica pompaInsulinica = new PompaInsulinica(id, int_glicemia, int_insulina, commento);
                    repositoryInsulina.save(pompaInsulinica);
                } catch (NumberFormatException ex){
                    model.addAttribute("errore", "INSERISCI VALORI INTERI");
                    return "insulina";
                }
            } else {
                model.addAttribute("errore", "INSERISCI ENTRAMBI I VALORI");
                return "insulina";
            }
            return "insulina";
        }
        else
            return "notfound";
    }

    @RequestMapping("/cronologia")
    public String getCronologia(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            List<PompaInsulinica> cronologia = new LinkedList<>();
            for (PompaInsulinica p: repositoryInsulina.findByIdUtente(id)){
                cronologia.add(p);
            }
            model.addAttribute("person", result.get());
            model.addAttribute("insuline", cronologia);
            return "cronologia";
        }
        else
            return "notfound";
    }

    @RequestMapping("/cancellacronologia")
    public String dropCronologia(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            for (PompaInsulinica p: repositoryInsulina.findByIdUtente(id)) {
                repositoryInsulina.delete(p);
            }
            model.addAttribute("person", result.get());
            return "cronologia";
        }
        else
            return "notfound";
    }

    @RequestMapping("/cancellariga")
    public String dropRiga(
            @RequestParam(name="idUtente", required=true) Long idUtente,
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> persona = repository.findById(idUtente);
        if (persona.isPresent()) {
            model.addAttribute("person", persona.get());
            Optional<PompaInsulinica> result = repositoryInsulina.findById(id);
            if (result.isPresent()) {
                repositoryInsulina.delete(result.get());
                List<PompaInsulinica> cronologia = new LinkedList<>();
                for (PompaInsulinica p: repositoryInsulina.findByIdUtente(idUtente)){
                    cronologia.add(p);
                }
                model.addAttribute("insuline", cronologia);
                return "cronologia";
            }
            else
                return "notfound";
        }
        else
            return "notfound";
    }
}