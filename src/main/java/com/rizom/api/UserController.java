package com.rizom.api;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.rizom.model.User;
import com.rizom.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @Transactional
    @PostMapping(value = "users/addNew")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "login";
    }

    @GetMapping("/potentiaagendicon7273")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

//    @PostMapping("/users/save")
//    public String saveUser(User user) {
//        userService.save(user);
//
//        return "redirect:/users";
//    }

    @GetMapping("/users")
    public String listUsers(Model model) {

        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-form";
    }


    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        this.userService.delete(id);
        return "redirect:/users";
    }



}