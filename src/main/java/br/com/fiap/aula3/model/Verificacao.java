package br.com.fiap.aula3.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "verifications")
public class Verificacao {

    @Id
    private String id;
    private String tipo; // facial, biometria, documento
    private LocalDateTime data;
    private boolean fraude;
    private ImageMetadata imagem1Metadata;
    private ImageMetadata imagem2Metadata;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isFraude() {
        return fraude;
    }

    public void setFraude(boolean fraude) {
        this.fraude = fraude;
    }

    public ImageMetadata getImagem1Metadata() {
        return imagem1Metadata;
    }

    public void setImagem1Metadata(ImageMetadata imagem1Metadata) {
        this.imagem1Metadata = imagem1Metadata;
    }

    public ImageMetadata getImagem2Metadata() {
        return imagem2Metadata;
    }

    public void setImagem2Metadata(ImageMetadata imagem2Metadata) {
        this.imagem2Metadata = imagem2Metadata;
    }
}
