package project.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "email_notification")
public class EmailNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sent_date")
    private LocalDateTime sentDate;

    @ManyToMany
    @JoinTable(
            name = "email_notification_vulnerabilities",
            joinColumns = @JoinColumn(name = "email_notification_id"),
            inverseJoinColumns = @JoinColumn(name = "vulnerability_id")
    )
    private Set<Vulnerabilities> vulnerabilities;

    public EmailNotification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public Set<Vulnerabilities> getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(Set<Vulnerabilities> notifiedVulnerabilities) {
        this.vulnerabilities = notifiedVulnerabilities;
    }
}
