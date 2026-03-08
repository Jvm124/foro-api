package com.jvm.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        Long autorId,
        Long cursoId
) {
    public DatosListaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getFechaActualizacion(),
                topico.getAutor().getId(),
                topico.getCurso().getId()
        );
    }
}