package br.com.fiap.aula3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index"; // resources/templates/index.html
    }

    @GetMapping("/face")
    public String face() {
        return "face"; // resources/templates/face.html
    }

    @GetMapping("/biometry")
    public String biometry() {
        return "biometry"; // resources/templates/biometry.html
    }

    @GetMapping("/document")
    public String document() {
        return "document"; // resources/templates/document.html
    }
}
