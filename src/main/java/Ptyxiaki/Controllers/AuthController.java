package Ptyxiaki.Controllers;

import Ptyxiaki.Dtos.RegisterDto;
import Ptyxiaki.Entities.AppUser;
import Ptyxiaki.Enums.UserRole;
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

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void prepareContext(final Model model) {
        var roles = Arrays.stream(UserRole.values())
                .collect(Collectors.toList());

        model.addAttribute("userRoleValues", roles);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(@ModelAttribute("user") RegisterDto registerDto) {

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid RegisterDto user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            return "register";
        }

        AppUser existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            redirectAttributes.addFlashAttribute("MSG_ERROR", "error");
            return "redirect:/register";
        }

        AppUser existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            redirectAttributes.addFlashAttribute("MSG_ERROR", "error");
            return "redirect:/register";
        }

        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("MSG_SUCCESS", "You have registered successfully.");

        return "redirect:/login";
    }
}
