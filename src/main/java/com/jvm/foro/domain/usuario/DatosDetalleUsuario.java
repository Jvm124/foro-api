package com.jvm.foro.domain.usuario;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        String contrasenia,
        Long perfil

) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getContrasenia(),
                usuario.getPerfil().getId()
        );
    }
}
