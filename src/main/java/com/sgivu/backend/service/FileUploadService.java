package com.sgivu.backend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface FileUploadService {

    /**
     * Carga un archivo y lo devuelve como un recurso.
     *
     * @param filename El nombre del archivo a cargar.
     * @return El recurso que representa el archivo cargado.
     * @throws MalformedURLException Si la URL del archivo es mal formada.
     */
    Resource load(String filename) throws MalformedURLException;

    /**
     * Copia un archivo subido al servidor y devuelve el nombre del archivo copiado.
     *
     * @param multipartFile El archivo subido que se va a copiar.
     * @return El nombre del archivo copiado.
     * @throws IOException Si ocurre un error de entrada/salida durante la copia del archivo.
     */
    String copy(MultipartFile multipartFile) throws IOException;

    /**
     * Elimina un archivo del servidor.
     *
     * @param filename El nombre del archivo a eliminar.
     * @return true si el archivo fue eliminado con éxito, false en caso contrario.
     */
    boolean delete(String filename);

}
