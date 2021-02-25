package com.example.teste.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
    @GetMapping("api/v1")
    public String home(){
        return "Bem-vindo à API Desafio Técnico Muxi!";
    }
}
