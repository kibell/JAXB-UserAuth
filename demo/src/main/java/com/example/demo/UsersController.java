package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private XmlService xmlService;

    @PostMapping("/register")
    public String register(@RequestBody User newUser) throws Exception {
        Users users = xmlService.readUsersFromFile();
        for (User user : users.getUsers()) {
            if (user.getUsername().equals(newUser.getUsername())) {
                return "Username already exists!";
            }
        }
        users.getUsers().add(newUser);
        xmlService.writeUsersToFile(users);
        return "User registered successfully!";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody User loginUser) throws Exception {
        Users users = xmlService.readUsersFromFile();
        for (User user : users.getUsers()) {
            if (user.getUsername().equals(loginUser.getUsername()) && user.getPassword().equals(loginUser.getPassword())) {
                return "Authentication successful!";
            }
        }
        return "Invalid username or password!";
    }
}