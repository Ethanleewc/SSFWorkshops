package com.example.day16workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public class DecodingBoard implements Serializable{
    private int total_count;
    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }  

    public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder()
                .add("totalCount", this.getTotal_count());
    }


    
}