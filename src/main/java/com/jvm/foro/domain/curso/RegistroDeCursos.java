package com.jvm.foro.domain.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegistroDeCursos {
    @Autowired
    private CursoRepository cursoRepository;

    public DatosDetalleCurso registrar(DatosRegistroCurso datos){
        var curso = new Curso(datos);
        cursoRepository.save(curso);
        return new DatosDetalleCurso(curso);
    }


}
