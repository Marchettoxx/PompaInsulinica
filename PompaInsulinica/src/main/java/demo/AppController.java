package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PompaInsulinicaRepository repositoryInsulina;

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
            return "create";
        } else {
            Person person = new Person(nome, cognome, email, username, password);
            repository.save(person);
            model.addAttribute("person", person);
            return "login";
        }
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
            @RequestParam(name="glicemia", required=false) Integer glicemia,
            @RequestParam(name="insulina", required=false) Integer insulina,
            @RequestParam(name="commento", required=true) String commento,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            if (glicemia!= null && insulina!=null) {
                PompaInsulinica pompaInsulinica = new PompaInsulinica(id, glicemia, insulina, commento);
                repositoryInsulina.save(pompaInsulinica);
                model.addAttribute("person", result.get());
                return "insulina";
            } else {
                model.addAttribute("person", result.get());
                return "insulina";
            }
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
            for (PompaInsulinica p: repositoryInsulina.findAll()){
                cronologia.add(p);
            }
            model.addAttribute("person", result.get());
            model.addAttribute("insuline", cronologia);
            return "cronologia";
        }
        else
            return "notfound";
    }
}