package com.example.practicews13.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import com.example.practicews13.model.Contact;

@Component
public class Contacts {

    public void saveContact(Contact ctc, Model model, ApplicationArguments appArgs, String defaultDataDir) throws IOException{
        String dataFileName = ctc.getId();
        PrintWriter printWriter = null;
        FileWriter fileWriter = null;

        try {
        fileWriter = new FileWriter(getDataDir(appArgs, defaultDataDir) + "/" + dataFileName);
        printWriter = new PrintWriter(fileWriter);
        printWriter.println(ctc.getName());
        printWriter.println(ctc.getEmail());
        printWriter.println(ctc.getPhoneNumber());
        printWriter.println(ctc.getDob().toString());
     } catch (IOException e) {
        System.err.println(e);
     } finally {
        printWriter.close();
        fileWriter.close();
     }
     model.addAttribute("contact", ctc);
    }
    
    private String getDataDir(ApplicationArguments appArgs, String defaultDataDir){
        String dataDirResult = null;
        List<String> optValues = null;
        String[] optValuesArr = null;
        Set<String> opsNames = appArgs.getOptionNames();
        String[] optNameArr = opsNames.toArray(new String [(opsNames.size())]);
        if (optNameArr.length > 0){
            optValues = appArgs.getOptionValues(optNameArr[0]);
            optValuesArr = optValues.toArray(new String[optValues.size()]);
            dataDirResult = optValuesArr[0];
        } else{
            dataDirResult = defaultDataDir;
        }
        return dataDirResult;
    }

    public void getContactById(Model model, String contactId, ApplicationArguments appArgs, String defaultDataDir){
        Contact c = new Contact();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Path filePath = new File(getDataDir(appArgs, defaultDataDir) + "/" + contactId).toPath();
        Charset charset = Charset.forName("UTF-8");

        try {
            List<String> stringListData = Files.readAllLines(filePath, charset);
            c.setId(contactId);
            c.setName(stringListData.get(0));
            c.setEmail(stringListData.get(1));
            c.setPhoneNumber(Integer.parseInt(stringListData.get(2)));
            LocalDate dob = LocalDate.parse(stringListData.get(3), formatter);
            c.setDob(dob);
        } catch (IOException e) {
            System.err.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact info not found");
        }
        model.addAttribute("contact", c);        
    }
}
