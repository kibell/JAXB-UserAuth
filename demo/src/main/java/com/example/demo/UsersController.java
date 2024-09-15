package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private XmlService xmlService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model) throws Exception {
        Users users = xmlService.readUsersFromFile();
        for (User user : users.getUsers()) {
            if (user.getUsername().equals(username)) {
                model.addAttribute("message", "Username already exists!");
                return "register";
            }
        }
        users.getUsers().add(new User(username, password, email));
        xmlService.writeUsersToFile(users);
        model.addAttribute("message", "User registered successfully!");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String username, @RequestParam String password, Model model) throws Exception {
        Users users = xmlService.readUsersFromFile();
        for (User user : users.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                model.addAttribute("message", "Authentication successful!");
                return "welcome"; // You can create a welcome.html page for successful login
            }
        }
        model.addAttribute("message", "Invalid username or password!");
        return "login";
    }
}