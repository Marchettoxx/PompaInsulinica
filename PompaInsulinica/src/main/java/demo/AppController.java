package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private PompaInsulinicaRepository repositoryInsulina;

    @RequestMapping("/")
    public String index(){return "login";
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
            return "notfound";
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logOut(Model model){
        return "login";
    }
    @RequestMapping("/create")
    public String create(Model model){
        return "create";
    }

    @RequestMapping("/indietro")
    public String indietro(Model model){
        return "login";
    }


    @RequestMapping("/nuovoutente")
    public String creaUtente(
            @RequestParam(name="nome", required=true) String nome,
            @RequestParam(name="cognome", required=true) String cognome,
            @RequestParam(name="email", required=true) String email,
            @RequestParam(name="username", required=true) String username,
            @RequestParam(name="password", required=true) String password,
            Model model){
        Person person = new Person(nome, cognome, email, username, password);
        repository.save(person);
        model.addAttribute("person", person);
        return "login";
    }

    @RequestMapping("/riprova")
    public String input(){
        return "login";
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
    public String edit(
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
    public String calcolaInsulina(
            @RequestParam(name="id", required=true) Long id,
            @RequestParam(name="glicemia", required=true) Integer glicemia,
            @RequestParam(name="insulina", required=true) Integer insulina,
            @RequestParam(name="commento", required=true) String commento) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            PompaInsulinica pompaInsulinica = new PompaInsulinica(id, glicemia, insulina, commento);
            repositoryInsulina.save(pompaInsulinica);
            return "utente";
        }
        else
            return "notfound";
    }


    //bella li Alessia

    /* Ciaoooooo */
    /*@RequestMapping("/input")
    public String input(){
        return "input";
    }

    @RequestMapping("/read")
    public String read(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()){
            Person person = result.get();
            model.addAttribute("person", person);
            return "read";
        }
        else
            return "notfound";
    }

    @RequestMapping("/edit")
    public String edit(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            Person person = result.get();
            model.addAttribute("person", person);
            return "edit";
        }
        else
            return "notfound";
    }

    /*@RequestMapping("/update")
    public String update(
            @RequestParam(name="id", required=true) Long id,
            @RequestParam(name="firstname", required=true) String firstname,
            @RequestParam(name="lastname", required=true) String lastname,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            repository.delete(result.get());
            Person person = new Person(firstname,lastname);
            repository.save(person);
            return "redirect:/list";
        }
        else
            return "notfound";
    }

   @RequestMapping("/delete")
    public String delete(
            @RequestParam(name="id", required=true) Long id) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()){
            repository.delete(result.get());
            return "redirect:/list";
        }
        else
            return "notfound";
    }


    /*@RequestMapping("/show1")
    public String show1(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()){
            Person person = result.get();
            model.addAttribute("person", person);
            return "show1";
        }
        else
            return "notfound";
    }*/

}