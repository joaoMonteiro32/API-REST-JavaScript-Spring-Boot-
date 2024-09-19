package project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.Target;

@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {
    
    boolean existsByNameContaining(String name);
}
