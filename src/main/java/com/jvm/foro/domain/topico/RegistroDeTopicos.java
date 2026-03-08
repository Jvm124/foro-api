package com.jvm.foro.domain.topico;

import com.jvm.foro.domain.ValidacionException;
import com.jvm.foro.domain.curso.CursoRepository;
import com.jvm.foro.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroDeTopicos {

    @Autowired
    private  TopicoRepository topicoRepository;

    @Autowired
    private  UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public DatosDetalleTopico registrar(DatosRegistroTopico datos) {

        if(!usuarioRepository.existsById(datos.autor())){
            throw new ValidacionException("No existe un autor con el id registrado");
        }
        if(!cursoRepository.existsById(datos.curso())){
            throw new ValidacionException("No existe un curso con el id registrado");
        }
        //validaciones(despues)
        var autor = usuarioRepository.getReferenceById(datos.autor());
        var curso = cursoRepository.getReferenceById(datos.curso());
        var topico = new Topico(datos, autor, curso);
        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);

    }

}
