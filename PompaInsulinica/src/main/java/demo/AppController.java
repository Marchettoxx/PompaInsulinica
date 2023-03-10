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
    private MeasurementRepository repositoryMeasurement;

    Integer MIN_USERNAME = 6;
    Integer MAX_USERNAME = 15;
    Integer MIN_PASSWORD = 8;
    Integer MAX_PASSWORD = 12;
    Integer MIN_GLYCEMIA = 100;
    Integer MAX_GLYCEMIA = 300;
    Integer MIN_INSULIN = 2;
    Integer MAX_INSULIN = 10;
    Integer MAX_COMMENT = 30;

    /**
     * @return pagina login
     */
    @RequestMapping("/")
    public String index(){
        return "logIn";
    }

    /**
     * Funzione che fa ritornare la persona alla pagina precedente
     * @param id id della persona
     * @param model modello
     * @return pagina homePage se esecuzione va a buon fine altrimenti notFound
     */
    @RequestMapping("/back")
    public String back(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "homePage";
        }
        else
            return "notFound";
    }

    /**
     * Funzione che fa ritornare la persona alla pagina precedente
     * @param id id della persona
     * @param model modello
     * @return pagina profile se esecuzione va a buon fine altrimenti notFound
     */
    @RequestMapping("/backProfile")
    public String backProfile(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "profile";
        }
        else
            return "notFound";
    }

    /**
     * @return pagina login
     */
    @RequestMapping("/logOut")
    public String logOut(){
        return "logIn";
    }

    /**
     * Funzione che esegue la verifica delle credenziali della persona per
     * concedere l'accesso all'applicazione
     * @param username username della persona
     * @param password password della persona
     * @param model modello
     * @return pagina homePage se esecuzione va a buon fine altrimenti login
     */
    @RequestMapping("/tryLogIn")
    public String logInAccess(
            @RequestParam(name="username") String username,
            @RequestParam(name="password") String password,
            Model model){
        Optional<Person> person = repository.findByUsername(username);
        if (person.isPresent()) {
            if (person.get().getUsername().equals(username) && person.get().getPassword().equals(password)) {
                model.addAttribute("person", person.get());
                return "homePage";
            }
            else {
                model.addAttribute("error", "CREDENZIALI ERRATE");
                return "logIn";
            }
        }
        else {
            model.addAttribute("error", "UTENTE NON ESISTENTE");
            return "logIn";
        }
    }

    /**
     * @param model modello
     * @return pagina createNewPerson
     */
    @RequestMapping("/create")
    public String createPerson(Model model){
        model.addAttribute("errName", "obbligatorio");
        model.addAttribute("errSurname", "obbligatorio");
        model.addAttribute("errEmail", "valide (@gmail.com, @yahoo.it)");
        model.addAttribute("errUsername", " min: " + MIN_USERNAME + " max: " + MAX_USERNAME);
        model.addAttribute("errPassword", " min: " + MIN_PASSWORD + " max: " + MAX_PASSWORD + " e almeno !?.- e numero");
        return "createNewPerson";
    }

    /**
     * Funzione che consente di creare un nuovo utente
     * @param name nome persona
     * @param surname cognome persona
     * @param email email persona
     * @param username username persona
     * @param password password persona
     * @param model persona
     * @return login se esecuzione va a buon vine altrimenti ritorna la pagina createNewPerson
     */
    @RequestMapping("/newPerson")
    public String createPerson(
            @RequestParam(name="name") String name,
            @RequestParam(name="surname") String surname,
            @RequestParam(name="email") String email,
            @RequestParam(name="username") String username,
            @RequestParam(name="password") String password,
            Model model) {
        boolean flag = true;

        if (!name.isEmpty()) {
            model.addAttribute("name", name);
            model.addAttribute("errName", "");
        }
        else {
            model.addAttribute("errName", "obbligatorio");
            flag = false;
        }

        if (!surname.isEmpty()) {
            model.addAttribute("surname", surname);
            model.addAttribute("errSurname", "");
        }
        else {
            model.addAttribute("errSurname", "obbligatorio");
            flag = false;
        }

        if (email.contains("@gmail.com") || email.contains("@yahoo.it")) {
            model.addAttribute("email", email);
            model.addAttribute("errEmail", "");
        }
        else {
            model.addAttribute("errEmail", "valide (@gmail.com, @yahoo.it)");
            flag = false;
        }

        if (username.length() >= MIN_USERNAME && username.length() <= MAX_USERNAME) {
            Optional<Person> person = repository.findByUsername(username);
            if (person.isPresent()) {
                model.addAttribute("errUsername", "username già utilizzato");
                flag = false;
            }
            else {
                model.addAttribute("username", username);
                model.addAttribute("errUsername", "");
            }
        }
        else {
            model.addAttribute("errUsername", " min: " + MIN_USERNAME + " max: " + MAX_USERNAME);
            flag = false;
        }

        if (valid(password)) {
            model.addAttribute("password", password);
            model.addAttribute("errPassword", "");
        }
        else {
            model.addAttribute("errPassword", "min: " + MIN_PASSWORD + "max: " + MAX_PASSWORD + " e almeno !?.- e numero");
            flag = false;
        }

        if (flag) {
            Person newPerson = new Person(name, surname, email, username, password);
            repository.save(newPerson);
            model.addAttribute("person", newPerson);
            return "login";
        }
        else {
            model.addAttribute("error", "INSERISCI CREDENZIALI VALIDE");
            return "createNewPerson";
        }
    }

    /**
     * Funzione che verifica la validità della password
     * @param password password che la persona inserisce in fase di creazione account
     * @return un booleano a True se password valida, a False se password non valida
     */
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

    /**
     * Funzione che mostra le credenziali persona
     * @param id id persona
     * @param model modello
     * @return pagina profile se esecuzione va a buon fine altrimenti ritorna pagina notFound
     */
    @RequestMapping("/profile")
    public String showDataPerson(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "profile";
        }
        else
            return "notFound";
    }

    /**
     * Funzione che elimina account della persona
     * @param id persona
     * @return pagina login se esecuzione va a buon fine altrimenti ritorna pagina notFound
     */
    @RequestMapping("/deleteAccount")
    public String deleteAccount(
            @RequestParam(name="id") Long id) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            repository.delete(person.get());
            return "logIn";
        }
        else
            return "notFound";
    }

    /**
     * @param id id persona
     * @param model modello
     * @return pagina editPerson se esecuzione va a buon fine altrimenti ritorna pagina notFound
     */
    @RequestMapping("/edit")
    public String editPerson(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            model.addAttribute("errEmail", "valide (@gmail.com, @yahoo.it)");
            model.addAttribute("errUsername", " min: " + MIN_USERNAME + " max: " + MAX_USERNAME);
            model.addAttribute("errPassword", "min: " + MIN_PASSWORD + "max: " + MAX_PASSWORD + " e almeno !?.- e numero");
            return "editPerson";
        }
        else
            return "notFound";
    }

    /**
     * Funzione che tramite i parametri passati modifica le credenziali della persona
     * @param id id persona
     * @param name nome persona
     * @param surname cognome persona
     * @param email email persona
     * @param username username persona
     * @param password password persona
     * @param model modello
     * @return pagina profile se esecuzione va a buon fine altrimenti se ci sono errori pagina editPerson altrimenti pagina notFound
     */
    @RequestMapping("/saveEdit")
    public String editDataPerson(
            @RequestParam(name="id") Long id,
            @RequestParam(name="name") String name,
            @RequestParam(name="surname") String surname,
            @RequestParam(name="email") String email,
            @RequestParam(name="username") String username,
            @RequestParam(name="password") String password,
            Model model) {
        Optional<Person> oldPerson = repository.findById(id);
        if (oldPerson.isPresent()) {
            Person temp = oldPerson.get();
            boolean flag = true;

            if (!name.isEmpty()) {
                temp.setName(name);
                model.addAttribute("errName", "");
            } else {
                model.addAttribute("errName", "obbligatorio");
                flag = false;
            }

            if (!surname.isEmpty()) {
                temp.setSurname(surname);
                model.addAttribute("errSurname", "");
            } else {
                model.addAttribute("errSurname", "obbligatorio");
                flag = false;
            }

            if (email.contains("@gmail.com") || email.contains("@yahoo.it")) {
                temp.setEmail(email);
                model.addAttribute("errEmail", "");
            } else {
                model.addAttribute("errEmail", "valide (@gmail.com, @yahoo.it)");
                flag = false;
            }

            if (username.length() >= MIN_USERNAME && username.length() <= MAX_USERNAME) {
                Optional<Person> personaWithSameUsername = repository.findByUsername(username);
                if (personaWithSameUsername.isPresent()) {
                    if (!personaWithSameUsername.get().getId().equals(id)) {
                        model.addAttribute("errUsername", "username già utilizzato");
                        flag = false;
                    }
                    else {
                        model.addAttribute("errUsername", "");
                    }
                }
                else {
                    temp.setUsername(username);
                    model.addAttribute("errUsername", "");
                }
            } else {
                model.addAttribute("errUsername", " min: " + MIN_USERNAME + " max: " + MAX_USERNAME);
                flag = false;
            }

            if (valid(password)) {
                temp.setPassword(password);
                model.addAttribute("errPassword", "");
            } else {
                model.addAttribute("errPassword", "min: " + MIN_PASSWORD + "max: " + MAX_PASSWORD + " e almeno !?.- e numero");
                flag = false;
            }

            if (flag) {
                repository.delete(oldPerson.get());
                Person newPerson = new Person(name, surname, email, username, password);
                repository.save(newPerson);
                List<Measurement> chronology = repositoryMeasurement.findByIdPerson(id);
                for (Measurement p : chronology) {
                    repositoryMeasurement.delete(p);
                }
                chronology.replaceAll(x -> x.setIdPerson(newPerson.getId()));
                repositoryMeasurement.saveAll(chronology);
                model.addAttribute("person", newPerson);
                return "profile";
            } else {
                model.addAttribute("person", temp);
                model.addAttribute("error", "INSERISCI CREDENZIALI VALIDE");
                return "editPerson";
            }
        }
        else
            return "notFound";
    }

    /**
     * @param id id persona
     * @param model modello
     * @return pagina insertMeasurement se esecuzione va a buon fine altrimenti notFound
     */
    @RequestMapping("/registration")
    public String registrationMeasurement(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            // metto a schermo ultima iniezione fatta
            List<Measurement> chronology = repositoryMeasurement.findByIdPerson(id);
            if (!chronology.isEmpty()) {
                Measurement measurement = chronology.get(chronology.size() - 1);
                model.addAttribute("lastMeasurement", String.format("ULTIMA MISURAZIONE:  %d %.1f %s %s", measurement.getGlycemia(), measurement.getInsulin(), measurement.getTime(), measurement.getComment()));
            }
            model.addAttribute("person", person.get());
            model.addAttribute("errGlycemia", "valore min: " + MIN_GLYCEMIA + " max: " + MAX_GLYCEMIA);
            model.addAttribute("errInsulin", "valore min: " + MIN_INSULIN + " max: " + MAX_INSULIN);
            model.addAttribute("errComment", "lunghezza max 120");
            return "insertMeasurement";
        }
        else
            return "notFound";
    }

    /**
     * Funzione che permette d'inserire nuova misurazione
     * @param id id persona
     * @param glycemia glicemia misurazione
     * @param insulin insulina misurazione
     * @param comment commento misurazione
     * @param model modello
     * @return pagina insertMeasurement se esecuzione va a buon fine altrimenti pagina notFound
     */
    @RequestMapping("/save")
    public String saveMeasurement(
            @RequestParam(name="id") Long id,
            @RequestParam(name="glycemia", required=false) String glycemia,
            @RequestParam(name="insulin", required=false) String insulin,
            @RequestParam(name="comment") String comment,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            boolean flag = true;

            if (!glycemia.isEmpty()) {
                try {
                    int int_glycemia = Integer.parseInt(glycemia);
                    if (int_glycemia >= MIN_GLYCEMIA && int_glycemia <= MAX_GLYCEMIA) {
                        model.addAttribute("glicemia", glycemia);
                        model.addAttribute("errGlycemia", "");
                    }
                    else {
                        model.addAttribute("errGlycemia", "valore min: " + MIN_GLYCEMIA + " max: " + MAX_GLYCEMIA);
                        flag = false;
                    }
                }
                catch (NumberFormatException ex){
                    model.addAttribute("errGlycemia", "inserisci numero intero");
                    flag = false;
                }
            }
            else {
                model.addAttribute("errGlycemia", "obbligatorio");
                flag = false;
            }

            if (!insulin.isEmpty()) {
                try {
                    int int_insulin = Integer.parseInt(insulin);
                    if (int_insulin >= MIN_INSULIN && int_insulin <= MAX_INSULIN) {
                        model.addAttribute("insulin", insulin);
                        model.addAttribute("errInsulin", "");
                    }
                    else {
                        model.addAttribute("errInsulin", "valore min: " + MIN_INSULIN + " max: " + MAX_INSULIN);
                        flag = false;
                    }
                }
                catch (NumberFormatException ex){
                    model.addAttribute("errInsulin", "inserisci numero intero");
                    flag = false;
                }
            }
            else {
                model.addAttribute("errInsulin", "obbligatorio");
                flag = false;
            }

            if (comment.length() < MAX_COMMENT) {
                model.addAttribute("comment", comment);
                model.addAttribute("errComment", "");
            }
            else {
                model.addAttribute("errComment", "lunghezza max 120");
                flag = false;
            }

            if (flag) {
                Measurement measurement = new Measurement(id, Integer.parseInt(glycemia), Integer.parseInt(insulin), comment);
                repositoryMeasurement.save(measurement);
                model.addAttribute("glycemia", "");
                model.addAttribute("insulin", "");
                model.addAttribute("comment", "");
            }
            else
                model.addAttribute("error", "INSERISCI VALORI VALIDI");

            List<Measurement> cronologia = repositoryMeasurement.findByIdPerson(id);
            if (!cronologia.isEmpty()) {
                Measurement measurement = cronologia.get(cronologia.size() - 1);
                model.addAttribute("lastMeasurement", String.format("ULTIMA MISURAZIONE:  %d %.1f %s %s", measurement.getGlycemia(), measurement.getInsulin(), measurement.getTime(), measurement.getComment()));
            }
            model.addAttribute("errGlycemia", "valore min: " + MIN_GLYCEMIA + " max: " + MAX_GLYCEMIA);
            model.addAttribute("errInsulin", "valore min: " + MIN_INSULIN + " max: " + MAX_INSULIN);
            model.addAttribute("errComment", "lunghezza max 120");
            return "insertMeasurement";
        }
        else
            return "notFound";
    }

    /**
     * @param id id persona
     * @param model modello
     * @return pagina chronology se esistono misurazioni in cronologia altrimenti pagina homePage altrimenti notFound
     */
    @RequestMapping("/chronology")
    public String getChronology(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            List<Measurement> chronology = repositoryMeasurement.findByIdPerson(id);
            if (!chronology.isEmpty()) {
                model.addAttribute("person", person.get());
                model.addAttribute("chronology", chronology);
                return "chronology";
            }
            else {
                model.addAttribute("person", person.get());
                model.addAttribute("notification", "cronologia vuota crea nuova misurazione");
                return "homePage";
            }
        }
        else
            return "notFound";
    }

    /**
     * Funzione che elimina la cronologia totale di misurazioni
     * @param id id persona
     * @param model modello
     * @return pagina chronology senza nessuna misurazione altrimenti notFound
     */
    @RequestMapping("/deleteChronology")
    public String dropChronology(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            for (Measurement p: repositoryMeasurement.findByIdPerson(id)) {
                repositoryMeasurement.delete(p);
            }
            model.addAttribute("person", person.get());
            return "chronology";
        }
        else
            return "notFound";
    }

    /**
     * Funzione che elimina singola misurazione
     * @param id id persona
     * @param idMeasurement id Misurazione
     * @param model modello
     * @return pagina chronology senza la misurazione eliminata altrimenti pagina notFound
     */
    @RequestMapping("/deleteMeasurement")
    public String dropMeasurement(
            @RequestParam(name="id") Long id,
            @RequestParam(name="idMeasurement") Long idMeasurement,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            Optional<Measurement> measurement = repositoryMeasurement.findById(idMeasurement);
            if (measurement.isPresent()) {
                repositoryMeasurement.delete(measurement.get());
                List<Measurement> cronologia = repositoryMeasurement.findByIdPerson(id);
                model.addAttribute("person", person.get());
                model.addAttribute("chronology", cronologia);
                return "chronology";
            }
            else {
                return "notFound";
            }
        }
        else
            return "notFound";
    }
}