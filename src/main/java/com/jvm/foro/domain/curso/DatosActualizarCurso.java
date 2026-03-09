package com.jvm.foro.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull Long id,
        String nombre,
        Categoria categoria

) {
}
