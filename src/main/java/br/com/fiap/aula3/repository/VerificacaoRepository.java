package br.com.fiap.aula3.repository;

import br.com.fiap.aula3.model.Verificacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VerificacaoRepository extends MongoRepository<Verificacao, String> {
}

