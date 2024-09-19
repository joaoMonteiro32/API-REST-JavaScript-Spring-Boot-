package project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.Target;
import project.model.Misconfigurations;
import project.model.Vulnerabilities;

@Repository
public interface CombinedRepository extends JpaRepository<Target, Integer> {

    Misconfigurations save(Misconfigurations misconfigurations);

    Vulnerabilities save(Vulnerabilities vulnerabilities);
}

