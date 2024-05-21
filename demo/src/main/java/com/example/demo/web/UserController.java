package com.example.demo.web;

import com.example.demo.dao.entities.User;
import com.example.demo.service.UserManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    @Qualifier("userManagerImpl")  // Specify the bean to inject
    private UserManager userManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login_page";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userManager.registerUser(user);

        // Vous pouvez récupérer l'utilisateur après son enregistrement en utilisant un autre moyen,
        // par exemple, en utilisant la méthode findByLogin de UserManager
        User registeredUser = userManager.findByLogin(user.getUsername());

        if (registeredUser != null) {
            // Authenticate the user programmatically
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    registeredUser.getUsername(), user.getPassword()
            );
            Authentication auth = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(auth);

            return "redirect:/index";
        } else {
            // Gérer le cas où l'utilisateur n'est pas enregistré avec succès
            return "redirect:/register?error";
        }
    }

}
