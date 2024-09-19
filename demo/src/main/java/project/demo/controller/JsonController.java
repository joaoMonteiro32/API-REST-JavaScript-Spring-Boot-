package project.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import project.demo.repository.CombinedRepository;
import project.demo.repository.EmailNotificationRepository;
import project.model.EmailNotification;
import project.model.Misconfigurations;
import project.model.Target;
import project.model.Vulnerabilities;
import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class JsonController {

    @Autowired
    private CombinedRepository repository;

    @Autowired
    private VulnerabilityCheckController vulnerabilityCheckController;

    @Autowired
    private EmailNotificationRepository emailNotificationRepository;

    Logger logger = LoggerFactory.getLogger(JsonController.class);

    private static final String GET_URL = "http://localhost:8081/vulnerability/check";

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAllEntities(@RequestBody Set<Target> targets) {
        try {
            for (Target target : targets) {
                Set<Misconfigurations> misconfigurations = target.getMisconfigurations();
                Set<Vulnerabilities> vulnerabilities = target.getVulnerabilities();

                Target savedTarget = repository.save(target);

                for (Misconfigurations misconfiguration : misconfigurations) {
                    misconfiguration.setTargetId(savedTarget);
                    repository.save(misconfiguration);
                }

                Set<Vulnerabilities> notifiedVulnerabilities = new HashSet<>();
                for (Vulnerabilities vulnerability : vulnerabilities) {
                    vulnerability.setTargetId(savedTarget);
                    Vulnerabilities savedVulnerability = repository.save(vulnerability);

                    if ("CRITICAL".equals(vulnerability.getSeverity()) || "HIGH".equals(vulnerability.getSeverity())) {
                        logger.info("heyyyyyyyyyyyyy");
                        sendEmailAlert(vulnerability, savedTarget.getName());
                        notifiedVulnerabilities.add(savedVulnerability);
                    }
                }
                if (!notifiedVulnerabilities.isEmpty()) {
                    logger.info("heyyyyyyyyyyyyy");
                    
                    EmailNotification emailNotification = new EmailNotification();
                    emailNotification.setSentDate(LocalDateTime.now());
                    emailNotification.setVulnerabilities(notifiedVulnerabilities);
                    emailNotificationRepository.save(emailNotification);

                    //sendEmailsSynchronously(notifiedVulnerabilities);
                }
            }

            logger.info("Doing stuff");
            vulnerabilityCheckController.triggerVulnerabilityCheck(); 
            
            RestTemplate restTemplate = new RestTemplate();

            // Send GET request
            ResponseEntity<String> response = restTemplate.exchange(
                    GET_URL,
                    HttpMethod.GET,
                    null,
                    String.class
            );
            logger.info(response.getBody());

            return new ResponseEntity<>("Salvo na base de dados com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Falha a salvar dados: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void sendEmailAlert(Vulnerabilities vulnerability, String targetName) throws MessagingException {
        
    }

    public static class EntitiesSavedEvent {
        private final Object source;

        public EntitiesSavedEvent(Object source) {
            this.source = source;
        }

        public Object getSource() {
            return source;
        }
    }
}