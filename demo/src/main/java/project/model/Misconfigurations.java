package project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A Misconfigurations.
 */
@Entity
@Table(name = "misconfigurations")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Misconfigurations implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "avdid")
    private String avdid;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "message", columnDefinition = "MEDIUMTEXT")
    private String message;

    @Column(name = "resolution", columnDefinition = "MEDIUMTEXT")
    private String resolution;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "misconfigurations", "vulnerabilities" }, allowSetters = true)
    private Target targetId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public Misconfigurations id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public Misconfigurations type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvdid() {
        return this.avdid;
    }

    public Misconfigurations avdid(String avdid) {
        this.setAvdid(avdid);
        return this;
    }

    public void setAvdid(String avdid) {
        this.avdid = avdid;
    }

    public String getTitle() {
        return this.title;
    }

    public Misconfigurations title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public Misconfigurations message(String message) {
        this.setMessage(message);
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResolution() {
        return this.resolution;
    }

    public Misconfigurations resolution(String resolution) {
        this.setResolution(resolution);
        return this;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Target getTargetId() {
        return this.targetId;
    }

    public void setTargetId(Target target) {
        this.targetId = target;
    }

    public Misconfigurations targetId(Target target) {
        this.setTargetId(target);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Misconfigurations)) {
            return false;
        }
        return getId() != null && getId().equals(((Misconfigurations) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Misconfigurations{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", avdid='" + getAvdid() + "'" +
            ", title='" + getTitle() + "'" +
            ", message='" + getMessage() + "'" +
            ", resolution='" + getResolution() + "'" +
            "}";
    }
}