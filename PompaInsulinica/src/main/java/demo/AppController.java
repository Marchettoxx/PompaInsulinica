package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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
    public String index(Model model){
        model.addAttribute("person", repository.findAll());
        return "login";
    }

    @RequestMapping("/back")
    public String back(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "utente";
        }
        else
            return "notfound";
    }

    @RequestMapping("/backprofile")
    public String backProfile(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "profilo";
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
        Optional<Person> person = repository.findByUsername(username);
        if (person.isPresent()) {
            if (person.get().getUsername().equals(username) && person.get().getPassword().equals(password)) {
                model.addAttribute("person", person.get());
                return "utente";
            }
            else {
                model.addAttribute("errore", "CREDENZIALI ERRATE");
                return "login";
            }
        }
        else {
            model.addAttribute("errore", "UTENTE NON ESISTENTE");
            return "login";
        }
    }

    @RequestMapping("/create")
    public String createUtente(Model model){
        model.addAttribute("errnome", "obbligatorio");
        model.addAttribute("errcognome", "obbligatorio");
        model.addAttribute("erremail", "valide (@gmail.com, @yahoo.it)");
        model.addAttribute("errusername", " min: " + MIN_USERNAME + " max: " + MAX_USERNAME);
        model.addAttribute("errpassword", " min: " + MIN_PASSWORD + " max: " + MAX_PASSWORD + " e almeno !?.- e numero");
        return "create";
    }

    @RequestMapping("/nuovoutente")
    public String creaUtente(
            @RequestParam(name="nome", required=true) String nome,
            @RequestParam(name="cognome", required=true) String cognome,
            @RequestParam(name="email", required=true) String email,
            @RequestParam(name="username", required=true) String username,
            @RequestParam(name="password", required=true) String password,
            Model model) {
        boolean flag = true;

        if (!nome.isEmpty()) {
            model.addAttribute("nome", nome);
            model.addAttribute("errnome", "");
        }
        else {
            model.addAttribute("errnome", "obbligatorio");
            flag = false;
        }

        if (!cognome.isEmpty()) {
            model.addAttribute("cognome", cognome);
            model.addAttribute("errcognome", "");
        }
        else {
            model.addAttribute("errcognome", "obbligatorio");
            flag = false;
        }

        if (email.contains("@gmail.com") || email.contains("@yahoo.it")) {
            model.addAttribute("email", email);
            model.addAttribute("erremail", "");
        }
        else {
            model.addAttribute("erremail", "valide (@gmail.com, @yahoo.it)");
            flag = false;
        }

        if (username.length() >= MIN_USERNAME && username.length() <= MAX_USERNAME) {
            Optional<Person> person = repository.findByUsername(username);
            if (person.isPresent()) {
                model.addAttribute("errusername", "username già utilizzato");
                flag = false;
            }
            else {
                model.addAttribute("username", username);
                model.addAttribute("errusername", "");
            }
        }
        else {
            model.addAttribute("errusername", " min: " + MIN_USERNAME + " max: " + MAX_USERNAME);
            flag = false;
        }

        if (valid(password)) {
            model.addAttribute("password", password);
            model.addAttribute("errpassword", "");
        }
        else {
            model.addAttribute("errpassword", "min: " + MIN_PASSWORD + "max: " + MAX_PASSWORD + " e almeno !?.- e numero");
            flag = false;
        }

        if (flag) {
            Person newPerson = new Person(nome, cognome, email, username, password);
            repository.save(newPerson);
            model.addAttribute("person", newPerson);
            return "login";
        }
        else {
            model.addAttribute("errore", "INSERISCI CREDENZIALI VALIDE");
            return "create";
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
        return digit >= 1 && special >= 1 && password.length() >= MIN_PASSWORD && password.length() <= MAX_PASSWORD;
    }

    @RequestMapping("/profilo")
    public String showDatiUtente(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "profilo";
        }
        else
            return "notfound";
    }

    @RequestMapping("/deleteaccount")
    public String deleteAccount(
            @RequestParam(name="id", required=true) Long id) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            repository.delete(person.get());
            return "login";
        }
        else
            return "notfound";
    }

    @RequestMapping("/modifica")
    public String modifica(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            model.addAttribute("erremail", "valide (@gmail.com, @yahoo.it)");
            model.addAttribute("errusername", " min: " + MIN_USERNAME + " max: " + MAX_USERNAME);
            model.addAttribute("errpassword", "min: " + MIN_PASSWORD + "max: " + MAX_PASSWORD + " e almeno !?.- e numero");
            return "edit";
        }
        else
            return "notfound";
    }

    @RequestMapping("/salvamodifica")
    public String editDatiUtente(
            @RequestParam(name="id", required=true) Long id,
            @RequestParam(name="nome", required=true) String nome,
            @RequestParam(name="cognome", required=true) String cognome,
            @RequestParam(name="email", required=true) String email,
            @RequestParam(name="username", required=true) String username,
            @RequestParam(name="password", required=true) String password,
            Model model) {
        Optional<Person> oldPerson = repository.findById(id);
        if (oldPerson.isPresent()) {
            Person temp = oldPerson.get();
            boolean flag = true;

            if (!nome.isEmpty()) {
                temp.setNome(nome);
                model.addAttribute("errnome", "");
            } else {
                model.addAttribute("errnome", "obbligatorio");
                flag = false;
            }

            if (!cognome.isEmpty()) {
                temp.setCognome(cognome);
                model.addAttribute("errcognome", "");
            } else {
                model.addAttribute("errcognome", "obbligatorio");
                flag = false;
            }

            if (email.contains("@gmail.com") || email.contains("@yahoo.it")) {
                temp.setEmail(email);
                model.addAttribute("erremail", "");
            } else {
                model.addAttribute("erremail", "valide (@gmail.com, @yahoo.it)");
                flag = false;
            }

            if (username.length() >= MIN_USERNAME && username.length() <= MAX_USERNAME) {
                Optional<Person> personaWithSameUsername = repository.findByUsername(username);
                if (personaWithSameUsername.isPresent()) {
                    if (!personaWithSameUsername.get().getId().equals(id)) {
                        model.addAttribute("errusername", "username già utilizzato");
                        flag = false;
                    }
                    else {
                        model.addAttribute("errusername", "");
                    }
                }
                else {
                    temp.setUsername(username);
                    model.addAttribute("errusername", "");
                }
            } else {
                model.addAttribute("errusername", " min: " + MIN_USERNAME + " max: " + MAX_USERNAME);
                flag = false;
            }

            if (valid(password)) {
                temp.setPassword(password);
                model.addAttribute("errpassword", "");
            } else {
                model.addAttribute("errpassword", "min: " + MIN_PASSWORD + "max: " + MAX_PASSWORD + " e almeno !?.- e numero");
                flag = false;
            }

            if (flag) {
                repository.delete(oldPerson.get());
                Person newPerson = new Person(nome, cognome, email, username, password);
                repository.save(newPerson);
                List<PompaInsulinica> cronologia = repositoryInsulina.findByIdUtente(id);
                for (PompaInsulinica p : cronologia) {
                    repositoryInsulina.delete(p);
                }
                cronologia.replaceAll(x -> x.setIdUtente(newPerson.getId()));
                repositoryInsulina.saveAll(cronologia);
                model.addAttribute("person", newPerson);
                return "profilo";
            } else {
                model.addAttribute("person", temp);
                model.addAttribute("errore", "INSERISCI CREDENZIALI VALIDE");
                return "edit";
            }
        }
        else
            return "notfound";
    }

    @RequestMapping("/pompainsulinica")
    public String pompaInsulinica(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            // metto a schermo ultima iniezione fatta
            List<PompaInsulinica> cronologia = repositoryInsulina.findByIdUtente(id);
            if (!cronologia.isEmpty()) {
                PompaInsulinica misurazione = cronologia.get(cronologia.size() - 1);
                model.addAttribute("lastmisurazione", String.format("ULTIMA MISURAZIONE:  %d %.1f %s %s", misurazione.getGlicemia(), misurazione.getInsulina(), misurazione.getTime(), misurazione.getCommento()));
            }
            model.addAttribute("person", person.get());
            model.addAttribute("errglicemia", "valore min: " + MIN_GLICEMIA + " max: " + MAX_GLICEMIA);
            model.addAttribute("errinsulina", "valore min: " + MIN_INSULINA + " max: " + MAX_INSULINA);
            model.addAttribute("errcommento", "lunghezza max 120");
            return "insulina";
        }
        else
            return "notfound";
    }

    @RequestMapping("/salva")
    public String saveMisurazione(
            @RequestParam(name="id", required=true) Long id,
            @RequestParam(name="glicemia", required=false) String glicemia,
            @RequestParam(name="insulina", required=false) String insulina,
            @RequestParam(name="commento", required=true) String commento,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            boolean flag = true;

            if (!glicemia.isEmpty()) {
                try {
                    int int_glicemia = Integer.parseInt(glicemia);
                    if (int_glicemia >= MIN_GLICEMIA && int_glicemia <= MAX_GLICEMIA) {
                        model.addAttribute("glicemia", glicemia);
                        model.addAttribute("errglicemia", "");
                    }
                    else {
                        model.addAttribute("errglicemia", "valore min: " + MIN_GLICEMIA + " max: " + MAX_GLICEMIA);
                        flag = false;
                    }
                }
                catch (NumberFormatException ex){
                    model.addAttribute("errglicemia", "inserisci numero intero");
                    flag = false;
                }
            }
            else {
                model.addAttribute("errglicemia", "obbligatorio");
                flag = false;
            }

            if (!insulina.isEmpty()) {
                try {
                    int int_insulina = Integer.parseInt(insulina);
                    if (int_insulina >= MIN_INSULINA && int_insulina <= MAX_INSULINA) {
                        model.addAttribute("insulina", insulina);
                        model.addAttribute("errinsulina", "");
                    }
                    else {
                        model.addAttribute("errinsulina", "valore min: " + MIN_INSULINA + " max: " + MAX_INSULINA);
                        flag = false;
                    }
                }
                catch (NumberFormatException ex){
                    model.addAttribute("errinsulina", "inserisci numero intero");
                    flag = false;
                }
            }
            else {
                model.addAttribute("errinsulina", "obbligatorio");
                flag = false;
            }

            if (commento.length() < 120) {
                model.addAttribute("commento", commento);
                model.addAttribute("errcommento", "");
            }
            else {
                model.addAttribute("errcommento", "lunghezza max 120");
                flag = false;
            }

            if (flag) {
                PompaInsulinica pompaInsulinica = new PompaInsulinica(id, Integer.parseInt(glicemia), Integer.parseInt(insulina), commento);
                repositoryInsulina.save(pompaInsulinica);
                model.addAttribute("glicemia", "");
                model.addAttribute("insulina", "");
                model.addAttribute("commento", "");
            }
            else
                model.addAttribute("errore", "INSERISCI VALORI VALIDI");

            List<PompaInsulinica> cronologia = repositoryInsulina.findByIdUtente(id);
            if (!cronologia.isEmpty()) {
                PompaInsulinica misurazione = cronologia.get(cronologia.size() - 1);
                model.addAttribute("lastmisurazione", String.format("ULTIMA MISURAZIONE:  %d %.1f %s %s", misurazione.getGlicemia(), misurazione.getInsulina(), misurazione.getTime(), misurazione.getCommento()));
            }
            model.addAttribute("errglicemia", "valore min: " + MIN_GLICEMIA + " max: " + MAX_GLICEMIA);
            model.addAttribute("errinsulina", "valore min: " + MIN_INSULINA + " max: " + MAX_INSULINA);
            model.addAttribute("errcommento", "lunghezza max 120");
            return "insulina";
        }
        else
            return "notfound";
    }

    @RequestMapping("/cronologia")
    public String getCronologia(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            List<PompaInsulinica> cronologia = repositoryInsulina.findByIdUtente(id);
            if (!cronologia.isEmpty()) {
                model.addAttribute("person", person.get());
                model.addAttribute("cronologia", cronologia);
                return "cronologia";
            }
            else {
                model.addAttribute("person", person.get());
                model.addAttribute("notifica", "cronologia vuota crea nuova misurazione");
                return "utente";
            }
        }
        else
            return "notfound";
    }

    @RequestMapping("/cancellacronologia")
    public String dropCronologia(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            for (PompaInsulinica p: repositoryInsulina.findByIdUtente(id)) {
                repositoryInsulina.delete(p);
            }
            model.addAttribute("person", person.get());
            return "cronologia";
        }
        else
            return "notfound";
    }

    @RequestMapping("/cancellamisurazione")
    public String dropMisurazione(
            @RequestParam(name="id", required=true) Long id,
            @RequestParam(name="idMisurazione", required=true) Long idMisurazione,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            Optional<PompaInsulinica> misurazione = repositoryInsulina.findById(idMisurazione);
            if (misurazione.isPresent()) {
                repositoryInsulina.delete(misurazione.get());
                List<PompaInsulinica> cronologia = repositoryInsulina.findByIdUtente(id);
                model.addAttribute("person", person.get());
                model.addAttribute("cronologia", cronologia);
                return "cronologia";
            }
            else {
                model.addAttribute("errore", "misurazione non presente");
                return "notfound";
            }
        }
        else
            return "notfound";
    }
}