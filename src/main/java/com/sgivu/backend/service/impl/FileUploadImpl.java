package com.sgivu.backend.service.impl;

import com.sgivu.backend.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadImpl implements FileUploadService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final static String UPLOADS_FOLDER = "uploads";

    /**
     * Carga un recurso (archivo) desde el sistema de archivos.
     *
     * @param filename Nombre del archivo a cargar.
     * @return Recurso que representa el archivo cargado.
     * @throws MalformedURLException Si la URL del archivo es incorrecta.
     * @throws RuntimeException      Si no se puede cargar o leer el archivo.
     */
    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathPhoto = getAbsolutePath(filename);

        logger.info("pathPhoto{}", pathPhoto);

        Resource resource;
        resource = new UrlResource(pathPhoto.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Error: Can't load photo: " + pathPhoto);
        }
        return resource;
    }

    /**
     * Copia un archivo subido (MultipartFile) al sistema de archivos.
     *
     * @param multipartFile Archivo subido que se va a copiar.
     * @return Nombre único generado para el archivo copiado.
     * @throws IOException Si ocurre un error durante la copia del archivo.
     */
    @Override
    public String copy(MultipartFile multipartFile) throws IOException {
        String uniqueFilename = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        Path rootPath = getAbsolutePath(uniqueFilename);

        logger.info("rootPath: {}", rootPath);

        Files.copy(multipartFile.getInputStream(), rootPath);
        return uniqueFilename;
    }

    /**
     * Elimina un archivo del sistema de archivos.
     *
     * @param filename Nombre del archivo a eliminar.
     * @return {@code true} si el archivo fue eliminado con éxito, {@code false} en caso contrario.
     */
    @Override
    public boolean delete(String filename) {
        if (filename == null || filename.isEmpty()) {
            return false;
        }
        Path rootPath = getAbsolutePath(filename);
        File file = rootPath.toFile();
        if (file.exists() && file.canRead()) {
            return file.delete();
        }
        return false;
    }

    /**
     * Obtiene la ruta absoluta de un archivo dado su nombre.
     *
     * @param filename Nombre del archivo.
     * @return Ruta absoluta del archivo.
     */
    public Path getAbsolutePath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }
}
