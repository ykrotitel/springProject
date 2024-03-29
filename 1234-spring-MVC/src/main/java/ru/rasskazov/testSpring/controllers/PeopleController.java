package ru.rasskazov.testSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rasskazov.testSpring.dao.PersonDAO;
import ru.rasskazov.testSpring.models.Person;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        // Получим всех людей из DAO и передадим их всех для отображения на представлении View
        model.addAttribute("people", personDAO.index());
        return "/people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "/people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return ("/people/newPerson");
    }

//    @PostMapping("/newPerson")
//    public String create(@RequestParam("name") String name,
//                         @RequestParam("id") int id,
//                         @RequestParam("email") String email,
//                         Model model) {
//
//        Person person = new Person(id, name);
//        person.setMail(email);
//
//        // добавляем человека в БД
//
//        model.addAttribute(person);
//        return ("/people/newPerson");
//    }

    @PostMapping()
    public String createWithAnnotation(@ModelAttribute("person") Person person) {

        personDAO.save(person);
        //добавляем человека в БД

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") int id,
                            Model model) {
        model.addAttribute("person", personDAO.show(id));
        System.out.println("This is editForm !");
        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {

        // добавляем человека в БД

        System.out.println("This is update !");
        personDAO.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("${id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return ("redirect:/people");
    }
}

