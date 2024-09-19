package project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "target")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Target implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "kind")
    private String kind; // Nova coluna kind

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "targetId")
    @JsonIgnoreProperties(value = { "targetId" }, allowSetters = true)
    private Set<Misconfigurations> misconfigurations = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "targetId")
    @JsonIgnoreProperties(value = { "targetId" }, allowSetters = true)
    private Set<Vulnerabilities> vulnerabilities = new HashSet<>();

    // Getters e setters omitidos para brevidade

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Set<Misconfigurations> getMisconfigurations() {
        return this.misconfigurations;
    }

    public void setMisconfigurations(Set<Misconfigurations> misconfigurations) {
        if (this.misconfigurations != null) {
            this.misconfigurations.forEach(i -> i.setTargetId(null));
        }
        if (misconfigurations != null) {
            misconfigurations.forEach(i -> i.setTargetId(this));
        }
        this.misconfigurations = misconfigurations;
    }

    public Set<Vulnerabilities> getVulnerabilities() {
        return this.vulnerabilities;
    }

    public void setVulnerabilities(Set<Vulnerabilities> vulnerabilities) {
        if (this.vulnerabilities != null) {
            this.vulnerabilities.forEach(i -> i.setTargetId(null));
        }
        if (vulnerabilities != null) {
            vulnerabilities.forEach(i -> i.setTargetId(this));
        }
        this.vulnerabilities = vulnerabilities;
    }

    public Target vulnerabilities(Set<Vulnerabilities> vulnerabilities) {
        this.setVulnerabilities(vulnerabilities);
        return this;
    }

    public Target addVulnerabilities(Vulnerabilities vulnerabilities) {
        this.vulnerabilities.add(vulnerabilities);
        vulnerabilities.setTargetId(this);
        return this;
    }

    public Target removeVulnerabilities(Vulnerabilities vulnerabilities) {
        this.vulnerabilities.remove(vulnerabilities);
        vulnerabilities.setTargetId(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Target)) {
            return false;
        }
        return getId() != null && getId().equals(((Target) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Target{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
