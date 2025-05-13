package br.com.fiap.aula3.controller;

import br.com.fiap.aula3.services.ImageValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private ImageValidationService imageValidationService;

    //  1. Verificação Facial
    @PostMapping("/face")
    public ResponseEntity<String> uploadFaceImages(
            @RequestParam("facialNormal") MultipartFile facialNormal,
            @RequestParam("facialSorrindo") MultipartFile facialSorrindo) {

        if (facialNormal.isEmpty() || facialSorrindo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Imagens faciais não podem estar vazias.");
        }

        try {
            imageValidationService.validateImagePair(facialNormal, facialSorrindo, "facialNormal", "facialSorrindo", "facial");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na validação: " + e.getMessage());
        }

        return ResponseEntity.ok("Imagens faciais validadas com sucesso.");
    }

    //  2. Verificação Biométrica
    @PostMapping("/biometry")
    public ResponseEntity<String> uploadBiometryImages(
            @RequestParam("biometriaEsquerdo") MultipartFile biometriaEsquerdo,
            @RequestParam("biometriaDireito") MultipartFile biometriaDireito) {

        if (biometriaEsquerdo.isEmpty() || biometriaDireito.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Imagens biométricas não podem estar vazias.");
        }

        try {
            imageValidationService.validateImagePair(biometriaEsquerdo, biometriaDireito, "biometriaEsquerdo", "biometriaDireito", "biometria");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na validação: " + e.getMessage());
        }

        return ResponseEntity.ok("Imagens biométricas validadas com sucesso.");
    }

    //  3. Verificação de Documentos
    @PostMapping("/document")
    public ResponseEntity<String> uploadDocumentImages(
            @RequestParam("documentFrente") MultipartFile documentFrente,
            @RequestParam("documentVerso") MultipartFile documentVerso) {

        if (documentFrente.isEmpty() || documentVerso.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Imagens do documento não podem estar vazias.");
        }

        try {
            imageValidationService.validateImagePair(documentFrente, documentVerso, "documentFrente", "documentVerso", "documento");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na validação: " + e.getMessage());
        }

        return ResponseEntity.ok("Imagens de documento validadas com sucesso.");
    }
}
