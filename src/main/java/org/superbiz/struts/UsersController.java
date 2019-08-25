package org.superbiz.struts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/addUser")
    public String addUserForm() {
        return "addUserForm";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, Model model) {
        try {
            userRepository.save(user);
        } catch (Exception | Error e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "addUserForm";
        }

        return "addedUser";
    }

    @GetMapping("/findUser")
    public String findUserForm() {
        return "findUserForm";
    }

    @PostMapping("/findUser")
    public String findUser(@RequestParam long id, Model model) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        } else {
            model.addAttribute("errorMessage", "User not found");
            return "findUserForm";
        }

        return "displayUser";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "displayUsers";
    }
}
