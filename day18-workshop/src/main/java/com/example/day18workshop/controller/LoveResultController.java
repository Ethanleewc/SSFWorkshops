package com.example.day18workshop.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.day18workshop.model.LoveResult;
import com.example.day18workshop.service.LoveResultsService;

@Controller
@RequestMapping(path = "/loveresult")
public class LoveResultController {
    
    @Autowired
    private LoveResultsService loveResultsService;

    @PostMapping
    public String getLoveResult(@RequestParam(required = true) String fname, @RequestParam(required = true) String sname, Model model) throws IOException {
        Optional<LoveResult> lr = loveResultsService.getLoveResult(fname, sname);
        model.addAttribute("loveresult", lr.get());
        //Save result to redis
        loveResultsService.save(lr.get());
        return "loveresult";
    }

    @GetMapping(path = "/list")
    public String listResult(Model model){
        List<LoveResult> list = loveResultsService.findAll();
        model.addAttribute("list", list);
        return "list";
    }

}

