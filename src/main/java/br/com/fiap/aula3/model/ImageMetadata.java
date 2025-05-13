package br.com.fiap.aula3.model;

import java.time.LocalDateTime;

public class ImageMetadata {
    private LocalDateTime timestamp; // ISO 8601
    private String ipOrigem;
    private Double latitude;
    private Double longitude;

    private Dispositivo dispositivo;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getIpOrigem() {
        return ipOrigem;
    }

    public void setIpOrigem(String ipOrigem) {
        this.ipOrigem = ipOrigem;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public static class Dispositivo {
        private String fabricante;
        private String modelo;
        private String sistemaOperacional;

        public String getFabricante() {
            return fabricante;
        }

        public void setFabricante(String fabricante) {
            this.fabricante = fabricante;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public String getSistemaOperacional() {
            return sistemaOperacional;
        }

        public void setSistemaOperacional(String sistemaOperacional) {
            this.sistemaOperacional = sistemaOperacional;
        }
    }
}

