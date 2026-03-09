package com.jvm.foro.domain.respuesta;


import com.jvm.foro.domain.ValidacionException;
import com.jvm.foro.domain.topico.TopicoRepository;
import com.jvm.foro.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegistroDeRespuestas {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RepuestaRepository repuestaRepository;

    public DatosDetalleRespuesta registrar (DatosRegistroRespuesta datos){
        if(!usuarioRepository.existsById(datos.autor())){
            throw new ValidacionException("No existe un autor con el id registrado");
        }

        if(!topicoRepository.existsById(datos.topico())){
            throw new ValidacionException("No existe un tópico con el id registrado");
        }

        var autor = usuarioRepository.getReferenceById(datos.autor());
        var topico = topicoRepository.getReferenceById(datos.topico());
        var respuesta = new Respuesta(datos, autor, topico);
        repuestaRepository.save(respuesta);
        return new DatosDetalleRespuesta(respuesta);
    }
}