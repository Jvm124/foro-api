package com.jvm.foro.domain.usuario;

import com.jvm.foro.domain.ValidacionException;
import com.jvm.foro.domain.perfil.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistroDeUsuarios {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public DatosDetalleUsuario registrar(DatosRegistroUsuario datos){
        if (!perfilRepository.existsById(datos.perfil())){
            throw new ValidacionException("No existe un perfil con el id registrado");
        }
        var perfil = perfilRepository.getReferenceById(datos.perfil());
        var contraseniaEncriptada = passwordEncoder.encode(datos.contrasenia());

        var usuario = new Usuario(datos, contraseniaEncriptada, perfil);
        usuarioRepository.save(usuario);
        return  new DatosDetalleUsuario(usuario);
    }
}
