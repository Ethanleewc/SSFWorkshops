package com.example.day13httpsession.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.day13httpsession.model.Cart;
import com.example.day13httpsession.model.Item;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path="/cart")
public class ShoppingCartController {
    
    @GetMapping
    public String getCart(Model model, HttpSession session){
        Cart cart = (Cart)session.getAttribute("cart");
        if(null == cart){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }

        model.addAttribute("item", new Item());
        model.addAttribute("cart", cart);
        
        return "cart";
    }

    @PostMapping()
    public String postData(Model model, HttpSession session,
        @Valid Item item, BindingResult bindResult) {
        Cart cart = (Cart)session.getAttribute("cart");
        
        if(bindResult.hasErrors()){
            model.addAttribute("item", item);
            model.addAttribute("cart", cart);
            return "cart";
        }

        cart.addItemToCart(item);
        model.addAttribute("item", item);
        model.addAttribute("cart", cart);
        return "cart";
    }
    
}