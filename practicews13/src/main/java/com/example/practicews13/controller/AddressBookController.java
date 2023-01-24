package com.example.practicews13.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.practicews13.model.Contact;
import com.example.practicews13.util.Contacts;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/addressbook")
public class AddressBookController {
    
    @Autowired
    Contacts ctcz;

    @Autowired
    ApplicationArguments appArgs;

    private static final String DEFAULT_DATADIR ="/Users/bindm/src/practicews13";

    private String dataDir;

    @GetMapping
    public String showAddressBookForm(Model model){
        model.addAttribute("contact", new Contact());
        return "addressbook";
    }

    @PostMapping
    public String saveContact(@Valid Contact contact, BindingResult bindingResult, Model model) throws IOException{
        if(bindingResult.hasErrors()){
            return "addressbook";
        }
        if(!checkAgeInBetween(contact.getDob())){
            ObjectError err = new ObjectError("dob", "Age must be between 10 and 100");
            bindingResult.addError(err);
        }
        ctcz.saveContact(contact, model, appArgs, DEFAULT_DATADIR);
        return "results";
    }

    @GetMapping("{contactId}")
    public String getContactById(Model model, @PathVariable String contactId) {
        ctcz.getContactById(model, contactId, appArgs, dataDir);
        return "results";
    }

    private boolean checkAgeInBetween(LocalDate dob){
        int calculatedAge = 0;
        if(null != dob){
            calculatedAge = Period.between(dob, LocalDate.now()).getYears();
        }

        if(calculatedAge >= 10 && calculatedAge <= 100){
            return true;
        }
        return false;
    }
}
