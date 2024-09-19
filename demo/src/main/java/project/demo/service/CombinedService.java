package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.repository.CombinedRepository;
import project.model.Misconfigurations;
import project.model.Target;
import project.model.Vulnerabilities;

import java.util.Set;

@Service
public class CombinedService {

    @Autowired
    private CombinedRepository repository;

    public void saveAllEntities(Set<Target> targets) {
        for (Target target : targets) {
            Set<Misconfigurations> misconfigurations = target.getMisconfigurations();
            Set<Vulnerabilities> vulnerabilities = target.getVulnerabilities();

            Target savedTarget = repository.save(target);

            for (Misconfigurations misconfiguration : misconfigurations) {
                misconfiguration.setTargetId(savedTarget);
                repository.save(misconfiguration);
            }

            for (Vulnerabilities vulnerability : vulnerabilities) {
                vulnerability.setTargetId(savedTarget);
                repository.save(vulnerability);
            }
        }
    }
}
