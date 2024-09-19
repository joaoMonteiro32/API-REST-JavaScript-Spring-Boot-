package project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RemovePodController {
    private String targetUrl;

    @Autowired
    private PodController podController;

    public void updateTargetUrl() {
        String podIP = podController.getRemoveScriptIP();
        if (!podIP.startsWith("Error") && !podIP.equals("Pod not found") && !podIP.startsWith("Failure executing")) {
            this.targetUrl = "http://" + podIP + ":5000/endpoint";
        } else {
            this.targetUrl = null;
        }
    }

    @PostMapping("/remover")
    public ResponseEntity<String> testar(@RequestBody MessagePayload payload) {

        targetUrl = "http://10.42.1.142:5000/endpoint";


        RestTemplate restTemplate = new RestTemplate();

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        // Convert payload to JSON string
        String jsonPayload = "{\"message\": \"" + payload.getMessage() + "\"}";

        System.out.println("Jsonpayload: " + jsonPayload);

        // Create request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

        // Send POST request
        ResponseEntity<String> response = restTemplate.exchange(
            targetUrl,
            HttpMethod.POST,
            requestEntity,
            String.class
        );

        return ResponseEntity.ok(response.getBody());
    }

    public static class MessagePayload {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
