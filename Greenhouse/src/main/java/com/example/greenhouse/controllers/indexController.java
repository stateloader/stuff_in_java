package com.example.greenhouse.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class indexController {

  @GetMapping("/")
  // Shows "thymeleafed" index.html-file.
  public String showIndex() {
    return "index";
  }
}
