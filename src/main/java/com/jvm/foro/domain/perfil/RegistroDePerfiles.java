package com.jvm.foro.domain.perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroDePerfiles {
    @Autowired
    private PerfilRepository perfilRepository;
    public DatosDetallePerfil registrar(DatosRegistroPerfil datos){
        var perfil = new Perfil(datos);
        perfilRepository.save(perfil);
        return new DatosDetallePerfil(perfil);
    }


}
