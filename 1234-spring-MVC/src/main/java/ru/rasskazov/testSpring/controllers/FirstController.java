package ru.rasskazov.testSpring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request)
    {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        System.out.println("name is " + name + " and surname is " + surname);

        return "/first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname)
    {
        System.out.println(name + " and " + surname);
        return "/first/goodbye";
    }
}
