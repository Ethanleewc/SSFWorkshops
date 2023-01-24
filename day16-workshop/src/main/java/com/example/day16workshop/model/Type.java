package com.example.day16workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public class Type implements Serializable{
    private String type;
    private int count;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder()
                .add("type", this.getType())
                .add("count", this.getCount());
                
    } 
    
}