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
    private MisurazioneRepository repositoryMisurazioni;

    Integer MIN_USERNAME = 6;
    Integer MAX_USERNAME = 15;
    Integer MIN_PASSWORD = 8;
    Integer MAX_PASSWORD = 12;
    Integer MIN_GLICEMIA = 100;
    Integer MAX_GLICEMIA = 300;
    Integer MIN_INSULINA = 2;
    Integer MAX_INSULINA = 10;
    Integer MAX_COMMENTO = 30;

    /**
     * @return pagina login
     */
    @RequestMapping("/")
    public String index(){
        return "login";
    }

    /**
     * Funzione che fa ritornare l'utente alla pagina precedente
     * @param id id dell'utente
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
     * Funzione che fa ritornare l'utente alla pagina precedente
     * @param id id dell'utente
     * @param model modello
     * @return pagina profilo se esecuzione va a buon fine altrimenti notFound
     */
    @RequestMapping("/backProfile")
    public String backProfile(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "profilo";
        }
        else
            return "notFound";
    }

    /**
     * @return pagina login
     */
    @RequestMapping("/logout")
    public String logOut(){
        return "login";
    }

    /**
     * Funzione che esegue la verifica delle credenziali dell'utente per
     * concedere l'accesso all'applicazione
     * @param username username dell'utente
     * @param password password dell'utente
     * @param model modello
     * @return pagina homePage se esecuzione va a buon fine altrimenti login
     */
    @RequestMapping("/login")
    public String logIn(
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
                model.addAttribute("errore", "CREDENZIALI ERRATE");
                return "login";
            }
        }
        else {
            model.addAttribute("errore", "UTENTE NON ESISTENTE");
            return "login";
        }
    }

    /**
     * @param model modello
     * @return pagina createNewUtente
     */
    @RequestMapping("/create")
    public String createUtente(Model model){
        model.addAttribute("errNome", "obbligatorio");
        model.addAttribute("errCognome", "obbligatorio");
        model.addAttribute("errEmail", "valide (@gmail.com, @yahoo.it)");
        model.addAttribute("errUsername", " min: " + MIN_USERNAME + " max: " + MAX_USERNAME);
        model.addAttribute("errPassword", " min: " + MIN_PASSWORD + " max: " + MAX_PASSWORD + " e almeno !?.- e numero");
        return "createNewUtente";
    }

    /**
     * Funzione che consente di creare un nuovo utente
     * @param nome nome utente
     * @param cognome cognome utente
     * @param email email utente
     * @param username username utente
     * @param password password utente
     * @param model modello
     * @return login se esecuzione va a buon vine altrimenti ritorna la pagina createNewUtente
     */
    @RequestMapping("/nuovoUtente")
    public String creaUtente(
            @RequestParam(name="nome") String nome,
            @RequestParam(name="cognome") String cognome,
            @RequestParam(name="email") String email,
            @RequestParam(name="username") String username,
            @RequestParam(name="password") String password,
            Model model) {
        boolean flag = true;

        if (!nome.isEmpty()) {
            model.addAttribute("nome", nome);
            model.addAttribute("errNome", "");
        }
        else {
            model.addAttribute("errNome", "obbligatorio");
            flag = false;
        }

        if (!cognome.isEmpty()) {
            model.addAttribute("cognome", cognome);
            model.addAttribute("errCognome", "");
        }
        else {
            model.addAttribute("errCognome", "obbligatorio");
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
            Person newPerson = new Person(nome, cognome, email, username, password);
            repository.save(newPerson);
            model.addAttribute("person", newPerson);
            return "login";
        }
        else {
            model.addAttribute("errore", "INSERISCI CREDENZIALI VALIDE");
            return "createNewUtente";
        }
    }

    /**
     * Funzione che verifica la validità della password
     * @param password password che utente inserisce in fase di creazione account
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
     * Funzione che mostra le credenziali utente
     * @param id id utente
     * @param model modello
     * @return pagina profilo se esecuzione va a buon fine altrimenti ritorna pagina notFound
     */
    @RequestMapping("/profilo")
    public String showDatiUtente(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "profilo";
        }
        else
            return "notFound";
    }

    /**
     * Funzione che elimina account utente
     * @param id utente
     * @return pagina login se esecuzione va a buon fine altrimenti ritorna pagina notFound
     */
    @RequestMapping("/deleteAccount")
    public String deleteAccount(
            @RequestParam(name="id") Long id) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            repository.delete(person.get());
            return "login";
        }
        else
            return "notFound";
    }

    /**
     * @param id id utente
     * @param model modello
     * @return pagina editUtente se esecuzione va a buon fine altrimenti ritorna pagina notFound
     */
    @RequestMapping("/modifica")
    public String modifica(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            model.addAttribute("errEmail", "valide (@gmail.com, @yahoo.it)");
            model.addAttribute("errUsername", " min: " + MIN_USERNAME + " max: " + MAX_USERNAME);
            model.addAttribute("errPassword", "min: " + MIN_PASSWORD + "max: " + MAX_PASSWORD + " e almeno !?.- e numero");
            return "editUtente";
        }
        else
            return "notFound";
    }

    /**
     * Funzione che tramite i parametri passati modifica le credenziali dell'utente
     * @param id id utente
     * @param nome nome utente
     * @param cognome cognome utente
     * @param email email utente
     * @param username username utente
     * @param password password utente
     * @param model modello
     * @return pagina profilo se esecuzione va a buon fine altrimenti se ci sono errori pagina editUtente altrimenti pagina notFound
     */
    @RequestMapping("/salvaModifica")
    public String editDatiUtente(
            @RequestParam(name="id") Long id,
            @RequestParam(name="nome") String nome,
            @RequestParam(name="cognome") String cognome,
            @RequestParam(name="email") String email,
            @RequestParam(name="username") String username,
            @RequestParam(name="password") String password,
            Model model) {
        Optional<Person> oldPerson = repository.findById(id);
        if (oldPerson.isPresent()) {
            Person temp = oldPerson.get();
            boolean flag = true;

            if (!nome.isEmpty()) {
                temp.setNome(nome);
                model.addAttribute("errNome", "");
            } else {
                model.addAttribute("errNome", "obbligatorio");
                flag = false;
            }

            if (!cognome.isEmpty()) {
                temp.setCognome(cognome);
                model.addAttribute("errCognome", "");
            } else {
                model.addAttribute("errCognome", "obbligatorio");
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
                Person newPerson = new Person(nome, cognome, email, username, password);
                repository.save(newPerson);
                List<Misurazione> cronologia = repositoryMisurazioni.findByIdUtente(id);
                for (Misurazione p : cronologia) {
                    repositoryMisurazioni.delete(p);
                }
                cronologia.replaceAll(x -> x.setIdUtente(newPerson.getId()));
                repositoryMisurazioni.saveAll(cronologia);
                model.addAttribute("person", newPerson);
                return "profilo";
            } else {
                model.addAttribute("person", temp);
                model.addAttribute("errore", "INSERISCI CREDENZIALI VALIDE");
                return "editUtente";
            }
        }
        else
            return "notFound";
    }

    /**
     * @param id id utente
     * @param model modello
     * @return pagina inserisciMisurazione se esecuzione va a buon fine altrimenti notFound
     */
    @RequestMapping("/pompaInsulinica")
    public String pompaInsulinica(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            // metto a schermo ultima iniezione fatta
            List<Misurazione> cronologia = repositoryMisurazioni.findByIdUtente(id);
            if (!cronologia.isEmpty()) {
                Misurazione misurazione = cronologia.get(cronologia.size() - 1);
                model.addAttribute("lastMisurazione", String.format("ULTIMA MISURAZIONE:  %d %.1f %s %s", misurazione.getGlicemia(), misurazione.getInsulina(), misurazione.getTime(), misurazione.getCommento()));
            }
            model.addAttribute("person", person.get());
            model.addAttribute("errGlicemia", "valore min: " + MIN_GLICEMIA + " max: " + MAX_GLICEMIA);
            model.addAttribute("errInsulina", "valore min: " + MIN_INSULINA + " max: " + MAX_INSULINA);
            model.addAttribute("errCommento", "lunghezza max 120");
            return "inserisciMisurazione";
        }
        else
            return "notFound";
    }

    /**
     * Funzione che permette d'inserire nuova misurazione
     * @param id id utente
     * @param glicemia glicemia misurazione
     * @param insulina insulina misurazione
     * @param commento commento misurazione
     * @param model modello
     * @return pagina inserisciMisurazione se esecuzione va a buon fine altrimenti pagina notFound
     */
    @RequestMapping("/salva")
    public String saveMisurazione(
            @RequestParam(name="id") Long id,
            @RequestParam(name="glicemia", required=false) String glicemia,
            @RequestParam(name="insulina", required=false) String insulina,
            @RequestParam(name="commento") String commento,
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
                        model.addAttribute("errGlicemia", "");
                    }
                    else {
                        model.addAttribute("errGlicemia", "valore min: " + MIN_GLICEMIA + " max: " + MAX_GLICEMIA);
                        flag = false;
                    }
                }
                catch (NumberFormatException ex){
                    model.addAttribute("errGlicemia", "inserisci numero intero");
                    flag = false;
                }
            }
            else {
                model.addAttribute("errGlicemia", "obbligatorio");
                flag = false;
            }

            if (!insulina.isEmpty()) {
                try {
                    int int_insulina = Integer.parseInt(insulina);
                    if (int_insulina >= MIN_INSULINA && int_insulina <= MAX_INSULINA) {
                        model.addAttribute("insulina", insulina);
                        model.addAttribute("errInsulina", "");
                    }
                    else {
                        model.addAttribute("errInsulina", "valore min: " + MIN_INSULINA + " max: " + MAX_INSULINA);
                        flag = false;
                    }
                }
                catch (NumberFormatException ex){
                    model.addAttribute("errInsulina", "inserisci numero intero");
                    flag = false;
                }
            }
            else {
                model.addAttribute("errInsulina", "obbligatorio");
                flag = false;
            }

            if (commento.length() < MAX_COMMENTO) {
                model.addAttribute("commento", commento);
                model.addAttribute("errCommento", "");
            }
            else {
                model.addAttribute("errCommento", "lunghezza max 120");
                flag = false;
            }

            if (flag) {
                Misurazione misurazione = new Misurazione(id, Integer.parseInt(glicemia), Integer.parseInt(insulina), commento);
                repositoryMisurazioni.save(misurazione);
                model.addAttribute("glicemia", "");
                model.addAttribute("insulina", "");
                model.addAttribute("commento", "");
            }
            else
                model.addAttribute("errore", "INSERISCI VALORI VALIDI");

            List<Misurazione> cronologia = repositoryMisurazioni.findByIdUtente(id);
            if (!cronologia.isEmpty()) {
                Misurazione misurazione = cronologia.get(cronologia.size() - 1);
                model.addAttribute("lastMisurazione", String.format("ULTIMA MISURAZIONE:  %d %.1f %s %s", misurazione.getGlicemia(), misurazione.getInsulina(), misurazione.getTime(), misurazione.getCommento()));
            }
            model.addAttribute("errGlicemia", "valore min: " + MIN_GLICEMIA + " max: " + MAX_GLICEMIA);
            model.addAttribute("errInsulina", "valore min: " + MIN_INSULINA + " max: " + MAX_INSULINA);
            model.addAttribute("errCommento", "lunghezza max 120");
            return "inserisciMisurazione";
        }
        else
            return "notFound";
    }

    /**
     * @param id id utente
     * @param model modello
     * @return pagina cronologia se esistono misurazioni in cronologia altrimenti pagina homePage altrimenti notFound
     */
    @RequestMapping("/cronologia")
    public String getCronologia(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            List<Misurazione> cronologia = repositoryMisurazioni.findByIdUtente(id);
            if (!cronologia.isEmpty()) {
                model.addAttribute("person", person.get());
                model.addAttribute("cronologia", cronologia);
                return "cronologia";
            }
            else {
                model.addAttribute("person", person.get());
                model.addAttribute("notifica", "cronologia vuota crea nuova misurazione");
                return "homePage";
            }
        }
        else
            return "notFound";
    }

    /**
     * Funzione che elimina la cronologia totale di misurazioni
     * @param id id utente
     * @param model modello
     * @return pagina cronologia senza nessuna misurazione altrimenti notFound
     */
    @RequestMapping("/cancellaCronologia")
    public String dropCronologia(
            @RequestParam(name="id") Long id,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            for (Misurazione p: repositoryMisurazioni.findByIdUtente(id)) {
                repositoryMisurazioni.delete(p);
            }
            model.addAttribute("person", person.get());
            return "cronologia";
        }
        else
            return "notFound";
    }

    /**
     * Funzione che elimina singola misurazione
     * @param id id utente
     * @param idMisurazione id Misurazione
     * @param model modello
     * @return pagina cronologia senza misurazione eliminata altrimenti pagina notFound
     */
    @RequestMapping("/cancellaMisurazione")
    public String dropMisurazione(
            @RequestParam(name="id") Long id,
            @RequestParam(name="idMisurazione") Long idMisurazione,
            Model model) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            Optional<Misurazione> misurazione = repositoryMisurazioni.findById(idMisurazione);
            if (misurazione.isPresent()) {
                repositoryMisurazioni.delete(misurazione.get());
                List<Misurazione> cronologia = repositoryMisurazioni.findByIdUtente(id);
                model.addAttribute("person", person.get());
                model.addAttribute("cronologia", cronologia);
                return "cronologia";
            }
            else {
                return "notFound";
            }
        }
        else
            return "notFound";
    }
}