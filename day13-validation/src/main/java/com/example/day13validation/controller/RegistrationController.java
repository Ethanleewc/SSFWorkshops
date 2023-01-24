package com.example.day13validation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.day13validation.models.Person;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path="/register")
public class RegistrationController {

    @GetMapping
    public String getRegForm(Model model){
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping
    public String postRegistration(@Valid Person person, 
                BindingResult bResult, Model model){
        if(bResult.hasErrors()){
            return "register";
        }
        return "thankyou";
    }
}
