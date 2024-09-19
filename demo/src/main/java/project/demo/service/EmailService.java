package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public void sendEmailsSynchronously(List<String> recipients, String subject, String text) {
        int delaySeconds = 20; // Intervalo de 20 segundos entre cada envio

        for (int i = 0; i < recipients.size(); i++) {
            String to = recipients.get(i);
            LocalDateTime scheduledTime = LocalDateTime.now().plusSeconds(i * delaySeconds);
            
            executorService.schedule(() -> sendEmail(to, subject, text), delaySeconds * i, TimeUnit.SECONDS);
            
            logger.info("E-mail agendado para " + to + " Ã s " + scheduledTime);
        }
    }

    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            emailSender.send(message);
            logger.info("E-mail enviado para " + to);
        } catch (MessagingException e) {
            logger.error("Erro ao enviar e-mail para " + to + ": " + e.getMessage());
        }
    }

    // Fechar o executor ao desligar o aplicativo
    public void shutdownExecutorService() {
        executorService.shutdown();
    }
}