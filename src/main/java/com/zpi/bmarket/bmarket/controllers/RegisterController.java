package com.zpi.bmarket.bmarket.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zpi.bmarket.bmarket.PostRegisterStatus;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.UserDTO;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String register(WebRequest request, Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user",user);
        return "registerView";
    }

    @RequestMapping(value = "/postRegister" ,method = RequestMethod.POST)
    public String postRegister(@ModelAttribute UserDTO userDTO,Model model) {
        // TODO: 30.03.2019 in case of error do not redirect to post register view
        if(!userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
            model.addAttribute("status", PostRegisterStatus.PASSWORDS_NOT_MATCH);
            return "postRegisterView";
        }
        //create user from user DTO
        User user = new User();
        user.setLogin(userDTO.getLogin());
        // TODO: 30.03.2019 hash function on password here
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        //add user to database
        userRepository.save(user);

        model.addAttribute("user",user);
        model.addAttribute("status", PostRegisterStatus.SUCCESS);
        return "postRegisterView";
    }
}
