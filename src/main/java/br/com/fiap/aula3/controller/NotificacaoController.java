package br.com.fiap.aula3.controller;

import br.com.fiap.aula3.dto.FraudNotification;
import br.com.fiap.aula3.repository.NotifcacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotifcacaoRepository repository;

    @PostMapping("/fraude")
    public ResponseEntity<String> receberNotificacao(@RequestBody FraudNotification notificacao) {
        System.out.println("Notificação recebida: " + notificacao);

        repository.save(notificacao);

        return ResponseEntity.ok("Notificação de fraude registrada com sucesso");
    }
}
