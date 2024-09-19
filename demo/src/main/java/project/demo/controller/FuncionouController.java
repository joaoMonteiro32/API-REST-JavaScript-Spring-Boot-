package project.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuncionouController {
    @GetMapping("/testar")
    public String testar() {
        return "1111";
    }
}

