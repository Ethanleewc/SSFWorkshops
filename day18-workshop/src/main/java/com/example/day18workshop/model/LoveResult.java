package com.example.day18workshop.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class LoveResult implements Serializable {
    
    private String fname;
    private String sname;
    private String percentage;
    private String result;
    private Boolean isCompatible;

    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getPercentage() {
        return percentage;
    }
    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public Boolean getIsCompatible() {
        return isCompatible;
    }
    public void setIsCompatible(Boolean isCompatible) {
        this.isCompatible = isCompatible;
    }
    
    
    public static LoveResult create(String json) throws IOException {
        LoveResult res = new LoveResult();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            res.setFname(o.getString("fname"));
            res.setSname(o.getString("sname"));
            res.setPercentage(o.getString("percentage"));
            res.setResult(o.getString("result"));

            if(Integer.parseInt(res.percentage) >= 75){
                res.setIsCompatible(true);
            } else {
                res.setIsCompatible(false);
            }
        }
        return res;
    }
    
}
