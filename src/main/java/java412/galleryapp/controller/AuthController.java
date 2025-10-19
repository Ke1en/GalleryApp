package java412.galleryapp.controller;

import jakarta.servlet.http.HttpSession;
import java412.galleryapp.entity.User;
import java412.galleryapp.service.UserService;
import java412.galleryapp.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/auth/home")
    public String showAuthView(HttpSession session, Model model) {

        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

        if (loggedIn == null) {
            loggedIn = false;
        }
        model.addAttribute("loggedIn", loggedIn);

        return "auth-view";
    }

    @GetMapping("/auth/register")
    public String showRegisterForm() {
        return "register";
    }

    @GetMapping("/auth/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/auth/change-password")
    public String showChangePasswordForm() {
        return "change-password";
    }

    @PostMapping("/auth/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email,
                           Model model) {

        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {

            model.addAttribute("error", "User is already taken");

            return "register";

        }

        userService.registerUser(username, password, email);

        return "redirect:/auth/login";

    }

    @PostMapping("/auth/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/auth/home";

    }

    @PostMapping("/auth/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {

        User user = userService.findByUsername(username).orElse(null);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {

            model.addAttribute("error", "Invalid username or password");

            return "login";

        }

        String token = jwtUtils.generateToken(user.getUsername());

        session.setAttribute("token", token);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("loggedIn", true);

        return "redirect:/images";

    }

    @PostMapping("/auth/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 HttpSession session,
                                 Model model) {

        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/auth/login";
        }

        User user = userService.findByUsername(username).orElse(null);
        if (user == null) {
            return "redirect:/auth/login";
        }

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {

            model.addAttribute("error", "Current password is incorrect");

            return "change-password";

        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userService.changePassword(user);

        return "redirect:/images";

    }


}
