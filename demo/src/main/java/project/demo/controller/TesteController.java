package project.demo.controller;

import project.model.teste;
import project.demo.service.testeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TesteController {

    @Autowired
    private testeService testeService;

    @PutMapping("/teste")
    public ResponseEntity<String> saveTestes(@RequestBody List<teste> testes) {
        try {
            testeService.saveAll(testes);
            return new ResponseEntity<>("Testes salvos com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro a salvar os testes: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}





