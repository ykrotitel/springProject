package ru.rasskazov.testSpring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                              @RequestParam(value = "surname", required = false) String surname,
                                Model model)
    {

        model.addAttribute("message", "Hello, " + name + " " +  surname);

        System.out.println(name + " and " + surname);
        return "/first/goodbye";
    }

    @GetMapping("/calc")
    public String calcPage(@RequestParam(value = "a", required = false) int a,
                           @RequestParam(value = "b", required = false) int b,
                           @RequestParam(value = "action", required = false) String action,
                           Model model) {
        double result;
        String answer = null;

        if (action.equals("division")) {
            System.out.println("This is division !");
            if (b == 0) {
                answer = "Division by zero"; //почему-то не работает при делении на ноль
                System.out.println(answer);
            } else {
                result = a / (double)b;
                answer = String.valueOf(result);
            }
        } else {
            result = 0;
            if (action.equals("addition")) {
                System.out.println("This is addition !");
                result = a + b;
            }
            else if (action.equals("subtraction")) {
                System.out.println("This is subtraction !");
                result = a - b;
            }
            else if (action.equals("multiplication"))
                result = a * b;
            answer = String.valueOf(result);
        }
        model.addAttribute("answer", answer);
        return "/first/calc";
    }

}
