package br.com.fiap.aula3.repository;

import br.com.fiap.aula3.dto.FraudNotification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifcacaoRepository extends MongoRepository<FraudNotification, String> {
    // Você pode adicionar métodos de consulta personalizados aqui, se quiser
}
