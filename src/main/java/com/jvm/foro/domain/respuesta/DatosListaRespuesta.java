package com.jvm.foro.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListaRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean solucion,
        Long topico,
        Long autor
) {
    public DatosListaRespuesta (Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion(),
                respuesta.getTopico().getId(),
                respuesta.getAutor().getId()

        );
    }
}
