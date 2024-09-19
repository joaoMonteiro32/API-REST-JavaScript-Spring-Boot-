package project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.Vulnerabilities;

@Repository
public interface VulnerabilitiesRepository extends JpaRepository<Vulnerabilities, Long> {
}