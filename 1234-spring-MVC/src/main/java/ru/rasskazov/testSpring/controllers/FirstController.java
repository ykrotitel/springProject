package ru.rasskazov.testSpring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
                              @RequestParam(value = "surname", required = false) String surname,
                                Model model)
    {

        model.addAttribute("message", "Hello, " + name + " " +  surname);

        System.out.println(name + " and " + surname);
        return "/first/goodbye";
    }

    @GetMapping("/calc")
    public String calcPage(@RequestParam(value = "a", required = false) String a,
                           @RequestParam(value = "b", required = false) String b,
                           @RequestParam(value = "action", required = false) String action,
                           Model model) {
        int result;
        String answer = null;

        if (action.equals("divison")) {
            if (Integer.parseInt(b) == 0) {
                answer = "Division by zero"; //почему-то не работает при делении на ноль
                System.out.println(answer);
            } else {
                result = Integer.parseInt(a) / Integer.parseInt(b);
                answer = String.valueOf(result);
            }
        } else {
            result = 0;
            if (action.equals("addition"))
                result = Integer.parseInt(a) + Integer.parseInt(b);
            else if (action.equals("subtraction"))
                result = Integer.parseInt(a) - Integer.parseInt(b);
            else if (action.equals("multiplication"))
                result = Integer.parseInt(a) * Integer.parseInt(b);
            answer = String.valueOf(result);
        }
        model.addAttribute("answer", answer);
        return "/first/calc";
    }
}
