package project.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
public class ImageScanController {

    @GetMapping("/scanImage")
    public ResponseEntity<String> scanImage(@RequestParam String imageName) {
        
        String command = "trivy image " + imageName;
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String result = reader.lines().collect(Collectors.joining("\n"));
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                   
                    return ResponseEntity.ok(result);
                } else {
                    
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no scan da imagem: " + result);
                }
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao executar o scan da imagem.");
        }
    }
}