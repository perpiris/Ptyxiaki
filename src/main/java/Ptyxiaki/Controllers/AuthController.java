package Ptyxiaki.Controllers;

import Ptyxiaki.Dtos.RegisterDto;
import Ptyxiaki.Entities.AppUser;
import Ptyxiaki.Services.Implementation.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {

        RegisterDto user = new RegisterDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid RegisterDto user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            return "register";
        }

        AppUser existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }

        AppUser existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }

        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("MSG_SUCCESS", "You have registered successfully.");

        return "redirect:/login";
    }
}
