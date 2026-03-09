package com.jvm.foro.domain.usuario;

public record DatosListaUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        String contrasenia,
        Boolean activo,
        Long perfil

) {
    public DatosListaUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getContrasenia(),
                usuario.getActivo(),
                usuario.getPerfil().getId()
        );
    }
}
