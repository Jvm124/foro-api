package com.jvm.foro.domain.respuesta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        @NotBlank String mensaje,
        @Future LocalDateTime fechaCreacion,
        @NotNull Long topico,
        @NotNull Long autor

) {

}
