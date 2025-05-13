package br.com.fiap.aula3.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "notifications")
public class FraudNotification {
    private String transacaoId;           // Obrigatório
    private String tipoBiometria;         // Obrigatório
    private String tipoFraude;            // Obrigatório
    private String dataCaptura;           // Obrigatório (ISO 8601)
    private String notificadoPor = "sistema-de-monitoramento";  // Valor fixo
    private List<String> canalNotificacao = List.of("sms", "email");  // Valor fixo

    private Dispositivo dispositivo;      // Opcional
    private Metadados metadados;          // Opcional

    public String getTransacaoId() {
        return transacaoId;
    }

    public void setTransacaoId(String transacaoId) {
        this.transacaoId = transacaoId;
    }

    public String getTipoBiometria() {
        return tipoBiometria;
    }

    public void setTipoBiometria(String tipoBiometria) {
        this.tipoBiometria = tipoBiometria;
    }

    public String getTipoFraude() {
        return tipoFraude;
    }

    public void setTipoFraude(String tipoFraude) {
        this.tipoFraude = tipoFraude;
    }

    public String getDataCaptura() {
        return dataCaptura;
    }

    public void setDataCaptura(String dataCaptura) {
        this.dataCaptura = dataCaptura;
    }

    public String getNotificadoPor() {
        return notificadoPor;
    }

    public void setNotificadoPor(String notificadoPor) {
        this.notificadoPor = notificadoPor;
    }

    public List<String> getCanalNotificacao() {
        return canalNotificacao;
    }

    public void setCanalNotificacao(List<String> canalNotificacao) {
        this.canalNotificacao = canalNotificacao;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Metadados getMetadados() {
        return metadados;
    }

    public void setMetadados(Metadados metadados) {
        this.metadados = metadados;
    }

    public FraudNotification(String transacaoId, String tipoBiometria, String tipoFraude, String dataCaptura, String notificadoPor, List<String> canalNotificacao, Dispositivo dispositivo, Metadados metadados) {
        this.transacaoId = transacaoId;
        this.tipoBiometria = tipoBiometria;
        this.tipoFraude = tipoFraude;
        this.dataCaptura = dataCaptura;
        this.notificadoPor = notificadoPor;
        this.canalNotificacao = canalNotificacao;
        this.dispositivo = dispositivo;
        this.metadados = metadados;
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

        public Dispositivo(String fabricante, String modelo, String sistemaOperacional) {
            this.fabricante = fabricante;
            this.modelo = modelo;
            this.sistemaOperacional = sistemaOperacional;
        }
        // Getters, setters, construtores
    }

    public static class Metadados {
        private Double latitude;
        private Double longitude;
        private String ipOrigem;

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

        public String getIpOrigem() {
            return ipOrigem;
        }

        public void setIpOrigem(String ipOrigem) {
            this.ipOrigem = ipOrigem;
        }

        public Metadados(Double latitude, Double longitude, String ipOrigem) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.ipOrigem = ipOrigem;
        }
        // Getters, setters, construtores
    }
}

