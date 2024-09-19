package project.demo.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.model.MeuModelo;

@RestController
public class MeuControlador {

    @PostMapping("/meuendpoint")
    public ResponseEntity<String> receberJson(@RequestBody MeuModelo modelo) {
        return ResponseEntity.ok("Dados recebidos: " + modelo.getCampo1() + ", " + modelo.getCampo2());
    }
}

