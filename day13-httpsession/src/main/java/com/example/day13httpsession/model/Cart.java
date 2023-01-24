package com.example.day13httpsession.model;

import java.util.List;
import java.util.LinkedList;

public class Cart {
    private List<Item> contents =new LinkedList<Item>();
    
    public List<Item> getContents() {
        return contents;
    }

    public void setContents(List<Item> contents) {
        this.contents = contents;
    }

    public void addItemToCart(Item item){
        this.contents.add(item);
    }
}
