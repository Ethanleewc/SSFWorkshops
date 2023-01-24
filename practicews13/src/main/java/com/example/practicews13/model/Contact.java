package com.example.practicews13.model;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Contact {

    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 64, message = "The length must be between 3 and 64")
    private String name;

    @Email(message = "email is mandataory")
    private String email;

    @Min(value = 7, message = "must contain 7 digits")
    private int phoneNumber;

    @Past(message = "Date cannot be in the future")
    @NotNull(message = "Field is mandatory")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    private String id;
 
    
    public Contact() {
        this.id = generateId(8);
    }
    public Contact(String name, String email, int phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = generateId(8);
    }
    public Contact(String name, String email, int phoneNumber, LocalDate dob, String id) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    //Method to generate random HEX DEC Id
    private synchronized String generateId(int numOfChar){
        Random r = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < numOfChar){
            stringBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return stringBuilder.toString().substring(0, numOfChar);
    }
    
}
