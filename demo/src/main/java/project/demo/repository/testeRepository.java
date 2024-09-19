package project.demo.repository;

import project.model.teste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface testeRepository extends JpaRepository<teste, String> {
}
