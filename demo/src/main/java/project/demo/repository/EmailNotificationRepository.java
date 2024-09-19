package project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.EmailNotification;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {
    Set<EmailNotification> findAllBySentDateBefore(LocalDateTime dateTime);
}