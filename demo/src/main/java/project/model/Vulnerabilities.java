package project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A Vulnerabilities.
 */
@Entity
@Table(name = "vulnerabilities")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Vulnerabilities implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "pkg_id")
    private String pkgID;

    @Column(name = "installed_version")
    private String installedVersion;

    @Column(name = "status")
    private String status;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "description", columnDefinition = "MEDIUMTEXT")
    private String description;

    @Column(name = "severity")
    private String severity;

    @Column(name = "vulnerabilityID")
    private String vulnerabilityID; 

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "misconfigurations", "vulnerabilities" }, allowSetters = true)
    private Target targetId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public Vulnerabilities id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPkgID() {
        return this.pkgID;
    }

    public Vulnerabilities pkgID(String pkgID) {
        this.setPkgID(pkgID);
        return this;
    }

    public void setPkgID(String pkgID) {
        this.pkgID = pkgID;
    }

    public String getInstalledVersion() {
        return this.installedVersion;
    }

    public Vulnerabilities installedVersion(String installedVersion) {
        this.setInstalledVersion(installedVersion);
        return this;
    }

    public void setInstalledVersion(String installedVersion) {
        this.installedVersion = installedVersion;
    }

    public String getStatus() {
        return this.status;
    }

    public Vulnerabilities status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }

    public Vulnerabilities title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public Vulnerabilities description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return this.severity;
    }

    public Vulnerabilities severity(String severity) {
        this.setSeverity(severity);
        return this;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Target getTargetId() {
        return this.targetId;
    }

    public void setTargetId(Target target) {
        this.targetId = target;
    }

    public Vulnerabilities targetId(Target target) {
        this.setTargetId(target);
        return this;
    }

    public String getVulnerabilityID() {
        return vulnerabilityID;
    }

    public void setVulnerabilityID(String vulnerabilityID) {
        this.vulnerabilityID = vulnerabilityID;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vulnerabilities)) {
            return false;
        }
        return getId() != null && getId().equals(((Vulnerabilities) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vulnerabilities{" +
            "id=" + getId() +
            ", pkgID='" + getPkgID() + "'" +
            ", installedVersion=" + getInstalledVersion() +
            ", status='" + getStatus() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", severity='" + getSeverity() + "'" +
            "}";
    }
}