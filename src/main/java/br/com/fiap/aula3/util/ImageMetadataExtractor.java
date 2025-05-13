package br.com.fiap.aula3.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.*;
import com.drew.metadata.exif.GpsDirectory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import br.com.fiap.aula3.model.ImageMetadata;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ImageMetadataExtractor {

    public static ImageMetadata extractMetadata(InputStream imageStream) {
        ImageMetadata metadataResult = new ImageMetadata();

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(imageStream);

            // Data/Hora
            ExifSubIFDDirectory exifDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (exifDirectory != null) {
                Date date = exifDirectory.getDateOriginal();
                if (date != null) {
                    LocalDateTime timestamp = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                    metadataResult.setTimestamp(timestamp);
                } else {
                    System.err.println("Data original (Date/Time Original) não encontrada nos metadados.");
                }
            } else {
                System.err.println("Diretório ExifSubIFDDirectory não encontrado.");
            }

            // Localização
            GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
            if (gpsDirectory != null && gpsDirectory.getGeoLocation() != null) {
                metadataResult.setLatitude(gpsDirectory.getGeoLocation().getLatitude());
                metadataResult.setLongitude(gpsDirectory.getGeoLocation().getLongitude());
            } else {
                System.err.println("Metadados de localização não encontrados.");
            }

        } catch (Exception e) {
            System.err.println("Erro ao extrair metadados da imagem: " + e.getMessage());
            e.printStackTrace();
        }

        return metadataResult;
    }
}
