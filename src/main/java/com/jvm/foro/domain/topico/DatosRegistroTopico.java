package com.jvm.foro.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @Future LocalDateTime fechaCreacion,
        @NotNull Long autor,
        @NotNull Long curso


) {
}
