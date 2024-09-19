package project.demo.service;


import project.model.teste;
import project.demo.repository.testeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;


    @Service
    public class testeService {

        @Autowired
        private testeRepository testeRepository;
    
        public void saveAll(Iterable<teste> testes) {
            for (teste teste : testes) {
                if (teste.getId() == null) {
                    // Se o identificador não estiver definido, atribua um UUID único como String
                    teste.setId(UUID.randomUUID().toString());
                }
            }
            testeRepository.saveAll(testes);
        }
    }
