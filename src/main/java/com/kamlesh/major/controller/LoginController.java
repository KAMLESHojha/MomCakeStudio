package com.kamlesh.major.controller;

import com.kamlesh.major.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.ServletException;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String registerGet(){
        return "register";

    }
    @GetMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpRequestHandlerServlet request) throws ServletException{
        String password = user.getPasswordEncoder.encode(password));
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        UserRepository.save(user);
        request.login(user.getEmail(),password);
        return "redirect/";
    }
}
