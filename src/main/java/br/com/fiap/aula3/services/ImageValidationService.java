package br.com.fiap.aula3.services;

import br.com.fiap.aula3.model.Verificacao;
import br.com.fiap.aula3.repository.VerificacaoRepository;
import br.com.fiap.aula3.util.GeoUtils;
import br.com.fiap.aula3.util.ImageMetadataExtractor;
import br.com.fiap.aula3.model.ImageMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ImageValidationService {

    private static final long MAX_FILE_SIZE = 20 * 1024 * 1024;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 300;

    private final FraudeNotificationService notificationService;

    public ImageValidationService(FraudeNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    private VerificacaoRepository verificacaoRepository;

    public void validateImagePair(MultipartFile file1, MultipartFile file2, String name1, String name2, String tipo) throws Exception {
        ImageMetadata meta1 = null;
        ImageMetadata meta2 = null;
        boolean verificacaoSalva = false;

        try {
            validateFileType(file1, name1, tipo);
            validateFileType(file2, name2, tipo);
            validateFileSize(file1, name1, tipo);
            validateFileSize(file2, name2, tipo);
            validateResolution(file1, name1, tipo);
            validateResolution(file2, name2, tipo);

            meta1 = ImageMetadataExtractor.extractMetadata(file1.getInputStream());
            meta2 = ImageMetadataExtractor.extractMetadata(file2.getInputStream());

            long minutosDiferenca = Math.abs(ChronoUnit.MINUTES.between(meta1.getTimestamp(), meta2.getTimestamp()));
            if (meta1.getTimestamp() == null || meta2.getTimestamp() == null || minutosDiferenca > 10 ||
                    (meta1.getLatitude() != null && meta2.getLatitude() != null &&
                            GeoUtils.distanceInKm(meta1.getLatitude(), meta1.getLongitude(), meta2.getLatitude(), meta2.getLongitude()) > 1.0)) {

                salvarVerificacao(tipo, meta1, meta2, true);
                verificacaoSalva = true;

                notificationService.enviarNotificacao(tipo, "metadados-divergentes", file1);
                throw new Exception("Possível fraude detectada.");
            }

            // Persistência em caso de sucesso
            salvarVerificacao(tipo, meta1, meta2, false);
            verificacaoSalva = true;

        } catch (Exception e) {
            if (!verificacaoSalva && meta1 != null && meta2 != null) {
                salvarVerificacao(tipo, meta1, meta2, true);
            }
            throw e;
        }
    }


    private void salvarVerificacao(String tipo, ImageMetadata m1, ImageMetadata m2, boolean fraude) {
        Verificacao verificacao = new Verificacao();
        verificacao.setTipo(tipo);
        verificacao.setData(LocalDateTime.now());
        verificacao.setFraude(fraude);
        verificacao.setImagem1Metadata(m1);
        verificacao.setImagem2Metadata(m2);
        verificacaoRepository.save(verificacao);
    }

    private void validateFileType(MultipartFile file, String name, String tipo) throws Exception {
        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {
            notificationService.enviarNotificacao(tipo, "formato-invalido", file);
            throw new Exception("Tipo de arquivo inválido para " + name);
        }
    }

    private void validateFileSize(MultipartFile file, String name, String tipo) throws Exception {
        if (file.getSize() > MAX_FILE_SIZE) {
            notificationService.enviarNotificacao(tipo, "tamanho-excedido", file);
            throw new Exception("Arquivo muito grande: " + name);
        }
    }

    private void validateResolution(MultipartFile file, String name, String tipo) throws Exception {
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image.getWidth() < MIN_WIDTH || image.getHeight() < MIN_HEIGHT) {
                notificationService.enviarNotificacao(tipo, "resolucao-baixa", file);
                throw new Exception("Resolução muito baixa: " + name);
            }
        } catch (IOException e) {
            notificationService.enviarNotificacao(tipo, "erro-leitura", file);
            throw new Exception("Erro ao ler a imagem: " + name, e);
        }
    }

}
