package com.example.greenhouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

  @GetMapping("/")
  // Läser in "thymeleaf:ade" html-filen index.html.
  public String showIndex() {return "index";}
}