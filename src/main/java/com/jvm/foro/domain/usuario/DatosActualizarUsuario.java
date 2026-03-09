package com.jvm.foro.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
        @NotNull Long id,
        String nombre,
        String correoElectronico,
        String contrasenia

) {
}
