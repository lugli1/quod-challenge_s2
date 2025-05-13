package br.com.fiap.aula3.services;

import br.com.fiap.aula3.model.ImageMetadata;
import br.com.fiap.aula3.repository.NotifcacaoRepository;
import br.com.fiap.aula3.util.ImageMetadataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class FraudeNotificationService {

    @Autowired
    private NotifcacaoRepository repository;

    private static final String URL_NOTIFICACAO = "http://localhost:8080/api/notificacoes/fraude";

    @Autowired
    private RestTemplate restTemplate;

    public void enviarNotificacao(String tipoBiometria, String tipoFraude, MultipartFile imagem) {
        try {
            ImageMetadata metadata = ImageMetadataExtractor.extractMetadata(imagem.getInputStream());

            Map<String, Object> payload = new HashMap<>();
            payload.put("transacaoId", UUID.randomUUID().toString());
            payload.put("tipoBiometria", tipoBiometria);
            payload.put("tipoFraude", tipoFraude);
            payload.put("dataCaptura", new java.util.Date().toString());
            payload.put("notificadoPor", "sistema-de-monitoramento");
            payload.put("canalNotificacao", Arrays.asList("sms", "email"));

            Map<String, Object> metadados = new HashMap<>();

            metadados.put("latitude", metadata.getLatitude());
            metadados.put("longitude", metadata.getLongitude());
            metadados.put("ipOrigem", metadata.getIpOrigem());

            payload.put("metadados", metadados);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(URL_NOTIFICACAO, entity, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                System.err.println("Falha ao notificar fraude: " + response.getStatusCode());
            }

        } catch (Exception e) {
            System.err.println("Erro ao montar ou enviar notificação de fraude: " + e.getMessage());
        }
    }
}